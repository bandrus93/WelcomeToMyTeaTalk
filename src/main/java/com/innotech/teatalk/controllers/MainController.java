package com.innotech.teatalk.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.innotech.teatalk.models.Article;
import com.innotech.teatalk.models.Comment;
import com.innotech.teatalk.models.User;
import com.innotech.teatalk.services.ArticleService;
import com.innotech.teatalk.services.CommentService;
import com.innotech.teatalk.services.TagService;
import com.innotech.teatalk.services.UserService;
import com.innotech.teatalk.validators.UserValidator;

@Controller
public class MainController {
	@Autowired
	private UserService uService;
	@Autowired
	private UserValidator uValidator;
	@Autowired
	private ArticleService aService;
	@Autowired
	private TagService tService;
	@Autowired
	private CommentService cService;
	private User loggedInUser;
	private static String UPLOAD_PATH = "src/main/resources/static/images/thumbnails/";
	
	@GetMapping("/")
	public String index(Model model, HttpSession session) {
		if(session.getAttribute("user_id") != null) {
			loggedInUser = uService.fetchUser((Long)session.getAttribute("user_id"));
		} else {
			loggedInUser = null;
		}
		model.addAttribute("user", loggedInUser);
		model.addAttribute("posts", aService.fetchHotTea());
		model.addAttribute("popularPosts", aService.fetchMostPopular());
		model.addAttribute("recentPosts", aService.fetchMostRecent());
		return "index.jsp";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login.jsp";
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model model) {
		if(!uService.authenticateUser(email, password)) {
			model.addAttribute("error", "Invalid credentials");
			return "login.jsp";
		} else {
			session.setAttribute("user_id", uService.fetchUser(email).getId());
			return "redirect:/";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/register")
	public String registrationForm(@ModelAttribute("user") User user) {
		return "register.jsp";
	}
	
	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
		uValidator.validate(user, result);
		if(result.hasErrors()) {
			model.addAttribute("user", user);
			return "register.jsp";
		} else {
			User newUser = uService.createUser(user);
			session.setAttribute("user_id", newUser.getId());
			return "redirect:/";
		}
	}
	
	@GetMapping("/admin")
	public String grantPermission(Model model) {
		model.addAttribute("users", uService.fetchAll());
		return "admin.jsp";
	}
	
	@PostMapping("/admin")
	public String changePermission(@RequestParam("user") Long id, @RequestParam("permission") int permission, Model model) {
		uService.updatePermissions(id, permission);
		model.addAttribute("success", "Permissions updated succesfully");
		return "admin.jsp";
	}
	
	@GetMapping("/{category}")
	public String category(@PathVariable("category") String category, Model model) {
		model.addAttribute("tab", category);
		model.addAttribute("user", loggedInUser);
		model.addAttribute("posts", aService.fetchByCategory(category));
		return "categoryHome.jsp";
	}
	
	@GetMapping("/articles/new")
	public String createPost(Model model) {
		if(loggedInUser.getPermissions() < 2) {
			return "redirect:/";
		} else {
			model.addAttribute("user", loggedInUser);
			return "createPost.jsp";
		}
	}
	
	@PostMapping("/articles/new")
	public String uploadArticle(@RequestParam("title") String title, @RequestParam("thumbnail") MultipartFile thumbnail, @RequestParam("category") String category, @RequestParam("tags") String tags, @RequestParam("postBody") String body, Model model, HttpSession session) {
		if(title == null) {
			model.addAttribute("error", "Title cannot be blank");
			return "createPost.jsp";
		}
		if(thumbnail.isEmpty()) {
			model.addAttribute("error", "Thumbnail cannot be blank");
			return "createPost.jsp";
		}
		if(thumbnail.getSize() > 1000000) {
			model.addAttribute("error", "Thumbnail too large");
			return "createPost.jsp";
		}
		if(body == null) {
			model.addAttribute("error", "Post body cannot be blank");
			return "createPost.jsp";
		}
		Article post;
		try {
			byte[] bytes = thumbnail.getBytes();
			Path path = Paths.get(UPLOAD_PATH + thumbnail.getOriginalFilename());
			Files.write(path, bytes);
			String url = "/images/thumbnails/" + thumbnail.getOriginalFilename();
			Article article = new Article(title, url, category, body, loggedInUser);
			if(tags != null) {
				tService.addTags(article, tags);
			}
			post = aService.createPost(article);
		} catch(IOException e) {
			e.printStackTrace();
			model.addAttribute("error", "Something went wrong. Please refresh the page and try again.");
			return "createPost.jsp";
		}
		return "redirect:/" + category + "/articles/" + post.getId();
	}
	
	@GetMapping("/{category}/articles/{index}")
	public String viewArticle(@PathVariable("index") Long id, Model model) {
		Article article = aService.fetchArticle(id);
		model.addAttribute("user", loggedInUser);
		model.addAttribute("article", article);
		aService.updateViews(article);
		return "article.jsp";
	}
	
	@PostMapping("/{category}/articles/{index}")
	public String postComment(@PathVariable("index") Long id, @RequestParam("comment") String comment, Model model) {
		cService.postComment(loggedInUser, comment, aService.fetchArticle(id));
		model.addAttribute("user", loggedInUser);
		model.addAttribute("article", aService.fetchArticle(id));
		return "article.jsp";
	}
	
	@GetMapping("/articles/edit/{index}")
	public String editArticle(@PathVariable("index") Long id, Model model) {
		Article article = aService.fetchArticle(id);
		if(loggedInUser == article.getAuthor() || loggedInUser.getPermissions() == 3) {
			model.addAttribute("article", article);
			model.addAttribute("user", loggedInUser);
			return "edit.jsp";
		} else {
			return "redirect:/" + article.getCategory() + "/articles/" + article.getId();
		}
	}
	
	@PostMapping("/articles/edit/{index}")
	public String submitEdit(@PathVariable("index") Long id, @RequestParam("title") String title, @RequestParam("thumbnail") MultipartFile thumbnail, @RequestParam("category") String cat, @RequestParam("tags") String tags, @RequestParam("postBody") String body, Model model) {
		if(title == null) {
			model.addAttribute("error", "Title cannot be blank");
			return "edit.jsp";
		}
		if(thumbnail.isEmpty()) {
			model.addAttribute("error", "Thumbnail cannot be blank");
			return "edit.jsp";
		}
		if(body == null) {
			model.addAttribute("error", "Post body cannot be blank");
			return "edit.jsp";
		}
		Article edit;
		try {
			byte[] bytes = thumbnail.getBytes();
			Path path = Paths.get(UPLOAD_PATH + thumbnail.getOriginalFilename());
			if(!Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
				Files.write(path, bytes);
			}
			String url = "/images/thumbnails/" + thumbnail.getOriginalFilename();
			Long views = aService.fetchArticle(id).getViews();
			List<Comment> comments = aService.fetchArticle(id).getComments();
			Article updated = new Article(title, url, cat, body, views, aService.fetchArticle(id).getAuthor(), comments);
			if(tags != null) {
				tService.addTags(updated, tags);
			}
			edit = aService.editPost(updated, id);
		} catch(IOException e) {
			e.printStackTrace();
			model.addAttribute("error", "Something went wrong. Please refresh the page and try again.");
			return "edit.jsp";
		}
		return "redirect:/" + cat + "/articles/" + edit.getId();
	}
	
	@GetMapping("/articles/delete/{index}")
	public String deletePost(@PathVariable("index") Long id) {
		Article article = aService.fetchArticle(id);
		if(loggedInUser == article.getAuthor() || loggedInUser.getPermissions() == 3) {
			aService.deletePost(id);
			return "redirect:/" + article.getCategory();
		} else {
			return "redirect:/" + article.getCategory() + "/articles/" + article.getId();
		}
	}
}

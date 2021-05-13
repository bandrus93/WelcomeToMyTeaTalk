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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	private static String DELETE_PATH = "src/main/resources/static/";
	
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
	public String login(@ModelAttribute("error") String error) {
		return "login.jsp";
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, RedirectAttributes redirect) {
		if(!uService.authenticateUser(email, password)) {
			redirect.addFlashAttribute("error", "Invalid credentials");
			return "redirect:/login";
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
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes redirect, HttpSession session) {
		uValidator.validate(user, result);
		if(result.hasErrors()) {
			redirect.addFlashAttribute("user", user);
			return "redirect:/register";
		} else {
			User newUser = uService.createUser(user);
			session.setAttribute("user_id", newUser.getId());
			return "redirect:/";
		}
	}
	
	@GetMapping("/admin")
	public String grantPermission(@ModelAttribute("success") String message, Model model) {
		if(loggedInUser.getPermissions() < 3 || loggedInUser == null) {
			return "redirect:/login";
		} else {
			model.addAttribute("users", uService.fetchAll());
			return "admin.jsp";
		}
	}
	
	@PostMapping("/admin")
	public String changePermission(@RequestParam("user") Long id, @RequestParam("permission") int permission, RedirectAttributes redirect) {
		uService.updatePermissions(id, permission);
		redirect.addFlashAttribute("success", "Permissions updated succesfully");
		return "redirect:/admin";
	}
	
	@GetMapping("/{category}")
	public String category(@PathVariable("category") String category, Model model) {
		model.addAttribute("tab", category);
		model.addAttribute("user", loggedInUser);
		model.addAttribute("posts", aService.fetchByCategory(category));
		return "categoryHome.jsp";
	}
	
	@PostMapping("/search")
	public String search(@RequestParam("query") String query, Model model) {
		model.addAttribute("posts", aService.search(query));
		model.addAttribute("user", loggedInUser);
		model.addAttribute("tab", "search");
		return "categoryHome.jsp";
	}
	
	@GetMapping("/articles/new")
	public String createPost(@ModelAttribute("error") String error, Model model) {
		if(loggedInUser.getPermissions() < 2) {
			return "redirect:/";
		} else {
			model.addAttribute("user", loggedInUser);
			return "createPost.jsp";
		}
	}
	
	@PostMapping("/articles/new")
	public String uploadArticle(@RequestParam("title") String title, @RequestParam("thumbnail") MultipartFile thumbnail, @RequestParam("category") String category, @RequestParam("tags") String tags, @RequestParam("postBody") String body, RedirectAttributes redirect, HttpSession session) {
		if(title == null) {
			redirect.addFlashAttribute("error", "Title cannot be blank");
			return "redirect:/articles/new";
		}
		if(thumbnail.isEmpty()) {
			redirect.addFlashAttribute("error", "Thumbnail cannot be blank");
			return "redirect:/articles/new";
		}
		if(thumbnail.getSize() > 5000000) {
			redirect.addFlashAttribute("error", "Thumbnail too large");
			return "redirect:/articles/new";
		}
		if(body == null) {
			redirect.addFlashAttribute("error", "Post body cannot be blank");
			return "redirect:/articles/new";
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
			redirect.addFlashAttribute("error", "Something went wrong. Please refresh the page and try again.");
			return "redirect:/articles/new";
		}
		redirect.addFlashAttribute("REDIRECT_FLAG", "true");
		return "redirect:/" + category + "/articles/" + post.getId();
	}
	
	@GetMapping("/{category}/articles/{index}")
	public String viewArticle(@PathVariable("category") String cat, @PathVariable("index") Long id, @ModelAttribute("REDIRECT_FLAG") String redirect, Model model) {
		Article article = aService.fetchArticle(id);
		model.addAttribute("user", loggedInUser);
		model.addAttribute("tab", cat);
		model.addAttribute("article", article);
		if(!redirect.equals("true")) {
			aService.updateViews(article);
		}
		return "article.jsp";
	}
	
	@PostMapping("/{category}/articles/{index}")
	public String postComment(@PathVariable("category") String category, @PathVariable("index") Long id, @RequestParam("comment") String comment, RedirectAttributes redirect) {
		cService.postComment(loggedInUser, comment, aService.fetchArticle(id));
		redirect.addFlashAttribute("REDIRECT_FLAG", "true");
		return "redirect:/" + category + "/articles/" + id;
	}
	
	@GetMapping("/articles/edit/{index}")
	public String editArticle(@ModelAttribute("error") String error, @PathVariable("index") Long id, RedirectAttributes redirect, Model model) {
		Article article = aService.fetchArticle(id);
		if(loggedInUser == article.getAuthor() || loggedInUser.getPermissions() == 3) {
			model.addAttribute("article", article);
			model.addAttribute("user", loggedInUser);
			return "edit.jsp";
		} else {
			redirect.addFlashAttribute("REDIRECT_FLAG", "true");
			return "redirect:/" + article.getCategory() + "/articles/" + article.getId();
		}
	}
	
	@PostMapping("/articles/edit/{index}")
	public String submitEdit(@PathVariable("index") Long id, @RequestParam("title") String title, @RequestParam("thumbnail") MultipartFile thumbnail, @RequestParam("category") String cat, @RequestParam("tags") String tags, @RequestParam("postBody") String body, RedirectAttributes redirect) {
		String url = "";
		if(title == null) {
			redirect.addFlashAttribute("error", "Title cannot be blank");
			return "redirect:/articles/edit/" + id;
		}
		if(thumbnail.isEmpty()) {
			url = aService.fetchArticle(id).getThumbnail();
		}
		if(thumbnail.getSize() > 5000000) {
			redirect.addFlashAttribute("error", "Thumbnail too large");
			return "redirect:/articles/edit/" + id;
		}
		if(body == null) {
			redirect.addFlashAttribute("error", "Post body cannot be blank");
			return "redirect:/articles/edit/" + id;
		}
		Article edit;
		try {
			if(!thumbnail.isEmpty()) {
				byte[] bytes = thumbnail.getBytes();
				Path path = Paths.get(UPLOAD_PATH + thumbnail.getOriginalFilename());
				if(!Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
					Files.write(path, bytes);
				}
				url = "/images/thumbnails/" + thumbnail.getOriginalFilename();
			}
			Long views = aService.fetchArticle(id).getViews();
			List<Comment> comments = aService.fetchArticle(id).getComments();
			Article updated = new Article(title, url, cat, body, views, aService.fetchArticle(id).getAuthor(), comments);
			if(tags != null) {
				tService.addTags(updated, tags);
			}
			edit = aService.editPost(updated, id);
		} catch(IOException e) {
			e.printStackTrace();
			redirect.addFlashAttribute("error", "Something went wrong. Please refresh the page and try again.");
			return "redirect:/articles/edit/" + id;
		}
		redirect.addFlashAttribute("REDIRECT_FLAG", "true");
		return "redirect:/" + cat + "/articles/" + edit.getId();
	}
	
	@GetMapping("/articles/delete/{index}")
	public String deletePost(@PathVariable("index") Long id, RedirectAttributes redirect) {
		Article article = aService.fetchArticle(id);
		if(loggedInUser == article.getAuthor() || loggedInUser.getPermissions() == 3) {
			aService.deletePost(id);
			Path path = Paths.get(DELETE_PATH + article.getThumbnail());
			try {
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "redirect:/" + article.getCategory();
		} else {
			redirect.addFlashAttribute("REDIRECT_FLAG", "true");
			return "redirect:/" + article.getCategory() + "/articles/" + article.getId();
		}
	}
	
	@PostMapping("/comments/reply/{index}")
	public String replyComment(@PathVariable("index") Long id, @RequestParam("reply") String reply, @RequestParam("article") Long articleId, RedirectAttributes redirect) {
		Article article = aService.fetchArticle(articleId);
		Comment tRoot = cService.fetchComment(id);
		cService.postReply(loggedInUser, reply, tRoot);
		redirect.addFlashAttribute("REDIRECT_FLAG", "true");
		return "redirect:/" + article.getCategory() + "/articles/" + articleId;
	}
	
	@PostMapping("/comments/edit/{index}")
	public String editComment(@PathVariable("index") Long id, @RequestParam("comment") String comment, @RequestParam("article") Long articleId, RedirectAttributes redirect) {
		Article article = aService.fetchArticle(articleId);
		cService.editComment(id, comment);
		redirect.addFlashAttribute("REDIRECT_FLAG", "true");
		return "redirect:/" + article.getCategory() + "/articles/" + articleId;
	}
	
	@GetMapping("/comments/delete/{index}/{id}")
	public String deleteComment(@PathVariable("index") Long id, @PathVariable("id") Long articleId, RedirectAttributes redirect) {
		Article article = aService.fetchArticle(articleId);
		cService.deleteComment(id);
		redirect.addFlashAttribute("REDIRECT_FLAG", "true");
		return "redirect:/" + article.getCategory() + "/articles/" + articleId;
	}
	
	@GetMapping("/info/{category}")
	public String showInfo(@PathVariable("category") String cat, Model model) {
		model.addAttribute("tab", cat);
		model.addAttribute("user", loggedInUser);
		return "info.jsp";
	}
}

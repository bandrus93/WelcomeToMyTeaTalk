package com.innotech.teatalk.services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innotech.teatalk.models.Article;
import com.innotech.teatalk.repositories.ArticleRepository;
import com.innotech.teatalk.repositories.TagRepository;

@Service
public class ArticleService {
	@Autowired
	private ArticleRepository aRepo;
	@Autowired
	private TagRepository tRepo;
	
	public Article createPost(Article article) {
		return aRepo.save(article);
	}
	
	public Article editPost(Article article, Long id) {
		article.setId(id);
		return aRepo.save(article);
	}
	
	public void deletePost(Long id) {
		aRepo.deleteById(id);
	}
	
	public Article fetchArticle(Long id) {
		return aRepo.findById(id).orElse(null);
	}
	
	public List<Article> fetchByCategory(String cat) {
		return aRepo.findByCategory(cat);
	}
	
	public List<Article> fetchMostPopular() {
		List<Article> popular = aRepo.findAllByOrderByViewsDesc();
		List<Article> displayResult = new ArrayList<Article>();
		for(int i = 0; i < popular.size(); i++) {
			displayResult.add(popular.get(i));
			if(i == 4) {
				break;
			}
		}
		return displayResult;
	}
	
	public List<Article> fetchMostRecent() {
		List<Article> recent = aRepo.findAllByOrderByCreatedOnDesc();
		List<Article> displayResult = new ArrayList<Article>();
		for(int i = 0; i < recent.size(); i++) {
			displayResult.add(recent.get(i));
			if(i == 4) {
				break;
			}
		}
		return displayResult;
	}
	
	public List<Article> fetchHotTea() {
		List<Article> articles = aRepo.findAll();
		List<Article> displayResult = new ArrayList<Article>();
		for(int i = 0; i < 10; i++) {
			int continueCounter = 0;
			for(int j = 0; j < articles.size(); j++) {
				if(j == 0 && i == 0) {
					displayResult.add(articles.get(j));
				}
				else if(j == continueCounter && displayResult.contains(articles.get(j))) {
					continueCounter++;
					continue;
				}
				else if(j == continueCounter && !displayResult.contains(articles.get(j))) {
					displayResult.add(articles.get(j));
				}
				else if(displayResult.get(i).getComments().size() <= articles.get(j).getComments().size() && !displayResult.contains(articles.get(j))) {
					displayResult.set(i, articles.get(j));
				}
			}
			if(i + 1 == articles.size()) {
				break;
			}
		}
		return displayResult;
	}
	
	public List<Article> search(String query) {
		List<Article> articles = aRepo.findAll();
		List<Article> searchResult = new ArrayList<Article>();
		for(int i = 0; i < 6; i++) {
			switch(i) {
			case 0:
				for(int j = 0; j < articles.size(); j++) {
					if(Pattern.compile(Pattern.quote(query), Pattern.CASE_INSENSITIVE).matcher(articles.get(j).getTitle()).find() && articles.get(j).getTags().contains(tRepo.findByNameContaining(query)) && Pattern.compile(Pattern.quote(query), Pattern.CASE_INSENSITIVE).matcher(articles.get(j).getPostBody()).find()) {
						searchResult.add(articles.get(j));
					}
				}
				break;
			case 1:
				for(int j = 0; j < articles.size(); j++) {
					if(Pattern.compile(Pattern.quote(query), Pattern.CASE_INSENSITIVE).matcher(articles.get(j).getTitle()).find() && articles.get(j).getTags().contains(tRepo.findByNameContaining(query)) && !searchResult.contains(articles.get(j))) {
						searchResult.add(articles.get(j));
					}
				}
				break;
			case 2:
				for(int j = 0; j < articles.size(); j++) {
					if(articles.get(j).getTags().contains(tRepo.findByNameContaining(query)) && Pattern.compile(Pattern.quote(query), Pattern.CASE_INSENSITIVE).matcher(articles.get(j).getPostBody()).find() && !searchResult.contains(articles.get(j))) {
						searchResult.add(articles.get(j));
					}
				}
				break;
			case 3:
				for(int j = 0; j < articles.size(); j++) {
					if(Pattern.compile(Pattern.quote(query), Pattern.CASE_INSENSITIVE).matcher(articles.get(j).getTitle()).find() && !searchResult.contains(articles.get(j))) {
						searchResult.add(articles.get(j));
					}
				}
				break;
			case 4:
				for(int j = 0; j < articles.size(); j++) {
					if(articles.get(j).getTags().contains(tRepo.findByNameContaining(query)) && !searchResult.contains(articles.get(j))) {
						searchResult.add(articles.get(j));
					}
				}
				break;
			case 5:
				for(int j = 0; j < articles.size(); j++) {
					if(Pattern.compile(Pattern.quote(query), Pattern.CASE_INSENSITIVE).matcher(articles.get(j).getPostBody()).find() && !searchResult.contains(articles.get(j))) {
						searchResult.add(articles.get(j));
					}
				}
				break;
			}
		}
		return searchResult;
	}
	
	public void updateViews(Article article) {
		Long views = article.getViews();
		if(views == null) {
			article.setViews((long)1);
		} else {
			views++;
			article.setViews(views);
		}
		aRepo.save(article);
	}
}

package com.innotech.teatalk.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innotech.teatalk.models.Article;
import com.innotech.teatalk.repositories.ArticleRepository;

@Service
public class ArticleService {
	@Autowired
	private ArticleRepository aRepo;
	
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
			if(i == 9) {
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
			if(i == 9) {
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

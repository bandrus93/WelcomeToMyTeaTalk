package com.innotech.teatalk.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.innotech.teatalk.models.Article;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
	List<Article> findAll();
	List<Article> findByCategory(String cat);
	List<Article> findAllByOrderByViewsDesc();
	List<Article> findAllByOrderByCreatedOnDesc();
}

package com.innotech.teatalk.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.innotech.teatalk.models.Article;
import com.innotech.teatalk.models.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
	List<Comment> findByArticle(Article article);
}

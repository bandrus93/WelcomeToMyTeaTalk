package com.innotech.teatalk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innotech.teatalk.models.Article;
import com.innotech.teatalk.models.Comment;
import com.innotech.teatalk.models.User;
import com.innotech.teatalk.repositories.CommentRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository cRepo;
	
	public void postComment(User user, String comment, Article article) {
		Comment threadRoot = new Comment(user, comment, article);
		cRepo.save(threadRoot);
	}
}

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
	
	public Comment fetchComment(Long id) {
		return cRepo.findById(id).orElse(null);
	}
	
	public void postComment(User user, String comment, Article article) {
		Comment threadRoot = new Comment(user, comment, article);
		cRepo.save(threadRoot);
	}
	
	public void postReply(User user, String reply, Comment thread) {
		Comment response = new Comment(user, reply, thread);
		cRepo.save(response);
	}
	
	public void editComment(Long id, String edited) {
		Comment editable = cRepo.findById(id).get();
		editable.setContent(edited);
		editable.setId(id);
		cRepo.save(editable);
	}
	
	public void deleteComment(Long id) {
		cRepo.deleteById(id);
	}
}

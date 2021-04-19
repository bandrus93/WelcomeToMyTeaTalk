package com.innotech.teatalk.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String content;
	@Column(updatable=false)
	private Date submittedOn;
	private Date editedOn;
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="replies",
		joinColumns=@JoinColumn(name="comment_id"),
		inverseJoinColumns=@JoinColumn(name="reply_id")
	)
	private List<Comment> threads;
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="replies",
		joinColumns=@JoinColumn(name="reply_id"),
		inverseJoinColumns=@JoinColumn(name="comment_id")
	)
	private List<Comment> replies;
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	@ManyToOne(fetch=FetchType.LAZY)
	private Article article;
	
	public Comment() {
		
	}
	
	public Comment(User user, String comment, Article article) {
		this.user = user;
		this.content = comment;
		this.article = article;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSubmittedOn() {
		return submittedOn;
	}

	public void setSubmittedOn(Date submittedOn) {
		this.submittedOn = submittedOn;
	}

	public Date getEditedOn() {
		return editedOn;
	}

	public void setEditedOn(Date editedOn) {
		this.editedOn = editedOn;
	}

	public List<Comment> getThreads() {
		return threads;
	}

	public void setThreads(List<Comment> threads) {
		this.threads = threads;
	}

	public List<Comment> getReplies() {
		return replies;
	}

	public void setReplies(List<Comment> replies) {
		this.replies = replies;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
	@PrePersist
	protected void onCreate() {
		this.submittedOn = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.editedOn = new Date();
	}
}

package com.innotech.teatalk.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="articles")
public class Article {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String title;
	private String thumbnail;
	private String category;
	private String postBody;
	private Long views = (long) 0;
	@Column(updatable=false)
	private Date createdOn;
	private Date updatedOn;
	@ManyToMany
	@JoinTable(
		name="article_tags",
		joinColumns=@JoinColumn(name="article_id"),
		inverseJoinColumns=@JoinColumn(name="tag_id")
	)
	private List<Tag> tags;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="author_id")
	private User author;
	@OneToMany(mappedBy="article", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Comment> comments;
	
	public Article() {
		
	}
	
	public Article(String title, String thumbnail, String cat, String body, User author) {
		this.title = title;
		this.thumbnail = thumbnail;
		this.category = cat;
		this.postBody = body;
		this.author = author;
	}
	
	public Article(String title, String thumbnail, String cat, String body, Long views, User author, List<Comment> comments) {
		this.title = title;
		this.thumbnail = thumbnail;
		this.category = cat;
		this.postBody = body;
		this.views = views;
		this.author = author;
		this.comments = comments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPostBody() {
		return postBody;
	}

	public void setPostBody(String postBody) {
		this.postBody = postBody;
	}

	public Long getViews() {
		return views;
	}

	public void setViews(Long views) {
		this.views = views;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdOn = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedOn = new Date();
	}
}

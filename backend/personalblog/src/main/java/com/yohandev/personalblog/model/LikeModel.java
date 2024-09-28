package com.yohandev.personalblog.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "likes")
public class LikeModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private LocalDateTime createdAt;
	
	@ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private UserModel user;
	
	@ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
	private PostModel post;
	
	public LikeModel() {
		
	}

	public LikeModel(long id, LocalDateTime createdAt, UserModel user, PostModel post) {
		this.id = id;
		this.createdAt = createdAt;
		this.user = user;
		this.post = post;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public PostModel getPost() {
		return post;
	}

	public void setPost(PostModel post) {
		this.post = post;
	}
	
	
}

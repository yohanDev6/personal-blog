package com.yohandev.personalblog.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class CommentModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String content;
	
	private LocalDateTime createdAt, updatedAt;
	
	@ManyToOne
    @JoinColumn(name = "parent_id")
    private CommentModel parentComment;

    // Comentários filhos (uma lista de comentários que são respostas a este comentário)
    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentModel> replies = new ArrayList<>();
	
	@ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private UserModel user;
	
	@ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
	private PostModel post;
	
	public CommentModel() {
		
	}

	public CommentModel(long id, String content, LocalDateTime createdAt, LocalDateTime updatedAt,
			CommentModel parentComment, UserModel user, PostModel post) {
		this.id = id;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.parentComment = parentComment;
		this.user = user;
		this.post = post;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public CommentModel getParentComment() {
		return parentComment;
	}

	public void setParentComment(CommentModel parentComment) {
		this.parentComment = parentComment;
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

	public List<CommentModel> getReplies() {
		return replies;
	}

	public void setReplies(List<CommentModel> replies) {
		this.replies = replies;
	}
	
	
}

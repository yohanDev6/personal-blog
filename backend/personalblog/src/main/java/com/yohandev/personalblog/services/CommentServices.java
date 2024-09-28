package com.yohandev.personalblog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yohandev.personalblog.dtos.CommentDTO;
import com.yohandev.personalblog.model.CommentModel;
import com.yohandev.personalblog.model.PostModel;
import com.yohandev.personalblog.model.UserModel;
import com.yohandev.personalblog.repositories.CommentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CommentServices extends Services{

	private final CommentRepository commentRepository;
	
	public CommentServices(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	
	public void saveComment(CommentDTO commentDTO, UserModel user, PostModel post) {
	    CommentModel comment = commentDTO.convertDTOToObject();
	    comment.setUser(user);
	    comment.setPost(post);
	    comment.setCreatedAt(setDateTimeNow());

	    if (commentDTO.parentId() > 0) {
	        CommentModel parentComment = getCommentById(commentDTO.parentId());
	        comment.setParentComment(parentComment);
	    }

	    commentRepository.save(comment);
	}

	public CommentModel getCommentById(long id) {
		return commentRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Comment not found"));
	}
	
	public List<CommentModel> getParentCommentsByPost(PostModel post) {
        return commentRepository.findByPostAndParentCommentIsNull(post);
    }

    public List<CommentModel> getCommentsByParentId(long parentId) {
        return commentRepository.findByParentComment_Id(parentId);
    }
    
	public void updateComment(CommentModel comment, UserModel user) {
		verifyFKRelation(comment.getUser().getId(), user.getId());
		comment.setUpdatedAt(setDateTimeNow());
		commentRepository.save(comment);
	}
	
	public void deleteComment(CommentModel comment, UserModel user) {
		verifyFKRelation(comment.getUser().getId(), user.getId());
		commentRepository.delete(comment);
	}
}

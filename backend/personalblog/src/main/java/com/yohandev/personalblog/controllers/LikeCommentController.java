package com.yohandev.personalblog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yohandev.personalblog.dtos.CommentDTO;
import com.yohandev.personalblog.dtos.LikeDTO;
import com.yohandev.personalblog.model.CommentModel;
import com.yohandev.personalblog.services.CommentServices;
import com.yohandev.personalblog.services.LikeServices;
import com.yohandev.personalblog.services.PostServices;
import com.yohandev.personalblog.services.UserServices;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("posts/")
public class LikeCommentController {
	
	@Autowired
	private PostServices postServices;
	
	@Autowired
	private UserServices userServices;
	
	@Autowired
	private LikeServices likeServices;
	
	@Autowired
	private CommentServices commentServices;
	
	@PostMapping("likes")
	public ResponseEntity<String> saveLike(@Valid @RequestBody LikeDTO likeDTO) {
		likeServices.saveLike(userServices.getUserById(likeDTO.userId()), postServices.getPostById(likeDTO.postId()));
		return new ResponseEntity<>("Like saved successfully", HttpStatus.CREATED);
	}
	
	@DeleteMapping("likes")
	public ResponseEntity<String> deleteLike(@Valid @RequestBody LikeDTO likeDTO) {
		likeServices.removeLike(likeDTO.id(), userServices.getUserById(likeDTO.userId()), postServices.getPostById(likeDTO.postId()));
		return new ResponseEntity<>("Like deleted successfully", HttpStatus.OK);
	}
	
    @PostMapping("comments")
    @Transactional
    public ResponseEntity<String> saveComment(@Valid @RequestBody CommentDTO commentDTO) {
        commentServices.saveComment(commentDTO, userServices.getUserById(commentDTO.userId()), postServices.getPostById(commentDTO.postId()));
        return new ResponseEntity<>("Comment created successfully", HttpStatus.CREATED);
    }

    @GetMapping("{postId}/comments")
    public ResponseEntity<?> getCommentsByPost(@PathVariable long postId) {
        List<CommentModel> parentComments = commentServices.getParentCommentsByPost(postServices.getPostById(postId));
        List<CommentDTO> commentDTOs = CommentDTO.convertToCommentDTOList(parentComments);
        return new ResponseEntity<>(commentDTOs, HttpStatus.OK);
    }
    
    @GetMapping("comments/{commentId}")
    public ResponseEntity<?> getCommentById(@PathVariable long commentId) {
    	CommentDTO comment = new CommentDTO(commentServices.getCommentById(commentId));
    	return new ResponseEntity<>(comment, HttpStatus.OK);
    }
    
    @PutMapping("comments")
    @Transactional
    public ResponseEntity<String> updateComment(@Valid @RequestBody CommentDTO commentDTO) {
        commentServices.updateComment(commentServices.getCommentById(commentDTO.id()), userServices.getUserById(commentDTO.userId()));
        return new ResponseEntity<>("Comment updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("comments")
    @Transactional
    public ResponseEntity<String> deleteComment(@Valid @RequestBody CommentDTO commentDTO) {
        commentServices.deleteComment(commentServices.getCommentById(commentDTO.id()), userServices.getUserById(commentDTO.userId()));
        
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}

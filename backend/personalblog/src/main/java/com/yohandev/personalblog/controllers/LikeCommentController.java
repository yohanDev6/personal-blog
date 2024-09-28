package com.yohandev.personalblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yohandev.personalblog.dtos.LikeDTO;
import com.yohandev.personalblog.services.LikeServices;
import com.yohandev.personalblog.services.PostServices;
import com.yohandev.personalblog.services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("posts/reactions")
public class LikeCommentController {
	
	@Autowired
	private PostServices postServices;
	
	@Autowired
	private UserServices userServices;
	
	@Autowired
	private LikeServices likeServices;
	
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
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yohandev.personalblog.controllers;

import com.yohandev.personalblog.dtos.PostDTO;
import com.yohandev.personalblog.model.UserModel;
import com.yohandev.personalblog.services.PostServices;
import com.yohandev.personalblog.services.UserServices;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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

@RestController
@RequestMapping("posts")
public class PostController {
    
    @Autowired
    private PostServices postServices;
    
    @Autowired
    private UserServices userServices;
    
    @PostMapping
    @Transactional
    public ResponseEntity<String> savePost(@Valid @RequestBody PostDTO postDTO) {
        UserModel user = userServices.getUserById(postDTO.userId());
        postServices.savePost(postDTO, user);
        return new ResponseEntity<>("Post created successfully", HttpStatus.CREATED);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<?> getPostById(@PathVariable long id) {
        PostDTO postDTO = new PostDTO(postServices.getPostById(id));
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }
   
    @PutMapping
    @Transactional
    public ResponseEntity<String> updatePost(@Valid @RequestBody PostDTO postDTO) {
        UserModel user = userServices.getUserById(postDTO.userId());
        postServices.updatePost(postDTO, user.getId());
        return new ResponseEntity<>("Post updated successfully", HttpStatus.OK);
    }
    
    @DeleteMapping
    @Transactional
    public ResponseEntity<String> deletePost(@Valid @RequestBody PostDTO postDTO) {
        UserModel user = userServices.getUserById(postDTO.userId());
        postServices.deletePost(postDTO, user.getId());
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }
}

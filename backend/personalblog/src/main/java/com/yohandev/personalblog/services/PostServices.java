/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yohandev.personalblog.services;

import com.yohandev.personalblog.dtos.PostDTO;
import com.yohandev.personalblog.model.PostModel;
import com.yohandev.personalblog.model.UserModel;
import com.yohandev.personalblog.repositories.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PostServices extends Services{
    
    private final PostRepository postRepository;
    
    public PostServices(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    
    public void savePost(PostDTO postDTO, UserModel user) {
        PostModel post = postDTO.convertDTOToObject();
        
        post.setUser(user);
        post.setCreatedAt(setDateTimeNow());
        
        postRepository.save(post);
    }
    
    public PostModel getPostById(long id) {
        verifyId(id);
        
        PostModel post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        
        return post;
    }
    
    public void updatePost(PostDTO postDTO, long userId) {
        verifyId(postDTO.id());
        verifyFKRelation(userId, postDTO.userId());
        
        PostModel post = postRepository.findById(postDTO.id())
                .orElseThrow(() -> new RuntimeException("Post not found"));
        
        post.setTitle(postDTO.title());
        
        if (postDTO.content().length() <= 0 || postDTO.content() != "") {
            post.setContent(postDTO.content());
        }
        
        post.setUpdatedAt(setDateTimeNow());
        
        postRepository.save(post);
    }
    
    public void deletePost(PostDTO postDTO, long userId) {
        verifyId(postDTO.id());
        
        verifyFKRelation(userId, postDTO.userId());
        
        PostModel post = postRepository.findById(postDTO.id())
                .orElseThrow(() -> new RuntimeException("Post not found"));
        
        postRepository.delete(post);
    }
}

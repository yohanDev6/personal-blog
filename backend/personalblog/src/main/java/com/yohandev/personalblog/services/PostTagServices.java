/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yohandev.personalblog.services;

import com.yohandev.personalblog.model.PostModel;
import com.yohandev.personalblog.model.TagModel;
import com.yohandev.personalblog.repositories.PostRepository;
import com.yohandev.personalblog.repositories.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PostTagServices extends Services{
    
    private final PostRepository postRepository;
    
    private final TagRepository tagRepository;
    
    public PostTagServices(PostRepository postRepository, TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
    }
    
    public void savePostTag(long postId, long tagId) {
        verifyId(postId);
        verifyId(tagId);
        
        PostModel post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        
        TagModel tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new EntityNotFoundException("Tag not found"));
               
        post.getTags().add(tag);
        postRepository.save(post);
    }
    
    public void deletePostTag(long postId, long tagId) {
        verifyId(postId);
        verifyId(tagId);
        
        PostModel post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        
        TagModel tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new EntityNotFoundException("Tag not found"));
               
        post.getTags().remove(tag);
        postRepository.save(post);
    }
}

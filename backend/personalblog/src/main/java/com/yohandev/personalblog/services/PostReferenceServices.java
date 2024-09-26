/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yohandev.personalblog.services;

import com.yohandev.personalblog.dtos.PostReferenceDTO;
import com.yohandev.personalblog.model.PostModel;
import com.yohandev.personalblog.model.PostReferencesModel;
import com.yohandev.personalblog.repositories.PostReferenceRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PostReferenceServices extends Services{
    
    private final PostReferenceRepository postReferenceRepository;
    
    public PostReferenceServices(PostReferenceRepository postReferenceRepository) {
        this.postReferenceRepository = postReferenceRepository;
    }
    
    public void savePostReference(PostReferenceDTO postReferenceDTO, PostModel post) {
        PostReferencesModel postReference = postReferenceDTO.convertDTOToObject();
        
        postReference.setContent(postReferenceDTO.content());
        postReference.setPost(post);
        
        postReferenceRepository.save(postReference);
    }
    
    public List<PostReferencesModel> getPostReferencesByPost(PostModel post) {
        verifyId(post.getId());
        return postReferenceRepository.findByPost(post);
    }
    
    public List<PostReferencesModel> getAllPostReferences() {
        return postReferenceRepository.findAll();
    }
    
    public void updatePostReference(PostReferenceDTO postReferenceDTO, long postId) {
        verifyId(postReferenceDTO.id());
        verifyFKRelation(postId, postReferenceDTO.postId());
        
        PostReferencesModel postReference = postReferenceRepository.findById(postReferenceDTO.id())
                .orElseThrow(() -> new RuntimeException("Post reference not found"));
        
        postReference.setContent(postReferenceDTO.content());
        
        postReferenceRepository.save(postReference);
    }
    
    public void deletePostReference(PostReferenceDTO postReferenceDTO, long postId) {
        verifyId(postReferenceDTO.id());
        
        verifyFKRelation(postId, postReferenceDTO.postId());
        
        PostReferencesModel post = postReferenceRepository.findById(postReferenceDTO.id())
                .orElseThrow(() -> new RuntimeException("Post not found"));
        
        postReferenceRepository.delete(post);
    }
}

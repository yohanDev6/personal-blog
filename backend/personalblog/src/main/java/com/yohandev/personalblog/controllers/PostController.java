/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yohandev.personalblog.controllers;

import com.yohandev.personalblog.dtos.ImageReqDTO;
import com.yohandev.personalblog.dtos.ImageResDTO;
import com.yohandev.personalblog.dtos.PostDTO;
import com.yohandev.personalblog.dtos.PostReferenceDTO;
import com.yohandev.personalblog.services.ImageServices;
import com.yohandev.personalblog.services.PostReferenceServices;
import com.yohandev.personalblog.services.PostServices;
import com.yohandev.personalblog.services.UserServices;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.io.IOException;
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

@RestController
@RequestMapping("posts")
public class PostController {
    
    @Autowired
    private PostServices postServices;
    
    @Autowired
    private UserServices userServices;
    
    @Autowired
    private PostReferenceServices postReferenceService;
    
    @Autowired
    private ImageServices imageServices;
    
    @PostMapping
    @Transactional
    public ResponseEntity<String> savePost(@Valid @RequestBody PostDTO postDTO) {
        postServices.savePost(postDTO, userServices.getUserById(postDTO.userId()));
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
        postServices.updatePost(postDTO, userServices.getUserById(postDTO.userId()).getId());
        return new ResponseEntity<>("Post updated successfully", HttpStatus.OK);
    }
    
    @DeleteMapping
    @Transactional
    public ResponseEntity<String> deletePost(@Valid @RequestBody PostDTO postDTO) {
        postServices.deletePost(postDTO, userServices.getUserById(postDTO.userId()).getId());
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }
    
    @PostMapping("postsreferences")
    @Transactional
    public ResponseEntity<String> savePostReference(@Valid @RequestBody PostReferenceDTO postReferenceDTO) {
        postReferenceService.savePostReference(postReferenceDTO, postServices.getPostById(postReferenceDTO.postId()));
        return new ResponseEntity<>("Post Reference created successfully", HttpStatus.CREATED);
    }
    
    @GetMapping("postsreferences")
    public ResponseEntity<?> getAllPostReferences() {
        List<PostReferenceDTO> postReferences = PostReferenceDTO.convertToPostReferenceList(postReferenceService.getAllPostReferences());
        return new ResponseEntity<>(postReferences, HttpStatus.OK);
    }
    
    @GetMapping("{postId}/postsreferences")
    public ResponseEntity<?> getPostReferencesByPost(@PathVariable long postId) {    
        List<PostReferenceDTO> postReferences = PostReferenceDTO
                .convertToPostReferenceList(postReferenceService.getPostReferencesByPost(postServices.getPostById(postId)));
        return new ResponseEntity<>(postReferences, HttpStatus.OK);
    }
    
    @PutMapping("{postId}/postsreferences")
    public ResponseEntity<String> updatePostReference(@Valid @RequestBody PostReferenceDTO postReferenceDTO, @PathVariable long postId) {
        postReferenceService.updatePostReference(postReferenceDTO, postId);
        return new ResponseEntity<>("Post Reference updated successfully", HttpStatus.OK);
    }
    
    @DeleteMapping("{postId}/postsreferences")
    public ResponseEntity<String> deletePostReference(@RequestBody PostReferenceDTO postReferenceDTO, @PathVariable long postId) {
        postReferenceService.deletePostReference(postReferenceDTO, postId);
        return new ResponseEntity<>("Post Reference deleted successfully", HttpStatus.OK);
    }
    
    @PostMapping("images")
    @Transactional
    public ResponseEntity<String> saveImage(@Valid @RequestBody ImageReqDTO imageReqDTO) throws IOException {
        imageServices.saveImage(imageReqDTO, postServices.getPostById(imageReqDTO.postId()));
        return new ResponseEntity<>("Image created successfully", HttpStatus.CREATED);
    }
    
    @GetMapping("{postId}/images")
    public ResponseEntity<?> getImagesByPost(@PathVariable long postId) {    
        List<ImageResDTO> images = ImageResDTO.convertToImageResDTOList(imageServices.getImagesByPost(postServices.getPostById(postId)));
        return new ResponseEntity<>(images, HttpStatus.OK);
    }
    
    @PutMapping("{postId}/images")
    public ResponseEntity<String> updateImage(@Valid @RequestBody ImageReqDTO imageReqDTO, @PathVariable long postId) throws IOException {
        imageServices.updateImage(imageReqDTO, postId);
        return new ResponseEntity<>("Image updated successfully", HttpStatus.OK);
    }
    
    @DeleteMapping("{postId}/images")
    public ResponseEntity<String> deleteImage(@RequestBody ImageReqDTO imageReqDTO, @PathVariable long postId) {
        imageServices.deleteImage(imageReqDTO, postId);
        return new ResponseEntity<>("Image deleted successfully", HttpStatus.OK);
    }
}

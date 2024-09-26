/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yohandev.personalblog.services;

import com.yohandev.personalblog.dtos.ImageReqDTO;
import com.yohandev.personalblog.model.ImageModel;
import com.yohandev.personalblog.model.PostModel;
import com.yohandev.personalblog.repositories.ImageRepository;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ImageServices extends Services{
    
    private final ImageRepository imageRepository;
    
    public ImageServices(ImageRepository imageRepository) {
        this.imageRepository= imageRepository;
    }
    
    public void saveImage(ImageReqDTO imageReqDTO, PostModel post) throws IOException {
        ImageModel image = imageReqDTO.convertDTOToObject();
        
        image.setPost(post);
        
        imageRepository.save(image);
    }
    
    public List<ImageModel> getImagesByPost(PostModel post) {
        return imageRepository.findByPost(post);
    }
    
    public void updateImage(ImageReqDTO imageReqDTO, long postId) throws IOException {
        verifyId(imageReqDTO.id());
        verifyFKRelation(postId, imageReqDTO.postId());
        
        ImageModel image = imageRepository.findById(imageReqDTO.id())
                .orElseThrow(() -> new RuntimeException("Image not found"));
        
        image.setAlt(imageReqDTO.alt());
        image.setContent(imageReqDTO.content().getBytes());
        
        imageRepository.save(image);
    }
    
    public void deleteImage(ImageReqDTO imageReqDTO, long postId) {
        verifyId(imageReqDTO.id());
        verifyFKRelation(postId, imageReqDTO.postId());
        
        ImageModel image = imageRepository.findById(imageReqDTO.id())
                .orElseThrow(() -> new RuntimeException("Image not found"));
        
        imageRepository.delete(image);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yohandev.personalblog.services;

import com.yohandev.personalblog.dtos.image.ImageReqDTO;
import com.yohandev.personalblog.model.ImageModel;
import com.yohandev.personalblog.repositories.ImageRepository;
import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ImageServices extends Services{

    private final ImageRepository imageRepository;

    public ImageServices(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ImageModel saveImage(ImageReqDTO imageReqDTO) throws IOException {
        ImageModel image = new ImageModel();
        
        image.setAlt(imageReqDTO.imageData().getOriginalFilename());
        image.setImageData(imageReqDTO.imageData().getBytes());

        return imageRepository.save(image);
    }

    public ImageModel getImageById(Long id) {
        verifyId(id);
        
        Optional<ImageModel> imageModel = imageRepository.findById(id);

        if (imageModel.isPresent()) {
            return imageModel.get();
        } else {
            throw new EntityNotFoundException("Image not found");
        }
    }
    
    public List<ImageModel> getAllImages(){
        return imageRepository.findAll();
    }

    public ImageModel updateImage(ImageReqDTO imageReqDTO) throws IOException {
        verifyId(imageReqDTO.id());
        
        ImageModel image = imageRepository.findById(imageReqDTO.id())
                .orElseThrow(() -> new RuntimeException("Image not found"));

        image.setAlt(imageReqDTO.imageData().getOriginalFilename());
        image.setImageData(imageReqDTO.imageData().getBytes());

        return imageRepository.save(image);
    }

    public void deleteImage(Long id) {
        verifyId(id);
        
        ImageModel image = imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));

        imageRepository.delete(image);
    }

}

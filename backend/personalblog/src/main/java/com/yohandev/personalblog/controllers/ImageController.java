/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yohandev.personalblog.controllers;

import com.yohandev.personalblog.dtos.image.ImageReqDTO;
import com.yohandev.personalblog.dtos.image.ImageResDTO;
import com.yohandev.personalblog.model.ImageModel;
import com.yohandev.personalblog.services.ImageServices;
import jakarta.persistence.EntityNotFoundException;
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
@RequestMapping("image")
public class ImageController {

    @Autowired
    private ImageServices imageServices;

    @PostMapping
    public ResponseEntity<String> saveImage(@RequestBody ImageReqDTO imageReqDTO) {
        try {
            imageServices.saveImage(imageReqDTO);
            return new ResponseEntity<>("Image saved successfully", HttpStatus.CREATED);
        } catch (IOException ioe) {
            return new ResponseEntity<>(ioe.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error ocurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getImageById(@PathVariable long id) {
        try {
            ImageResDTO imageResDTO = new ImageResDTO(imageServices.getImageById(id));
            return new ResponseEntity<>(imageResDTO, HttpStatus.OK);
        } catch (IllegalArgumentException | EntityNotFoundException iae) {
            HttpStatus status = (iae instanceof EntityNotFoundException) ? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(iae.getMessage(), status);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error ocurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllImages() {
        try {
            List<ImageResDTO> imageResDTOs = ImageResDTO.convertToUserResDTOList(imageServices.getAllImages());
            return new ResponseEntity<>(imageResDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error ocurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping
    public ResponseEntity<String> updateImage(@RequestBody ImageReqDTO imageReqDTO) {
        try {
            imageServices.updateImage(imageReqDTO);
            return new ResponseEntity<>("Image updated successfully", HttpStatus.OK);
        } catch (IllegalArgumentException | IOException iae) {
            return new ResponseEntity<>(iae.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error ocurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable long id) {
        try {
            imageServices.deleteImage(id);
            return new ResponseEntity<>("Image deleted successfully", HttpStatus.OK);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(iae.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error ocurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

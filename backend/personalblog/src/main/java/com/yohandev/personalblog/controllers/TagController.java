/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yohandev.personalblog.controllers;

import com.yohandev.personalblog.dtos.TagDTO;
import com.yohandev.personalblog.services.TagServices;
import jakarta.persistence.EntityNotFoundException;
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
@RequestMapping("tag")
public class TagController {

    @Autowired
    private TagServices tagServices;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody TagDTO tagDTO) {
        try {
            tagServices.save(tagDTO);
            return new ResponseEntity<>("Tag created successfully", HttpStatus.CREATED);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(iae.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error ocurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTagById(@PathVariable long id) {
        try {
            TagDTO tagDTO = new TagDTO(tagServices.get(id));
            return new ResponseEntity<>(tagDTO, HttpStatus.OK);
        } catch (EntityNotFoundException enfe) {
            return new ResponseEntity<>(enfe.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error ocurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping
    public ResponseEntity<?> getAllTags(){
        try {
            List<TagDTO> tagsDTO = TagDTO.convertToTagDTOList(tagServices.getAll());
            return new ResponseEntity<>(tagsDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error ocurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping
    public ResponseEntity<String> update(@RequestBody TagDTO tagDTO){
        try {
            tagServices.update(tagDTO);
            return new ResponseEntity<>("Tag updated successfully", HttpStatus.OK);
        } catch (EntityNotFoundException enfe) {
            return new ResponseEntity<>(enfe.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error ocurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable long id){
        try {
            tagServices.delete(id);
            return new ResponseEntity<>("Tag deleted successfully", HttpStatus.OK);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(iae.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error ocurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

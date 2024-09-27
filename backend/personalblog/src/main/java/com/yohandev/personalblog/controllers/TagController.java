/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yohandev.personalblog.controllers;

import com.yohandev.personalblog.dtos.PostTagDTO;
import com.yohandev.personalblog.dtos.TagDTO;
import com.yohandev.personalblog.services.PostTagServices;
import com.yohandev.personalblog.services.TagServices;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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

    @Autowired
    private PostTagServices postTagServices;
    
    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@Valid @RequestBody TagDTO tagDTO) {
        tagServices.saveTag(tagDTO);
        return new ResponseEntity<>("Tag created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTagById(@PathVariable long id) {
        TagDTO tagDTO = new TagDTO(tagServices.getTagById(id));
        return new ResponseEntity<>(tagDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllTags() {
        List<TagDTO> tagsDTO = TagDTO.convertToTagDTOList(tagServices.getAllTags());
        return new ResponseEntity<>(tagsDTO, HttpStatus.OK);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> update(@Valid @RequestBody TagDTO tagDTO) {
        tagServices.updateTag(tagDTO);
        return new ResponseEntity<>("Tag updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> update(@PathVariable long id) {
        tagServices.deleteTag(id);
        return new ResponseEntity<>("Tag deleted successfully", HttpStatus.OK);
    }
    
    @PostMapping("posttags")
    public ResponseEntity<String> addPostTag(@Valid @RequestBody PostTagDTO postTagDTO) {
        postTagServices.savePostTag(postTagDTO.postId(), postTagDTO.tagId());
        return new ResponseEntity<>("Post Tag relation saved successfully", HttpStatus.OK);
    }
    
    @DeleteMapping("posttags")
    public ResponseEntity<String> removePostTag(@Valid @RequestBody PostTagDTO postTagDTO) {
        postTagServices.deletePostTag(postTagDTO.postId(), postTagDTO.tagId());
        return new ResponseEntity<>("Post Tag relation removed successfully", HttpStatus.OK);
    }
}

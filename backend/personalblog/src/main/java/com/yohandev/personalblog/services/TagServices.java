/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yohandev.personalblog.services;

import com.yohandev.personalblog.dtos.TagDTO;
import com.yohandev.personalblog.model.tag.NameTag;
import com.yohandev.personalblog.model.tag.TagModel;
import com.yohandev.personalblog.repositories.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TagServices {
    
    private final TagRepository tagRepository;
    
    public TagServices(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }
    
    public void save(TagDTO tagDTO) {
        TagModel tagModel = new TagModel();
        tagModel.setId(tagDTO.id());
        tagModel.setName(tagDTO.name());
        if(isNameValid(tagModel)) {
            tagRepository.save(tagModel);
        } else {
            throw new IllegalArgumentException("This tag is not valid. Verify its name");
        }
    }
    
    private boolean isNameValid(TagModel tagModel){
        try {
            NameTag.valueOf(tagModel.getName().toString());
            return true;
        } catch (IllegalArgumentException iae) {
            return false;
        }
    }
    
    public TagModel get(long id) {
        if(id > 0) {
            Optional<TagModel> tagModel = tagRepository.findById(id);
            if(tagModel.isPresent()) {
                return tagModel.get();
            } else {
                throw new EntityNotFoundException("Tag not found");
            }
        } else {
            throw new IllegalArgumentException("This id is not valid");
        }
    }
    
    public List<TagModel> getAll() {
        return tagRepository.findAll();
    }
    
    public TagModel update(TagDTO tagDTO){
        Optional<TagModel> tagModel = tagRepository.findById(tagDTO.id());
        
        if(tagModel.isPresent()) {
            TagModel tagModelExisting = tagModel.get();
            
            tagModelExisting.setName(tagDTO.name());
            
            return tagRepository.save(tagModelExisting);
        } else {
            throw new EntityNotFoundException("Tag not found");
        }
    }
    
    public void delete(long id){
        if(id > 0) {
            tagRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Invalid id");
        }
    }
}

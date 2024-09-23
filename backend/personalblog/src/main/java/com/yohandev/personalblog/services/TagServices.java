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
public class TagServices extends Services{
    
    private final TagRepository tagRepository;
    
    public TagServices(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }
    
    public void saveTag(TagDTO tagDTO) {
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
    
    public TagModel getTagById(long id) {
        verifyId(id);
        
        Optional<TagModel> tag = tagRepository.findById(id);
        
        if (tag.isPresent()) {
            return tag.get();
        } else {
            throw new EntityNotFoundException("Tag not found");
        }
    }
    
    public List<TagModel> getAllTags() {
        return tagRepository.findAll();
    }
    
    public TagModel updateTag(TagDTO tagDTO){
        verifyId(tagDTO.id());
        
        TagModel tag = tagRepository.findById(tagDTO.id())
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        
        return tagRepository.save(tag);
    }
    
    public void deleteTag(long id){
        verifyId(id);
        TagModel tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        tagRepository.deleteById(id);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.yohandev.personalblog.dtos;

import com.yohandev.personalblog.model.NameTag;
import com.yohandev.personalblog.model.TagModel;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Yohan
 */
public record TagDTO(
        long id,
        
        @NotNull(message = "Tag name is required")
        NameTag name) {

    public TagDTO(TagModel tagModel) {
        this(tagModel.getId(), tagModel.getName());
    }
    
    public static List<TagDTO> convertToTagDTOList(List<TagModel> tagModels){
        return tagModels.stream()
                .map(TagDTO::new)
                .collect(Collectors.toList());
    }
}

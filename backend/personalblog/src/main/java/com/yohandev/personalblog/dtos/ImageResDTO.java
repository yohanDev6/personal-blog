/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.yohandev.personalblog.dtos;

import com.yohandev.personalblog.model.ImageModel;
import java.util.List;
import java.util.stream.Collectors;

public record ImageResDTO(
        long id,
        String alt,
        byte[] content,
        long postId) {

    public ImageResDTO(ImageModel image) {
        this(
                image.getId(),
                image.getAlt(),
                image.getContent(),
                image.getPost().getId());
    }
    
    public static List<ImageResDTO> convertToImageResDTOList(List<ImageModel> imageModels) {
        return imageModels.stream()
                .map(ImageResDTO::new)
                .collect(Collectors.toList());
    }
}

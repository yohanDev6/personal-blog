/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.yohandev.personalblog.dtos.image;

import com.yohandev.personalblog.model.ImageModel;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Yohan
 */
public record ImageResDTO(
        long id,
        String alt,
        byte[] imageData) {

    public ImageResDTO(ImageModel imageModel){
        this(imageModel.getId(), imageModel.getAlt(), imageModel.getImageData());
    }
    
    public ImageResDTO convertObjectToDTO(ImageModel imageModel) throws IllegalArgumentException{
        ImageResDTO imageResDTO = new ImageResDTO(imageModel);
        return imageResDTO;
    }
    
    public static List<ImageResDTO> convertToUserResDTOList(List<ImageModel> imageModels) {
        return imageModels.stream()
                .map(ImageResDTO::new)
                .collect(Collectors.toList());
    }
}

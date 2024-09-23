/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.yohandev.personalblog.dtos.image;

import com.yohandev.personalblog.model.ImageModel;
import jakarta.validation.constraints.NotNull;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Yohan
 */
public record ImageReqDTO(
        long id,
        
        @NotNull
        String alt,
        
        @NotNull
        MultipartFile imageData) {

    public ImageModel convertDTOToObject() throws IOException{
        ImageModel imageModel = new ImageModel();
        
        imageModel.setAlt(alt);
        imageModel.setImageData(imageData.getBytes());
        
        return imageModel;
    }
}

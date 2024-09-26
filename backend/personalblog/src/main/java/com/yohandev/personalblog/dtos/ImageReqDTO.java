/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.yohandev.personalblog.dtos;

import com.yohandev.personalblog.model.ImageModel;
import jakarta.validation.constraints.NotNull;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public record ImageReqDTO(
        long id,
        
        @NotNull(message = "Alt is required")
        String alt,
        
        @NotNull(message = "Content is required")
        MultipartFile content,
        
        @NotNull(message = "Post ID is required")
        long postId) {

    public ImageModel convertDTOToObject() throws IOException {
        ImageModel image = new ImageModel();
        image.setAlt(alt);
        image.setContent(content.getBytes());
        return image;
    }
}

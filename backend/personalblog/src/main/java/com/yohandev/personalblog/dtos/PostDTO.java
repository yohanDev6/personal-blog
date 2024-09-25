/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.yohandev.personalblog.dtos;

import com.yohandev.personalblog.model.PostModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record PostDTO(
        long id,
        
        @NotNull(message = "Title is required")
        @Size(min = 10, max = 64, message = "Title must be between 10 and 64 characters")
        String title,
        
        @NotNull(message = "Content is required")
        String content,
        
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        
        @NotNull(message = "User ID is required")
        long userId) {

    public PostDTO(PostModel postModel) {
        this(
                postModel.getId(),
                postModel.getTitle(),
                postModel.getContent(),
                postModel.getCreatedAt(),
                postModel.getUpdatedAt(),
                postModel.getUser().getId());
    }
    
    public PostModel convertDTOToObject() {
        PostModel post = new PostModel();
        
        post.setTitle(title);
        post.setContent(content);
        post.setCreatedAt(createdAt);
        
        return post;
    }
    
    public static List<PostDTO> convertToPostDTOList(List<PostModel> postModels) {
        return postModels.stream()
                .map(PostDTO::new)
                .collect(Collectors.toList());
    }
}

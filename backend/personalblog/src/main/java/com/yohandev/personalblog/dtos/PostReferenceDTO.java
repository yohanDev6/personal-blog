/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.yohandev.personalblog.dtos;

import com.yohandev.personalblog.model.PostReferencesModel;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public record PostReferenceDTO(
        long id,
        
        @NotNull(message = "Content is required")
        String content,
        
        @NotNull(message = "Post ID is required")
        long postId) {

    public PostReferenceDTO(PostReferencesModel postReference) {
        this(
                postReference.getId(),
                postReference.getContent(),
                postReference.getPost().getId());
    }
    
    public PostReferencesModel convertDTOToObject() {
        PostReferencesModel postReference = new PostReferencesModel();
        postReference.setContent(content);
        
        return postReference;
    }
    
    public static List<PostReferenceDTO> convertToPostReferenceList(List<PostReferencesModel> postReferences) {
        return postReferences.stream()
                .map(PostReferenceDTO::new)
                .collect(Collectors.toList());
    }
}

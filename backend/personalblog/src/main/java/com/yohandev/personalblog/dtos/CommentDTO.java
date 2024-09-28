package com.yohandev.personalblog.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.yohandev.personalblog.model.CommentModel;

import jakarta.validation.constraints.NotNull;

public record CommentDTO(
        long id,
        
        @NotNull(message = "Content is required")
        String content,
        
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        
        long parentId,
        List<CommentDTO> replies,
        
        @NotNull(message = "User ID is required")
        long userId,
        
        @NotNull(message = "Post ID is required")
        long postId) {

    public CommentDTO(CommentModel comment) {
        this(
            comment.getId(),
            comment.getContent(),
            comment.getCreatedAt(),
            comment.getUpdatedAt(),
            comment.getParentComment() != null ? comment.getParentComment().getId() : 0,
            convertToCommentDTOList(comment.getReplies()),
            comment.getUser().getId(),
            comment.getPost().getId()
        );
    }

    public static List<CommentDTO> convertToCommentDTOList(List<CommentModel> commentModels) {
        return commentModels.stream()
                .map(CommentDTO::new)
                .collect(Collectors.toList());
    }

    public CommentModel convertDTOToObject() {
        CommentModel comment = new CommentModel();
        comment.setContent(content);
        return comment;
    }
}


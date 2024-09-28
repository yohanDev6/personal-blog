package com.yohandev.personalblog.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record LikeDTO(
		long id,
		LocalDateTime createdAt,
		
		@NotNull(message = "User ID is required")
		long userId,
		
		@NotNull(message = "Post ID is required")
		long postId) {

	
}

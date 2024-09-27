/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.yohandev.personalblog.dtos;

import jakarta.validation.constraints.NotNull;


public record PostTagDTO(
        @NotNull(message = "Post ID is required")
        long postId,
        
        @NotNull(message = "Tag ID is required")
        long tagId) {
}

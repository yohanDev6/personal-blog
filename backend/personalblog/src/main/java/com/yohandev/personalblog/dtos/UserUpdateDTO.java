/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.yohandev.personalblog.dtos;

import com.yohandev.personalblog.model.UserModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Yohan
 */
public record UserUpdateDTO(
        @NotNull
        long id,
        
        @NotNull(message = "Name is required")
        @Size(min = 2, max = 64, message = "Name must be between 2 and 50 characters")
        String name,
        
        @NotNull(message = "Email is required")
        @Email(message = "Email should be valid")
        String email,
        
        boolean isBlocked,
        boolean isVerified,
        boolean isAdmin) {

    public UserModel convertDTOToObject() {
        UserModel userModel = new UserModel();
        
        userModel.setId(this.id);
        userModel.setName(this.name);
        userModel.setEmail(this.email);
        userModel.setIsBlocked(this.isBlocked);
        userModel.setIsVerified(this.isVerified);
        userModel.setIsAdmin(this.isAdmin);
        
        return userModel;
    }
}

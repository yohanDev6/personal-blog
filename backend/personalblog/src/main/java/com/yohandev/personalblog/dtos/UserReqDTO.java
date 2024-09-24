/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.yohandev.personalblog.dtos;

import com.yohandev.personalblog.model.UserModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserReqDTO(

    @NotNull(message = "Name is required")
    @Size(min = 3, max = 64, message = "Name must be between 3 and 64 characters")
    String name,

    @NotNull(message = "Email is required")
    @Email(message = "Email should be valid")
    String email,

    @NotNull(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    String password) {

    public UserModel convertDTOToObject() {
        if (name == "" || email == "" || password == "") {
            throw new IllegalArgumentException("User can not be null");
        }
        
        UserModel userModel = new UserModel();
        userModel.setName(this.name);
        userModel.setEmail(this.email);
        userModel.setPassword(this.password);
        return userModel;
    }
}

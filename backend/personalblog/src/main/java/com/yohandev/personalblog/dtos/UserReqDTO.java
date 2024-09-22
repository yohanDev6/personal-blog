/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.yohandev.personalblog.dtos;

import com.yohandev.personalblog.model.UserModel;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Yohan
 */
public record UserReqDTO(
        @NotNull
        String name,
        @NotNull
        String email,
        @NotNull
        String password) {

    public UserModel convertDTOToObject() {
        UserModel userModel = new UserModel();
        
        userModel.setName(this.name);
        userModel.setEmail(this.email);
        userModel.setPassword(this.password);
        
        return userModel;
    }
    
    
}

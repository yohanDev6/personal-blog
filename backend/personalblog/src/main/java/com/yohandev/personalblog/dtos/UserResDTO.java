/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.yohandev.personalblog.dtos;

import com.yohandev.personalblog.model.UserModel;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Yohan
 */
public record UserResDTO(
        long id,
        String name,
        String email,
        LocalDateTime createdAt,
        boolean isBlocked,
        boolean isVerified,
        boolean isAdmin) {

    public UserResDTO(UserModel userModel) {
        this(
                userModel.getId(),
                userModel.getName(),
                userModel.getEmail(),
                userModel.getCreatedAt(),
                userModel.getIsBlocked(),
                userModel.getIsVerified(),
                userModel.getIsAdmin());
    }

    public static List<UserResDTO> convertToUserResDTOList(List<UserModel> userModels) {
        return userModels.stream()
                .map(UserResDTO::new) // Converte cada UserModel em UserResDTO
                .collect(Collectors.toList());  // Coleta os resultados em uma lista
    }
}

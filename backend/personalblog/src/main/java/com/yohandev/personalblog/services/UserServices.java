/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yohandev.personalblog.services;

import com.yohandev.personalblog.dtos.UserReqDTO;
import com.yohandev.personalblog.dtos.UserUpdateDTO;
import com.yohandev.personalblog.model.UserModel;
import com.yohandev.personalblog.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserServices extends Services {

    private final UserRepository userRepository;

    @Autowired
    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(UserReqDTO userReqDTO) {
        UserModel userModel = userReqDTO.convertDTOToObject();

        // Criptografar a senha
        userModel.setPassword(encryptPassword(userModel.getPassword()));

        // Inserir a data atual
        LocalDateTime now = LocalDateTime.now();
        userModel.setCreatedAt(now);

        userRepository.save(userModel);
    }

    public UserModel getUserById(long id) {
        verifyId(id);
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found"));
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public UserModel updateUser(UserUpdateDTO userUpdateDTO) {
        verifyId(userUpdateDTO.id());

        UserModel existingUser = userRepository.findById(userUpdateDTO.id())
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(userUpdateDTO.name());
        existingUser.setEmail(userUpdateDTO.email());
        existingUser.setIsBlocked(userUpdateDTO.isBlocked());
        existingUser.setIsVerified(userUpdateDTO.isVerified());
        existingUser.setIsAdmin(userUpdateDTO.isAdmin());

        return userRepository.save(existingUser);
    }

    public void deleteUser(long id) {
        verifyId(id);
        
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        userRepository.delete(user);
    }

    private String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}

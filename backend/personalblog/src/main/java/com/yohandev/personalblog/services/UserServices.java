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
public class UserServices {

    private final UserRepository userRepository;

    @Autowired
    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(UserReqDTO userReqDTO) {
        UserModel userModel = userReqDTO.convertDTOToObject();

        // Criptografar a senha
        String password = encrypt(userModel.getPassword());
        userModel.setPassword(password);

        // Inserir a data atual
        LocalDateTime now = LocalDateTime.now();
        userModel.setCreatedAt(now);

        userRepository.save(userModel);
    }

    public UserModel get(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found"));
    }

    public List<UserModel> getAll() {
        return userRepository.findAll();
    }

    public UserModel update(UserUpdateDTO userUpdateDTO) {
        Optional<UserModel> existingUser = userRepository.findById(userUpdateDTO.id());

        if (existingUser.isPresent()) {
            UserModel userToUpdate = existingUser.get();

            userToUpdate.setName(userUpdateDTO.name());
            userToUpdate.setEmail(userUpdateDTO.email());
            userToUpdate.setIsBlocked(userUpdateDTO.isBlocked());
            userToUpdate.setIsVerified(userUpdateDTO.isVerified());
            userToUpdate.setIsAdmin(userUpdateDTO.isAdmin());

            return userRepository.save(userToUpdate);
        } else {
            throw new EntityNotFoundException("User with ID " + userUpdateDTO.id() + " not found.");
        }
    }

    public void delete(long id) {
        if (id > 0) {
            userRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Invalid id");
        }
    }

    private String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yohandev.personalblog.controllers;

import com.yohandev.personalblog.dtos.UserReqDTO;
import com.yohandev.personalblog.dtos.UserResDTO;
import com.yohandev.personalblog.dtos.UserUpdateDTO;
import com.yohandev.personalblog.services.UserServices;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody UserReqDTO userReqDTO) {
        try {
            if (userReqDTO != null) {
                userServices.save(userReqDTO);
                return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("User can not be null", HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<>("Illegal argument: " + iae.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error ocurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id) {
        try {
            UserResDTO userResDTO = new UserResDTO(userServices.get(id));
            return new ResponseEntity<>(userResDTO, HttpStatus.OK);
        } catch (EntityNotFoundException enfe) {
            return new ResponseEntity<>("User not found: " + enfe.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        try {
            List<UserResDTO> users = UserResDTO.convertToUserResDTOList(userServices.getAll());
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error ocurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody UserUpdateDTO userUpdateDTO) {
        try {
            if (userUpdateDTO.id() > 0) {
                userServices.update(userUpdateDTO);
                return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid id", HttpStatus.BAD_REQUEST);
            }
        } catch (EntityNotFoundException enfe) {
            return new ResponseEntity<>("User not found: " + enfe.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        try {
            userServices.delete(id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(iae.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

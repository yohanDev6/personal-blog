/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yohandev.personalblog.controllers;

import com.yohandev.personalblog.dtos.UserReqDTO;
import com.yohandev.personalblog.dtos.UserResDTO;
import com.yohandev.personalblog.model.UserModel;
import com.yohandev.personalblog.services.UserServices;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
}

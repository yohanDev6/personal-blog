/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yohandev.personalblog.controllers;

import com.yohandev.personalblog.dtos.DonationReqDTO;
import com.yohandev.personalblog.dtos.DonationResDTO;
import com.yohandev.personalblog.dtos.UserReqDTO;
import com.yohandev.personalblog.dtos.UserResDTO;
import com.yohandev.personalblog.dtos.UserUpdateDTO;
import com.yohandev.personalblog.services.DonationServices;
import com.yohandev.personalblog.services.UserServices;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private DonationServices donationServices;

    @PostMapping
    @Transactional
    public ResponseEntity<String> saveUser(@Validated @RequestBody UserReqDTO userReqDTO) {
        userServices.saveUser(userReqDTO);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id) {
        UserResDTO userResDTO = new UserResDTO(userServices.getUserById(id));
        return new ResponseEntity<>(userResDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<UserResDTO> users = UserResDTO.convertToUserResDTOList(userServices.getAllUsers());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> updateUser(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        userServices.updateUser(userUpdateDTO);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        userServices.deleteUser(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    //donations
    @PostMapping("{userId}/donations")
    @Transactional
    public ResponseEntity<String> saveDonation(@PathVariable Long userId,@Valid @RequestBody DonationReqDTO donationReqDTO) throws IOException {
        donationServices.saveDonation(userServices.getUserById(userId), donationReqDTO);
        return new ResponseEntity<>("Donation created successfully", HttpStatus.CREATED);
    }
    
    @GetMapping("{userId}/donations")
    public ResponseEntity<?> getDonationsByUser(@PathVariable long userId, @PathVariable long id) {
        List<DonationResDTO> donations = DonationResDTO.convertToDonationResDTOList(donationServices.getDonationsByUser(userId));
        return new ResponseEntity<>(donations, HttpStatus.OK);
    }
    
    @PutMapping("{userId}/donations")
    @Transactional
    public ResponseEntity<String> updateDonation(@PathVariable long userId, @Valid @RequestBody DonationReqDTO donationReqDTO) throws IOException {
        donationServices.updateDonation(userId, donationReqDTO);
        return new ResponseEntity<>("Donation updated successfully", HttpStatus.OK);
    }
    
    @DeleteMapping("{userId}/donations/{id}")
    @Transactional
    public ResponseEntity<String> deleteDonation(@PathVariable long userId, @PathVariable long id) {
        donationServices.deleteDonation(userId, id);
        return new ResponseEntity<>("Donation deleted successfully", HttpStatus.OK);
    }
}

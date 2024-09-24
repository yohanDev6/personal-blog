/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.yohandev.personalblog.dtos;

import com.yohandev.personalblog.model.DonationModel;
import jakarta.validation.constraints.NotNull;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record DonationReqDTO(
        long id,

        @NotNull(message = "Donation value is required")
        @Positive(message = "Donation value must be greater than zero")
        float value,

        @NotNull(message = "Receipt is required")
        @Size(min = 1, max = 1048576, message = "Receipt size must be between 1 byte and 1 MB") // 1MB limit
        MultipartFile receipt,

        @NotNull(message = "User ID is required")
        long userId) {

    public DonationModel convertDTOToObject() throws IOException {
        DonationModel donation = new DonationModel();
        donation.setValue(value);
        donation.setReceipt(receipt.getBytes());
        return donation;
    }
}

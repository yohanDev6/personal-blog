/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.yohandev.personalblog.dtos;

import com.yohandev.personalblog.model.DonationModel;
import java.io.IOException;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.multipart.MultipartFile;

public record DonationReqDTO(
        long id,
        @NotNull(message = "Donation value is required")
        @Positive(message = "Donation value must be greater than zero")
        float value,
        MultipartFile receipt,
        @NotNull(message = "User ID is required")
        long userId) {

    public DonationModel convertDTOToObject() throws IOException {
        DonationModel donation = new DonationModel();

        if (receipt != null) {
            if (receipt.getSize() > 2097152) {  // 2MB em bytes
                throw new IllegalArgumentException("Receipt size must not exceed 2 MB");
            } else if (receipt != null && receipt.getBytes() != null) {
                donation.setReceipt(receipt.getBytes());
            }
        }

        donation.setValue(value);
        return donation;
    }
}

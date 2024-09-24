/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.yohandev.personalblog.dtos;

import com.yohandev.personalblog.model.DonationModel;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Yohan
 */
public record DonationResDTO(
        long id,
        float value,
        LocalDateTime createdAt,
        byte[] receipt,
        long userId) {

    public DonationResDTO(DonationModel donationModel){
        this(
                donationModel.getId(),
                donationModel.getValue(),
                donationModel.getCreatedAt(),
                donationModel.getReceipt(),
                donationModel.getUser().getId());
    }
    
    public static List<DonationResDTO> convertToDonationResDTOList(List<DonationModel> donationModels) {
        return donationModels.stream()
                .map(DonationResDTO::new)
                .collect(Collectors.toList());
    }
}

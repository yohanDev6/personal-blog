/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yohandev.personalblog.services;

import com.yohandev.personalblog.dtos.DonationReqDTO;
import com.yohandev.personalblog.model.DonationModel;
import com.yohandev.personalblog.model.UserModel;
import com.yohandev.personalblog.repositories.DonationRepository;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DonationServices extends Services {
    
    private final DonationRepository donationRepository;
    
    public DonationServices(DonationRepository donationRepository){
        this.donationRepository = donationRepository;
    }
    
    public void saveDonation(UserModel user, DonationReqDTO donationReqDTO) throws IOException {
        DonationModel donation = donationReqDTO.convertDTOToObject();
        donation.setUser(user);
        
        donation.setCreatedAt(setDateTimeNow());
        
        donationRepository.save(donation);
    }
    
    public List<DonationModel> getDonationsByUser(long userId) {
        verifyId(userId);
        return donationRepository.findAll();
    }
    
    public void updateDonation(long userId, DonationReqDTO donation) throws IOException {
        verifyId(donation.id());
        verifyFKRelation(userId, donation.id());
        
        DonationModel existingDonation = donationRepository.findById(donation.id())
                .orElseThrow(() -> new RuntimeException("Donation not found"));
        
        existingDonation.setValue(donation.value());
        existingDonation.setReceipt(donation.receipt().getBytes());
        
        donationRepository.save(existingDonation);
    }
    
    public void deleteDonation(long userId, long id) {
        verifyId(id);
        verifyFKRelation(userId, id);
        
        DonationModel donation = donationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donation not found"));
        
        donationRepository.delete(donation);
    }
}

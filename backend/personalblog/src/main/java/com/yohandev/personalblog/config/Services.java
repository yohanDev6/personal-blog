/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yohandev.personalblog.config;

import com.yohandev.personalblog.repositories.DonationRepository;
import com.yohandev.personalblog.repositories.TagRepository;
import com.yohandev.personalblog.repositories.UserRepository;
import com.yohandev.personalblog.services.DonationServices;
import com.yohandev.personalblog.services.TagServices;
import com.yohandev.personalblog.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Services {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;
    
    @Autowired
    private DonationRepository donationRepository;
    
    @Bean
    public UserServices userServices() {
        return new UserServices(userRepository);
    }
    
    @Bean
    public TagServices tagServices() {
        return new TagServices(tagRepository);
    }
    
    @Bean
    public DonationServices donationServices() {
        return new DonationServices(donationRepository);
    }
}

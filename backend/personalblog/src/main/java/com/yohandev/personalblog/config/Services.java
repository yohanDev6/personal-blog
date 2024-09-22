/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yohandev.personalblog.config;

import com.yohandev.personalblog.repositories.UserRepository;
import com.yohandev.personalblog.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Services {
    @Autowired
    private UserRepository userRepository;

    @Bean
    public UserServices userServices() {
        return new UserServices(userRepository);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yohandev.personalblog.services;

import java.time.LocalDateTime;

/**
 *
 * @author Yohan
 */
public abstract class Services {
    protected void verifyId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException(id + " is an invalid ID");
        }
    }
    
    protected LocalDateTime setDateTimeNow() {
        return LocalDateTime.now();
    }
    
    protected void verifyUserReference(long userId, long id) {
        if (id != userId) {
            throw new IllegalArgumentException("This entity does not pertence to this user");
        }
    }
}

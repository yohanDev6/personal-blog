/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yohandev.personalblog.services;

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
}

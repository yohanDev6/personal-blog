/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yohandev.personalblog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "image")
public class ImageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String alt;

    @Column(name = "content")
    @Lob
    private byte[] imageData;

    public ImageModel() {

    }

    public ImageModel(long id, String alt, byte[] imageData) {
        this.id = id;
        this.alt = alt;
        this.imageData = imageData;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public double getTamanhoEmMB() {
        if (this.imageData != null) {
            double tamanhoEmBytes = this.imageData.length;
            // Converter de bytes para megabytes
            double tamanhoEmMB = tamanhoEmBytes / (1024 * 1024);
            return tamanhoEmMB;
        } else {
            return 0.0; // Se não houver imagem, retorna 0
        }
    }

}

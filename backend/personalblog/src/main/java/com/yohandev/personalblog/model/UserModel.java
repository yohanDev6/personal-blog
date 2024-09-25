/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yohandev.personalblog.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
public class UserModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private LocalDateTime createdAt;
    
    private String name, email, password;
    
    private boolean isBlocked, isVerified, isAdmin;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DonationModel> donations = new ArrayList<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostModel> posts = new ArrayList<>();
    
    public UserModel(){
        
    }

    public UserModel(long id, LocalDateTime createdAt, String name, String email, String password, boolean isBlocked, boolean isVerified, boolean isAdmin) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isBlocked = isBlocked;
        this.isVerified = isVerified;
        this.isAdmin = isAdmin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<DonationModel> getDonations() {
        return donations;
    }

    public void setDonations(List<DonationModel> donations) {
        this.donations = donations;
    }
    
    public void addDonation(DonationModel donation) {
        donations.add(donation);
        donation.setUser(this);
    }

    public void removeDonation(DonationModel donation) {
        donations.remove(donation);
        donation.setUser(null);
    }

    public List<PostModel> getPosts() {
        return posts;
    }

    public void setPosts(List<PostModel> posts) {
        this.posts = posts;
    }
    
    public void addPost(PostModel post) {
        posts.add(post);
        post.setUser(this);
    }

    public void removeDonation(PostModel post) {
        donations.remove(post);
        post.setUser(null);
    }
}
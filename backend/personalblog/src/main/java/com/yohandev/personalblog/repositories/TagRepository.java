/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.yohandev.personalblog.repositories;

import com.yohandev.personalblog.model.TagModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Yohan
 */
public interface TagRepository extends JpaRepository<TagModel, Long>{
    
}

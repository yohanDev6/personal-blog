/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.yohandev.personalblog.repositories;

import com.yohandev.personalblog.model.PostModel;
import com.yohandev.personalblog.model.PostReferencesModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostReferenceRepository extends JpaRepository<PostReferencesModel, Long>{
    
    List<PostReferencesModel> findByPost(PostModel post);
}

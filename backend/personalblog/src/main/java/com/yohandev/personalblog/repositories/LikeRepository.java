package com.yohandev.personalblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yohandev.personalblog.model.LikeModel;
import com.yohandev.personalblog.model.PostModel;
import com.yohandev.personalblog.model.UserModel;

public interface LikeRepository extends JpaRepository<LikeModel, Long>{

	long countByUser(UserModel user);
	long countByPost(PostModel post);
	long countByPostAndUser(PostModel post, UserModel user);
}

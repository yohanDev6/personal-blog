package com.yohandev.personalblog.services;

import org.springframework.stereotype.Service;

import com.yohandev.personalblog.model.LikeModel;
import com.yohandev.personalblog.model.PostModel;
import com.yohandev.personalblog.model.UserModel;
import com.yohandev.personalblog.repositories.LikeRepository;

@Service
public class LikeServices extends Services{

	private final LikeRepository likeRepository;
	
	public LikeServices(LikeRepository likeRepository) {
		this.likeRepository = likeRepository;
	}
	
	public void saveLike(UserModel user, PostModel post) {
		LikeModel like = new LikeModel();
		
		if(likeAlreadyExists(post, user)) {
			throw new IllegalStateException("User has already liked this post.");
		} else {
			like.setUser(user);
			like.setPost(post);
			like.setCreatedAt(setDateTimeNow());
			
			likeRepository.save(like);
		}
	}
	
	public long countLikesByPost(PostModel post) {
		return likeRepository.countByPost(post);
	}
	
	public long countLikesByUser(UserModel user) {
		return likeRepository.countByUser(user);
	}
	
	private boolean likeAlreadyExists(PostModel post, UserModel user) {
		return likeRepository.countByPostAndUser(post, user) > 0;
	}
	
	public void removeLike(long id, UserModel user, PostModel post) {
		verifyId(id);
		
		LikeModel like = likeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Like not found"));
		
		verifyFKRelation(like.getUser().getId(), user.getId());
		verifyFKRelation(like.getPost().getId(), post.getId());
		
		likeRepository.delete(like);
	}
}

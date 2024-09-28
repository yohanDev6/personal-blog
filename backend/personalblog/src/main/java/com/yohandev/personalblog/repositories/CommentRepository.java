package com.yohandev.personalblog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yohandev.personalblog.model.CommentModel;
import com.yohandev.personalblog.model.PostModel;

public interface CommentRepository extends JpaRepository<CommentModel, Long>{

    List<CommentModel> findByParentComment_Id(long parentId);

    List<CommentModel> findByPostAndParentCommentIsNull(PostModel post);
}

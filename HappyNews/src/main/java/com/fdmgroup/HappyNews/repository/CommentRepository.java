package com.fdmgroup.HappyNews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.HappyNews.model.Comment;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	
	
	
}

package com.fdmgroup.HappyNews.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fdmgroup.HappyNews.model.Article;
import com.fdmgroup.HappyNews.model.Comment;
import com.fdmgroup.HappyNews.repository.CommentRepository;

@Service
public class CommentService {

	CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		super();
		this.commentRepository = commentRepository;
	}
	
	public void saveComment(Comment comment) {
		commentRepository.save(comment);
		
	}
	
	public void deleteComment (int commentId) {
		commentRepository.deleteById(commentId);
		
	}
	
	public List <Comment> listOfCommentsForArticle(Article article){
		return commentRepository.findByArticle(article);
		
		
		
	}

	public Comment findCommentById(Integer id) {
	    return commentRepository.findById(id).orElse(null);
	}
	
}

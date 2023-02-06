package com.fdmgroup.HappyNews.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {

	@Id
	@GeneratedValue
	@Column(name="comment_ID")
	private int commentId;
	
	@ManyToOne
	HappyUser commentator;
	
	@ManyToOne
	Article article;
	
	private LocalDate publicationDate;
	
	private String commentText;

	
	public Comment() {
		super();
	
	}


	public Comment(HappyUser commentator, Article article, LocalDate publicationDate, String commentText) {
		super();
		this.commentator = commentator;
		this.article = article;
		this.publicationDate = publicationDate;
		this.commentText = commentText;
	}


	public int getCommentId() {
		return commentId;
	}



	public HappyUser getCommentator() {
		return commentator;
	}


	public void setCommentator(HappyUser commentator) {
		this.commentator = commentator;
	}


	public Article getArticle() {
		return article;
	}


	public void setArticle(Article article) {
		this.article = article;
	}


	public LocalDate getPublicationDate() {
		return publicationDate;
	}


	public void setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
	}


	public String getCommentText() {
		return commentText;
	}


	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}


	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", commentator=" + commentator + ", article=" + article
				+ ", publicationDate=" + publicationDate + ", commentText=" + commentText + "]";
	}
	
	

	
	
	
	
}

package com.fdmgroup.HappyNews.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Article {

	@Id
	@GeneratedValue
	@Column(name="article_ID")
	private int articleId;
	
	
	private String title;
	
	private String articleText;
	
	private LocalDate publicationDate;
	
	private String location;
	
	
	//user or admin?
	@ManyToOne
	HappyUser author;
	
	private boolean status;
	
	private String category;

	
	
	public Article() {
		
	}


	public Article(String title, String articleText, LocalDate date, String location, HappyUser happyUser, boolean status,
			String category) {
		super();
		this.title = title;
		this.articleText = articleText;
		this.publicationDate = date;
		this.location = location;
		this.author = happyUser;
		this.status = status;
		this.category = category;
	}
	
	
	
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return articleText;
	}

	public void setText(String text) {
		this.articleText = text;
	}

	public LocalDate getDate() {
		return publicationDate;
	}

	public void setDate(LocalDate date) {
		this.publicationDate = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public HappyUser getUser() {
		return author;
	}

	public void setUser(HappyUser user) {
		this.author = user;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getArticleId() {
		return articleId;
	}


	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", title=" + title + ", articleText=" + articleText
				+ ", publicationDate=" + publicationDate + ", location=" + location + ", author=" + author + ", status="
				+ status + ", category=" + category + "]";
	}

	
	
}

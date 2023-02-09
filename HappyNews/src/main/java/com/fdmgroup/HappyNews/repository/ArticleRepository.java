package com.fdmgroup.HappyNews.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fdmgroup.HappyNews.model.Article;
@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

	 @Query("SELECT a FROM Article a ORDER BY a.publicationDate DESC")
	  List<Article> findLatestArticles();
	}



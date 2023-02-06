package com.fdmgroup.HappyNews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.HappyNews.model.Article;
@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

}

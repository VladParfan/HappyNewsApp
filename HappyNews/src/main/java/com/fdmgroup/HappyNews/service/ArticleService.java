package com.fdmgroup.HappyNews.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.HappyNews.model.Article;
import com.fdmgroup.HappyNews.repository.ArticleRepository;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	
	
	public void saveArticle (Article article) {
		articleRepository.save(article);
}
	
	
	
	
	public Article findByArticleId(Integer articleId) {
		Optional <Article> articleOpt = articleRepository.findById(articleId);
		return articleOpt.orElse(null);
	}
	
	
}
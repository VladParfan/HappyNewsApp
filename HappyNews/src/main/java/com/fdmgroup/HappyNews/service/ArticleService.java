package com.fdmgroup.HappyNews.service;
import java.util.List;
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
	
	public void deleteArticle (Integer articleId) {
		articleRepository.deleteById(articleId);
	}
	
	
	public Article findByArticleId(Integer articleId) {
		Optional <Article> articleOpt = articleRepository.findById(articleId);
		return articleOpt.orElse(null);
	}
	
	public List<Article> listLatestSixArticles() {
	    List<Article> articles = articleRepository.findLatestArticles();
	    int size = articles.size();
	    if (size > 6) {
	      return articles.subList(0, 6);
	    }
	    return articles;
	  }
	
	
}
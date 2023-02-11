package com.fdmgroup.HappyNews.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.HappyNews.model.Article;
import com.fdmgroup.HappyNews.repository.ArticleRepository;

@Service
public class CategoryService {
	
	
	@Autowired
	private final ArticleRepository articleRepository;

	public CategoryService(ArticleRepository articleRepository) {
		super();
		this.articleRepository = articleRepository;
	}


	public List<Article> findArticleByCategory(String category) {
			List<Article> findByCategory = articleRepository.findByCategoryIgnoreCase(category);
		return findByCategory;
	}
}

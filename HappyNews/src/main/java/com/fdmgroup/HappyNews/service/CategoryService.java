package com.fdmgroup.HappyNews.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.fdmgroup.HappyNews.model.Article;
import com.fdmgroup.HappyNews.model.Category;
import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.repository.ArticleRepository;
import com.fdmgroup.HappyNews.repository.CategoryRepository;

@Service
public class CategoryService {
	
	
	@Autowired
	private final ArticleRepository articleRepository;
	
	@Autowired
	private final CategoryRepository categoryRepo;

	public CategoryService(ArticleRepository articleRepository,CategoryRepository categoryRepo) {
		super();
		this.articleRepository = articleRepository;
		this.categoryRepo = categoryRepo;
	}


	public List<Article> findArticleByCategory(String category) {
			List<Article> findByCategory = articleRepository.findByCategoryIgnoreCase(category);
		return findByCategory;
	}
	
	
	
	
	public List<Category> findAllCategoriesByUser(HappyUser user ){
		return categoryRepo.findAllCategoriesByUser(user);
	}
	
	public void createAndSaveNewCategory(String name, HappyUser user,ModelMap model) {
		if(checkingIsTheCategoryWithThisNameForThisUserExists(name, user)) {
			model.addAttribute("errorMessage", "you have this category added");
			System.out.println("=-------------------you have this category added");
		}
		Category category = new Category(name, user);
		categoryRepo.save(category);
	}
	
	public void deleteCategory(String categoryName, HappyUser user, ModelMap model) {
		if(!checkingIsTheCategoryWithThisNameForThisUserExists(categoryName, user)) {
			model.addAttribute("errorMessage", "this category not exist in your account");
		}
		List<Category> userCategories = findAllCategoriesByUser(user);
		for(Category category: userCategories) {
			if(category.getName().equals(categoryName)) {
				categoryRepo.deleteById(category.getCategoryId());
			}
		}
		}
	
		
		public boolean checkingIsTheCategoryWithThisNameForThisUserExists(String name, HappyUser user) {
			List<Category> userCategories = categoryRepo.findAllCategoriesByUser(user);
			for(Category category:userCategories) {
				if(category.getName().equals(name)) {
					return true;
				}else {
					return false;
				}
			}
			return false;
		}
		
	}
	


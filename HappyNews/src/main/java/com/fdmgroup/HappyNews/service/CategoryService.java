package com.fdmgroup.HappyNews.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.fdmgroup.HappyNews.controller.MainController;
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
	
	@Autowired 
	private final HappyUserDetailsService happyUserDetailsService;

	public CategoryService(ArticleRepository articleRepository,CategoryRepository categoryRepo,HappyUserDetailsService happyUserDetailsService) {
		super();
		this.articleRepository = articleRepository;
		this.categoryRepo = categoryRepo;
		this.happyUserDetailsService = happyUserDetailsService;
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
		}else{
			Category category = new Category(name, user);
			categoryRepo.save(category);	
		}
		
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
			System.out.println(" --------------------list " + userCategories);
			for(Category category:userCategories) {
				System.out.println("===============================================");
				System.out.println(category.getName());
				System.out.println("===============================================");
				if(category.getName().toLowerCase().equals(name.toLowerCase())) {
					return true;
				}
			}
			
			return false;
		}
		
		
		public Set<Article> recommendedArticles(ModelMap model){
			List<Article> allArticles= articleRepository.findAll();
			List<Category> currentUserCategories = findAllCategoriesByUser(currentUserObject(model));
			Set<Article> articlesByUserCategories = new HashSet<>();
			
			for(Article article: allArticles) {
				for(Category category: currentUserCategories) {
					if(article.getCategory().toLowerCase().equals(category.getName().toLowerCase())){
						articlesByUserCategories.add(article);
				}
			}
			
			}
			
			return articlesByUserCategories;
		}
		
		public HappyUser currentUserObject(ModelMap model) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();	
				HappyUser user = happyUserDetailsService.findUserByName(username);
				return user;
			
			
		}
	}
	


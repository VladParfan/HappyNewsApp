package com.fdmgroup.HappyNews.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fdmgroup.HappyNews.model.Article;
import com.fdmgroup.HappyNews.service.CategoryService;



@Controller
public class CategoryController {
	@Autowired
	private final MainController mainController;
	
	@Autowired
	private final CategoryService categoryService;
	
	
	
	public CategoryController(MainController mainController, CategoryService categoryService) {
		super();
		this.mainController = mainController;
		this.categoryService = categoryService;
	}



	@GetMapping("/{category}")
	public String goToCategory(ModelMap model,@PathVariable String category) {
		mainController.returnUserFromCurrentSession(model);
		
		List<Article> listOfArticles = categoryService.findArticleByCategory(category);
		
		model.addAttribute("listOfArticles", listOfArticles);
		model.addAttribute("category", category);
		
		

		
		
		
		return "showArticleFromCategory";
	}

}

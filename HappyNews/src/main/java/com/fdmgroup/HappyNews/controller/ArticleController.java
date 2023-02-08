package com.fdmgroup.HappyNews.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.HappyNews.model.Article;
import com.fdmgroup.HappyNews.model.Comment;
import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.repository.ArticleRepository;
import com.fdmgroup.HappyNews.security.HappyUserDetails;
import com.fdmgroup.HappyNews.service.ArticleService;
import com.fdmgroup.HappyNews.service.CommentService;
import com.fdmgroup.HappyNews.service.HappyUserDetailsService;

@Controller
public class ArticleController {

	@Autowired
	ArticleRepository articleRepository;

	@Autowired
	ArticleService articleService;

	@Autowired
	MainController maincontroller;

	@Autowired
	HappyUserDetailsService happyUserService;
	
	@Autowired
	
	CommentService commentService;

	@GetMapping(value = "/goToArticlePage/{articleId}")
	public String goToArticlePage(ModelMap model, @PathVariable int articleId) {
		maincontroller.returnUserFromCurrentSession(model);

		Article article = articleService.findByArticleId(articleId);
		
		List<Comment> listOfCommentsOfArticle = commentService.listOfCommentsForArticle(article);
		model.addAttribute("article", article);
		model.addAttribute("listOfCommentsOfArticle", listOfCommentsOfArticle);
		return "article";

	}

	@GetMapping(value = "/goToAddArticle")
	public String goToAddArticle(ModelMap model) {
		maincontroller.returnUserFromCurrentSession(model);

		return "addArticle";
	}

	@PostMapping("/addArticle")
	public String addArticle(ModelMap model, @RequestParam String title, @RequestParam String articleText,
			@RequestParam String location, @RequestParam String author, @RequestParam String category) {
		maincontroller.returnUserFromCurrentSession(model);
		HappyUser user = happyUserService.findUserByName(author);

		LocalDate publicationDate = LocalDate.now();
		Article newArticle = new Article(title, articleText, publicationDate, location, user, category);
		articleService.saveArticle(newArticle);

		return "article-confirmation";
	}

	@GetMapping(value = "/goToEditArticle/{articleId}")
	public String goToEditArticle(ModelMap model, @PathVariable int articleId) {
		maincontroller.returnUserFromCurrentSession(model);
		Article article = articleService.findByArticleId(articleId);
		model.addAttribute("article", article);

		return "editArticle";

	}

	@PostMapping("/editArticle")
	public String editProduct(ModelMap model, @RequestParam String title, @RequestParam String articleText,
			@RequestParam String location, @RequestParam String author, @RequestParam String category, @RequestParam Integer articleId) {

		maincontroller.returnUserFromCurrentSession(model);
		Article article = articleService.findByArticleId(articleId);
		
		article.setTitle(title);
		article.setArticleText(articleText);
		article.setLocation(location);
		article.setCategory(category);
		
		articleService.saveArticle(article);
		model.addAttribute("article", article);
		
		return "article";

	}

	@GetMapping("/deleteArticle")
	public String deleteArticle (ModelMap model, @RequestParam Integer articleId) {
		maincontroller.returnUserFromCurrentSession(model);
		Article article = articleService.findByArticleId(articleId);
		articleService.deleteArticle(article);
		
		return "index";
	}
	
}
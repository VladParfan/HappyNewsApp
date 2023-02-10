package com.fdmgroup.HappyNews.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.fdmgroup.HappyNews.model.Article;
import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.repository.ArticleRepository;
import com.fdmgroup.HappyNews.repository.HappyUserRepository;
import com.fdmgroup.HappyNews.util.Filters;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private HappyUserRepository happyUserRepository;
	
	
	public List<Article> findArticlesByName(String name) {
		
		
		List<Article> foundArticles = articleRepository.findByTitleIgnoreCaseStartingWith(name);
		
		List<Article> foundArticles2 = articleRepository.findByTitleIgnoreCaseEndingWith(name);
		
		List<Article> foundArticles3 = articleRepository.findByTitleIgnoreCaseContaining(" " + name+ " ");
		
		foundArticles.addAll(foundArticles2);
		foundArticles.addAll(foundArticles3);
		//List<Article>toReturn = foundArticles.stream().distinct().collect(Collectors.toList());

		//return foundArticles.stream().distinct().collect(Collectors.toList());
		return foundArticles;
		}
	
	public List<Article> findArticleByCategory(List<Article> articles, String category) {
		if (!category.equals("No filter")) {
			List<Article> findByCategory = articleRepository.findByCategoryIgnoreCase(category);
			List<Article> result =  joinTwoLists(articles, findByCategory);
			return result;
		}
		return articles;
	}
	
	
	public List<Article> findArticleByLocation(List<Article> articles, String location) {
		if (!location.equals("No filter")) {
			List<Article> findByCategory = articleRepository.findByLocationIgnoreCase(location);
			List<Article> result =  joinTwoLists(articles, findByCategory);
			return result;
		}
		return articles;
	}
	
	public List<Article> findArticleByAuthorName(List<Article> articles,String name, ModelMap model){
		if(name.equals("No filter")) {
			return articles;
		}
		
		
		Optional<HappyUser> optional = happyUserRepository.findByUsernameIgnoreCase(name);
		
		if(!optional.isPresent()) {
			String errorAuthor = "author not found";
			model.addAttribute("errorAuthor",errorAuthor);
			return articles;
		} 
		
		if (!name.equals("No filter")) {
			HappyUser author = happyUserRepository.findByUsernameIgnoreCase(name).get();
			List<Article> findByCategory = articleRepository.findByAuthor(author);
			
			List<Article> result =  joinTwoLists(articles, findByCategory);
			
			return result;
		}
		
		
		
		
		return articles;
	}
	
	
	public List<Article> joinTwoLists(List<Article> l1, List<Article> l2){

		Set<Article> result = l1.stream()
				  .distinct()
				  .filter(l2::contains)
				  .collect(Collectors.toSet());
		return result.stream().collect(Collectors.toList());

	}
	
	
	
	
	
public List<Article> filterResults(List<Article> articleList, Filters filters, ModelMap model) {
		
		List<Article>filteredArticles = articleList;
		System.out.println("------------------ location");
		filteredArticles = findArticleByLocation(filteredArticles, filters.getLocation());
		System.out.println("------------------ category");
		filteredArticles = findArticleByCategory(filteredArticles, filters.getCategory());
        System.out.println("------------------ author");
		filteredArticles = findArticleByAuthorName(filteredArticles, filters.getAuthor(), model);
		
       for(Article articles: filteredArticles) {
    	   System.out.println(articles.toString());
       }
		
		return filteredArticles;
	}
	
	
public List<Article> subtractList(List<Article> l1, List<Article> l2){
		
		l1.removeAll(l2);
		return l1;
	}
	
	
	public List<Article> findPostedarticles(List<Article> allArticles){
		List<Article> postedArticles= new ArrayList<>();
		for(Article article: allArticles) {
			if (article.isStatus()) {
				postedArticles.add(article);
			}
		}
		return postedArticles;
		
	}
	
	
	
	
	
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
	
	public Optional<Article> findOptionalByArticleId(Integer articleId) {
		Optional <Article> articleOpt = articleRepository.findById(articleId);
		return articleOpt;
	}
	
	public List<Article> findALlProducts(){
		return articleRepository.findAll();
	}
	
	public List<Article> listTopSixArticles() {
	List<Article> TopArticles = articleRepository.findTopSixByOrderByNumberOfCommentsDesc();
	return TopArticles.subList(0, Math.min(TopArticles.size(), 6));
	}
	
	
	/* public List<Article> listTopSixArticlesStream() { 
	 * return* articleRepository.findTopSixByOrderByNumberOfCommentsDesc().stream()* .limit(6) .collect(Collectors.toList()); 
	 * }*/
	
	
	
}
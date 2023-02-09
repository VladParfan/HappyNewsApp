package com.fdmgroup.HappyNews.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import com.fdmgroup.HappyNews.HappyNewsApplication;
import com.fdmgroup.HappyNews.controller.RequestController;
import com.fdmgroup.HappyNews.model.Article;
import com.fdmgroup.HappyNews.model.Comment;
import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.model.Request;
import com.fdmgroup.HappyNews.repository.ArticleRepository;
import com.fdmgroup.HappyNews.service.ArticleService;
import com.fdmgroup.HappyNews.service.CommentService;
import com.fdmgroup.HappyNews.service.HappyUserDetailsService;

@SpringBootTest(classes = { ArticleService.class })
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = HappyNewsApplication.class)
public class ArticleServiceTest {

	
	@InjectMocks
	private ArticleService mockArticleService;
	
	@MockBean
	ArticleRepository mockArticleRepository;

	@MockBean
	HappyUserDetailsService mockhappyUserService;

	@MockBean
	private RequestController mockRequestController;

	
	@MockBean
	private CommentService mockCommentService;

	@Mock
	Article mockArticle;
	
	@Mock
	Request mockRequest;
	
	@Mock
	HappyUser happyUser;
	
	@Test
	public void test_saveArticlemethod_callsSavemethodOfArticleRepository() {
		
		mockArticleService.saveArticle(mockArticle);
		verify(mockArticleRepository, times(1)).save(mockArticle);
		
	}

	@Test
	public void test_deleteArticle_method_callsDeleteByIdArticleRepositoryMethod() {
		
		mockArticleService.deleteArticle(mockArticle.getArticleId());
		verify(mockArticleRepository, times(1)).deleteById(mockArticle.getArticleId());
		
	}
	
	@Test
	public void test_findByArticleId_method_callsDeleteByIdArticleRepositoryMethod() {
		
		mockArticleService.findByArticleId(mockArticle.getArticleId());
		verify(mockArticleRepository, times(1)).findById(mockArticle.getArticleId());
		
	}
	
	
}

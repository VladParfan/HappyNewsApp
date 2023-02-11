package com.fdmgroup.HappyNews.controller;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.repository.HappyUserRepository;
import com.fdmgroup.HappyNews.service.HappyUserDetailsService;

public class HappyUserControllerTest {

	@MockBean	
	MainController mockMainController;

	@MockBean
	HappyUserDetailsService mockhappyUserService;
	
	@MockBean	
	
	@Mock
	HappyUser happyUser;
	private HappyUserRepository userRepository;
	
	
	
	@Autowired
	private MockMvc mockMvc;
	
	
}

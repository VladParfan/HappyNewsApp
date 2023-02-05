package com.fdmgroup.HappyNews.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.security.HappyUserDetails;

@Controller
public class ArticleController {
	
	@GetMapping("/hello")
   public String sayHello() {
	   return "hello";
   }
	
	@GetMapping("/showUserInfo")
	public String showUserInfo() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		HappyUserDetails happyUserDetails = (HappyUserDetails)authentication.getPrincipal();
		
		System.out.println("Current Session user" + happyUserDetails.getHappyUser());
		
		return "hello";
		
	}
	
}

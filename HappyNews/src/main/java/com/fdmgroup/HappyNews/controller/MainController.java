package com.fdmgroup.HappyNews.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.security.HappyUserDetails;

@Controller
public class MainController {

	
	/*
	 * @GetMapping("/login") public String loginPage() { return "login"; }
	 */
	
	
	@GetMapping("/hello")
	public String sayHello() {
		return "hello";
	}

	@GetMapping("/showUserInfo")
	public String showUserInfo() {

		System.out.println("Current Session user" + returnUserFromCurrentSession());

		return "login";

	}

	@GetMapping(value= "/")
	public String getIndex(ModelMap model) {
		return "index";
	}
	
	//--------------- below: from footer -----------------
	
	@GetMapping("/toAboutHappyNews")
	public String toAboutShazar(ModelMap model) {
		
		return "aboutHappyNews";
	}
	
	
	@GetMapping("/goToFAQ")
	public String toFAQ(ModelMap model) {
		
		return "FAQ";
	}
	
	@GetMapping("/toContact")
	public String toContactUs(ModelMap model) {
		
		return "contact";
	}
	
	@GetMapping("/toPrivacyPolicy")
	public String toPolicy(ModelMap model) {
		
		return "privacy";
	}
	
	@GetMapping("/toTermsAndConditions")
	public String toTermsAndConditions(ModelMap model) {
		
		return "termsCondition";
	}
	//--------------- end of footer -----------------
	
	public HappyUser returnUserFromCurrentSession() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		HappyUserDetails happyUserDetails = (HappyUserDetails) authentication.getPrincipal();
		return happyUserDetails.getHappyUser();
	}
	
}

package com.fdmgroup.HappyNews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.security.HappyUserDetails;
import com.fdmgroup.HappyNews.service.HappyUserDetailsService;

@Controller
public class MainController {

	@Autowired
	private final HappyUserDetailsService happyUserDetailsService;
	
	
	public MainController(HappyUserDetailsService happyUserDetailsService) {
		super();
		this.happyUserDetailsService = happyUserDetailsService;
	}

	/*
	 * @GetMapping("/login") public String loginPage() { return "login"; }
	 */
	
	
	@GetMapping("/hello")
	public String sayHello() {
		return "hello";
	}

	@GetMapping("/showUserInfo")
	public String showUserInfo(ModelMap model) {

		returnUserFromCurrentSession(model);

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
	
	public void returnUserFromCurrentSession(ModelMap model) {
		//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//HappyUserDetails happyUserDetails = (HappyUserDetails) authentication.getPrincipal();
		//return happyUserDetails.getHappyUser();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();	
		System.out.println("Username: " + username);	
		if(username.equals("anonymousUser")) {
			model.addAttribute("loggedIn", false);
		} else {
			model.addAttribute("loggedIn", true);
			HappyUser user = happyUserDetailsService.findUserByName(username);
			model.addAttribute("user", user);
		}
	}
	
}

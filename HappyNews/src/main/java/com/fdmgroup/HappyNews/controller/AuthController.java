package com.fdmgroup.HappyNews.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.security.HappyUserDetails;
import com.fdmgroup.HappyNews.service.HappyUserDetailsService;
import com.fdmgroup.HappyNews.service.RegistrationService;
import com.fdmgroup.HappyNews.util.HappyUserValidator;


@Controller
@RequestMapping("/auth")
public class AuthController {
			private final HappyUserValidator happyUserValidator;
			private final  RegistrationService registrationService;
			private final HappyUserDetailsService userDetailsService;
			
		@Autowired	
	public AuthController(HappyUserValidator happyUserValidator, RegistrationService registrationService,HappyUserDetailsService userDetailsService) {
				super();
				this.happyUserValidator = happyUserValidator;
				this.registrationService = registrationService;
				this.userDetailsService = userDetailsService;
			}


	@GetMapping("/login")
	public String loginPage() {
		return "auth/login";
	}
	
	
	@GetMapping("/registration")
	public String registrationPage(@ModelAttribute("happyUser") HappyUser happyUser) {
		return "auth/registration";
	}
	
	@PostMapping("/registration")
	public String performRegistration(@ModelAttribute("happyUser") @Valid HappyUser happyUser, BindingResult bindingResult) {
	 happyUserValidator.validate(happyUser, bindingResult);
	 
	 if(bindingResult.hasErrors()) {
		 return "/auth/registration";
	 }
	 registrationService.register(happyUser);
	 
	 return "redirect:/auth/login";
	 
	}
	
	@GetMapping("/admin")
	public String adminPage()
	{
		return "auth/admin";
	}
	
	@GetMapping("/change-password")
	public String changePasswordPage(@ModelAttribute("happyUser") HappyUser happyUser) {
			
			return "auth/changePassword";
		}
		
	@PostMapping("/change-password")
	public String changePassword(@RequestParam("email") String email,@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
			Integer hashedOldPassword = oldPassword.hashCode();
			String hashedOldPasswordString = hashedOldPassword.toString();
			UserDetails userDetails = userDetailsService.loadUserByEmailForPasswordChange(email);
			HappyUserDetails happyUserDetails1 = (HappyUserDetails) userDetails;
			if(userDetails.getPassword().equals(hashedOldPasswordString)) {
			    happyUserDetails1.setPassword(newPassword);
			    userDetails = (UserDetails) happyUserDetails1;
			    userDetailsService.saveUserToDb(happyUserDetails1);
			    
			}else {
				System.out.println("password does not match");
			}
			return "redirect:/auth/login";	
	}
}

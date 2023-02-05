package com.fdmgroup.HappyNews.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.service.RegistrationService;
import com.fdmgroup.HappyNews.util.HappyUserValidator;

@Controller
@RequestMapping("/auth")
public class AuthController {
			private final HappyUserValidator happyUserValidator;
			private final  RegistrationService registrationService;
			
		@Autowired	
	public AuthController(HappyUserValidator happyUserValidator, RegistrationService registrationService) {
				super();
				this.happyUserValidator = happyUserValidator;
				this.registrationService = registrationService;
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
}

package com.fdmgroup.HappyNews.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.repository.HappyUserRepository;
import com.fdmgroup.HappyNews.security.HappyUserDetails;
import com.fdmgroup.HappyNews.service.HappyUserDetailsService;
import com.fdmgroup.HappyNews.service.RegistrationService;
import com.fdmgroup.HappyNews.util.HappyUserValidator;


@Controller

public class AuthController {
			private final HappyUserRepository userRepository;
			private final HappyUserValidator happyUserValidator;
			private final  RegistrationService registrationService;
			private final HappyUserDetailsService userDetailsService;
			
		@Autowired	
	public AuthController(HappyUserValidator happyUserValidator, HappyUserRepository userRepository,RegistrationService registrationService,HappyUserDetailsService userDetailsService) {
				super();
				this.userRepository = userRepository;;
				this.happyUserValidator = happyUserValidator;
				this.registrationService = registrationService;
				this.userDetailsService = userDetailsService;
			}


	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/login-error") 
	public String loginError(ModelMap model) {
		
		model.addAttribute("errorMessage", "Invalid username or password");
		
		return "login";
	}
	
	
	@GetMapping("/registration")
	public String registrationPage(@ModelAttribute("happyUser") HappyUser happyUser) {
		return "registration";
	}
	
	@PostMapping("/registration")
	public String performRegistration(@ModelAttribute("happyUser") HappyUser happyUser,ModelMap model) {
	 
	 HappyUser userFromDatabase = userDetailsService.findUserByName(happyUser.getUsername());
	 if (userFromDatabase.getUsername().equals(happyUser.getUsername()) || happyUser.getUsername().equals("anonymousUser")) {
			model.addAttribute("errorMessage", "This user name already exists");
			return "registration";
		}
	 
	 registrationService.register(happyUser);
	 
	 return "redirect:/login";
	 
	}
	
	@GetMapping("/admin")
	public String adminPage()
	{
		return "admin";
	}
	
	@GetMapping("/change-password")
	public String changePasswordPage(@ModelAttribute("happyUser") HappyUser happyUser) {
			
			return "changePassword";
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
			return "redirect:/login";	
	}
	
	
	@GetMapping("/recover-password")
	public String recoverPasswordPage(/*@ModelAttribute("happyUser") HappyUser happyUser*/) {
			
			return "recoverPassword";
		}
		
	@PostMapping("/recover-password")
	public String recoverPassword(@RequestParam("email") String email,@RequestParam("petName") String petName,@RequestParam("newPassword") String newPassword, @RequestParam("confirmNewPassword") String confirmNewPassword, ModelMap model) {
			Optional<HappyUser> optional = userRepository.findByEmail(email);
			if(optional.isEmpty()) {
				model.addAttribute("errorMessage","User does'not exist");
				return "recoverPassword";
			}else {
				UserDetails userDetails = userDetailsService.loadUserByEmailForPasswordChange(email);
				HappyUserDetails happyUserDetails1 = (HappyUserDetails) userDetails;
				if(happyUserDetails1.getPetName().equals(petName)) {
					
					if(newPassword.equals(confirmNewPassword)) {
						happyUserDetails1.setPassword(newPassword);
					    userDetails = (UserDetails) happyUserDetails1;
					    userDetailsService.saveUserToDb(happyUserDetails1);
					}else {
						model.addAttribute("errorMessage", "Pssword doesn't match");
						return "recoverPassword";
					}
				    
				    
				}else {
					model.addAttribute("errorMessage","Pet Name does not match");
					return "recoverPassword";
				}
			}
			
			return "redirect:/login";	
	}
}

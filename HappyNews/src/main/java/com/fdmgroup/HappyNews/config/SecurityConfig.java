package com.fdmgroup.HappyNews.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fdmgroup.HappyNews.security.AuthProviderImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebMvcConfigurerAdapter{
	
	
	private final AuthProviderImpl authProvider;
	
	@Autowired
	public SecurityConfig(AuthProviderImpl authProvider) {
		
		this.authProvider = authProvider;
	}


	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authProvider);
	}
	
	

}

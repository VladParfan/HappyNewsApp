package com.fdmgroup.HappyNews.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fdmgroup.HappyNews.security.AuthProviderImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	private final AuthProviderImpl authProvider;
	
	@Autowired
	public SecurityConfig(AuthProviderImpl authProvider) {
		
		this.authProvider = authProvider;
	}

		@Override
		protected void configure(HttpSecurity http) throws Exception
		{
			
		http.csrf().disable()
		.authorizeRequests()
			.antMatchers("/auth/login","/error").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/auth/login")
			.loginProcessingUrl("/process_login")
			.defaultSuccessUrl("/hello", true)
			.failureUrl("/auth/login?error");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(authProvider);
	}
	
	

}

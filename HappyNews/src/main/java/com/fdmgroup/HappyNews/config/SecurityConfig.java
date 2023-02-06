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
		     .antMatchers("/css/**", "/js/**", "/h2/**", "WEB-INF/jsps/**", "/", "/**/*.png", "/**/*.PNG", "/**/*.jpg", "/**/*.JPG", "/register", "/goRegisterPage", "/goQuestionPassword", "/questionPassword", "/resetPassword", "/goToSearchingPage", "/goToFAQ", "/sendMessageToAdmin", "/toPrivacyPolicy", "/toTermsAndConditions", "/goShowProductsOfUser/{username}", "/showOtherUserProfile/{username}", "/goToProductPage/{productId}", "/goToCategory/{category}", "/login", "/toContact", "/toAboutShazar", "/filtered").permitAll()
			.antMatchers("/auth/admin").hasRole("ADMIN")
			.antMatchers("/auth/login","/error","/auth/registration","/auth/change-password").permitAll()
			.anyRequest().hasAnyRole("USER","ADMIN")
			.and()
			.formLogin().loginPage("/auth/login")
			.loginProcessingUrl("/process_login")
			.defaultSuccessUrl("/hello", true)
			.failureUrl("/auth/login?error")
			.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/auth/login")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.and()
		.csrf()
			.disable()	
		.httpBasic()
			.and()
		.headers().frameOptions().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(authProvider);
	}
	
	

}

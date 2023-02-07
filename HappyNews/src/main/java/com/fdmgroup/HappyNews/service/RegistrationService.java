package com.fdmgroup.HappyNews.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.repository.HappyUserRepository;


@Component
public class RegistrationService {
	
	private final HappyUserRepository userRepository;
		
	@Autowired
	public RegistrationService(HappyUserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	@Transactional
	public void register(HappyUser happyUser) {
		Optional<HappyUser> optional = userRepository.findByEmail(happyUser.getEmail());
		
		if(!optional.isPresent()) {
			happyUser.setRole("ROLE_USER");
			userRepository.save(happyUser);
		}else {
			System.out.println("user exist!!!   We shoud add this message to the front end ");
		}
		
		
	}
	
}

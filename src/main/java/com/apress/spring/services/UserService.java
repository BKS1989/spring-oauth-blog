package com.apress.spring.services;

import java.util.List;

import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.apress.spring.entities.User;
import com.apress.spring.repositories.UserRepository;

@Service
public class UserService  {
	
	@Autowired
	private UserRepository userRepository;
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	public String save(User user) throws Exception {
			user.setPassword(passwordEncoder().encode(user.getPassword()));
			userRepository.save(user);
			return "user added successfully";
	}
	
	public User getUser(String username){
		return userRepository.findByUsername(username);
	}
	
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
}

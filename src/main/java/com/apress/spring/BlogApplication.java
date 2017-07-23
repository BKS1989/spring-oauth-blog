package com.apress.spring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.apress.spring.entities.Role;
import com.apress.spring.entities.User;
import com.apress.spring.repositories.UserRepository;
import com.apress.spring.security.CustomUserDetail;
import com.apress.spring.services.UserService;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class BlogApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
	
	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository) throws Exception{
		builder.userDetailsService(username -> new CustomUserDetail(repository.findByUsername(username))).passwordEncoder(passwordEncoder);
	}
}

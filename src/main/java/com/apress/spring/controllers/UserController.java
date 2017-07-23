package com.apress.spring.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.token.TokenService;
//import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apress.spring.entities.Role;
import com.apress.spring.entities.User;
import com.apress.spring.pojo.UserRegistration;
import com.apress.spring.services.RoleService;
import com.apress.spring.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;
	
//	@Autowired
//	private TokenStore tokenStore;
	
	@PostMapping(value="/register")
	public String registerUser(@RequestBody UserRegistration userRegistration) throws Exception {
		if (!userRegistration.getPassword().equals(userRegistration.getConfirm_password())) {
			return "Password mismatch";
		} else if (userService.getUser(userRegistration.getUsername()) != null) {
			return userRegistration.getUsername() + " already exist";
		} else if (userRegistration.getRole()== null){
			List<Role> roles = new ArrayList<>();
			Role role = roleService.getRole("USER");
			Log log = LogFactory.getLog(UserController.class);
			log.info("Role Name " + role.getName());
			if (role != null ){
				roles.add(role);
			}else{
				return "Role(USER) does not exist";
			}
			return userService.save(new User(userRegistration.getUsername(), userRegistration.getPassword(),roles));
		}else{
			return userService.save(new User(userRegistration.getUsername(), userRegistration.getPassword(),roleService.getRoles()));
		}
		
	}

	@GetMapping(value = "/users")
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
//	@GetMapping(value="/logout")
//	public void logout(@RequestParam( value="access_token") String access_token ){
//		tokenStore.removeAccessToken(tokenStore.readAccessToken(access_token));;
//	}

	@GetMapping(value = "/getUsername")
	public String getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}

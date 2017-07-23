package com.apress.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apress.spring.entities.Role;
import com.apress.spring.repositories.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> getRoles(){
		return roleRepository.findAll();
	}
	
	public Role getRole(String role){
		return roleRepository.findOne(role);
	}

}

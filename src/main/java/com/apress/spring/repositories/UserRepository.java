package com.apress.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apress.spring.entities.User;
import com.apress.spring.entities.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByUsername(String username);
	
	public List<Role> findRoleByUsername(String username);

}

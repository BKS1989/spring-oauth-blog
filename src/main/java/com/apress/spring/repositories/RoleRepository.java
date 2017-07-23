package com.apress.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apress.spring.entities.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
	public Role findByName(String name);
}

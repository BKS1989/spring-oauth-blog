package com.apress.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apress.spring.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	public List<Post> findByTitleContaining(String title);
	
}

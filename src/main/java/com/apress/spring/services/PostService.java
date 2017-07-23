package com.apress.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;
import javax.transaction.Transactional;

import org.apache.log4j.spi.LoggerFactory;
import org.hibernate.dialect.PostgresPlusDialect;
import org.hibernate.validator.internal.util.privilegedactions.GetClassLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.apress.spring.entities.Post;
import com.apress.spring.repositories.PostRepository;

@Service
public class PostService {

	@Autowired
	public PostRepository postRepository;

	@Autowired
	public UserService userService;

	
	public List<Post> getPosts() {
		return postRepository.findAll();
	}

	public List<Post> findByTitle(String title) {
		return postRepository.findByTitleContaining(title);
	}
	
	public List<Post> findByUser(Long id) {
		List<Post> posts = postRepository.findAll();
		System.out.print(posts.size());
		if(posts.isEmpty()){
			System.out.println("Post is empty");
		}
		List<Post> postByUser = new ArrayList<>();
		for(Post post: posts){
			if (post.getCreater().getId().equals(id)){
				postByUser.add(post);
			}
			System.out.println("Post By User " + post.getCreater().getUsername());
		}
		return postByUser;
	}

	public String add(Post post) {
		if (post.getTitle() != null && post.getBody() != null) {
			System.out.println("Post Data" + post.getBody());
			post.setCreated(new Date());
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			post.setCreater(userService.getUser(username));
			postRepository.save(post);
			return "Successfully add your post";
		} else {
			return "something went wrong";
		}
	}

}

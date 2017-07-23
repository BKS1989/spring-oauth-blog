package com.apress.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import com.apress.spring.entities.Post;
import com.apress.spring.services.*;

@RestController
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired UserService userService;
	
	@RequestMapping(value = "/posts")
	private List<Post> getPosts(){
		return postService.getPosts();
	}
	
	@PostMapping(value="/post")
	private String post(@RequestBody Post post){
		return postService.add(post);
	}
	
//	@RequestMapping(value="/post/{title}",method=RequestMethod.GET)
//	public List<Post>  postBytitle(@PathVariable("title") String title){
//		return postService.findByTitle(title);
//	}
	
	@RequestMapping(value="/post/{username}",method=RequestMethod.GET)
	public List<Post> postByUser(@PathVariable("username") String username){
		return postService.findByUser(userService.getUser(username).getId());
	}
}

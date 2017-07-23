package com.apress.spring.entities;

import java.util.Date;

import javax.persistence.*;import org.hibernate.annotations.Cascade;

import com.apress.spring.utils.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title;

	private String body;

	private Date created;
	
	@ManyToOne
	private User Creater;
	
	public Post() {
	}

	public Post(String title, String body, Date created) {
		this.title = title;
		this.body = body;
		this.created = created;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	@JsonSerialize(using=JsonPostUserSerializer.class)
	public User getCreater() {
		return Creater;
	}

	public void setCreater(User creater) {
		Creater = creater;
	}
	
	
	
	
}

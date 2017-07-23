package com.apress.spring.utils;

import java.io.IOException;

import com.apress.spring.entities.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonPostUserSerializer extends JsonSerializer<User> {

	@Override
	public void serialize(User user, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
			gen.writeString(user.getUsername());
		
	}

}

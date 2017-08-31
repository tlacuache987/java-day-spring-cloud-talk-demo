package com.example.demo.api.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;

@RestController
public class UserController {

	@Value("${message}")
	private String message;
	
	@Value("${server.port}")
	private int port;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public User getUser() {

		User user = new User();

		user.setName("Ivan García");
		user.setMessage(message + ", response by: " + port);

		return user;
	}
}

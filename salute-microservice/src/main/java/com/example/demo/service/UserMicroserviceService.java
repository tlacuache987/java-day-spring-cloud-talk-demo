package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.api.rest.client.UserMicroserviceClient;
import com.example.demo.domain.User;

@Service
public class UserMicroserviceService {
	
	@Autowired
	private UserMicroserviceClient userMicroserviceClient;
	
	public User getUser(){
		return userMicroserviceClient.retrieveUserFromMicroservice();
	}
}

package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.api.rest.client.UserMicroserviceClient;
import com.example.demo.domain.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserMicroserviceService {
	
	@Autowired
	private UserMicroserviceClient userMicroserviceClient;
	
    @HystrixCommand(fallbackMethod = "getDummyUser")
    public User getUser(){
		return userMicroserviceClient.retrieveUserFromMicroservice();
	}
    
    public User getDummyUser(){
    	User user = new User();
    	user.setName("Dummy User");
    	user.setMessage("returned by hystrix circuit breaker");
		return user;
	}
}

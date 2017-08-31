package com.example.demo.api.rest.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.domain.User;

@FeignClient("user-microservice")
public interface UserMicroserviceClient {

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	User retrieveUserFromMicroservice();
}

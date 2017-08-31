package com.example.demo.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Salute;
import com.example.demo.domain.User;
import com.example.demo.service.UserMicroserviceService;

@RestController
public class SaluteController {

	@Value("${salute}")
	private String saluteString;
	
	@Autowired
	private UserMicroserviceService userMicroserviceService;

	@RequestMapping(value = "/salute", method = RequestMethod.GET)
	public Salute getUser() {

		Salute salute = new Salute();

		salute.setSalute(saluteString);
		salute.setUser(userMicroserviceService.getUser());

		return salute;
	}
}

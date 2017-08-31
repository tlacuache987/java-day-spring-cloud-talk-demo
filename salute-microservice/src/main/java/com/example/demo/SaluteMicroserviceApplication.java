package com.example.demo;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class SaluteMicroserviceApplication implements ApplicationContextAware {

	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		ac.getBean("requestMappingHandlerAdapter");
	}

	public static void main(String[] args) {
		SpringApplication.run(SaluteMicroserviceApplication.class, args);
	}
}

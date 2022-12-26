package com.ayushsingh.user_service;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
// @EnableEurekaClient //not working
@EnableDiscoveryClient
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

	// //Main class is a confifuration class
	// //The Spring container should have a bean of RestTemplate
	// //to autowire it
	// @Bean
	// public RestTemplate restTemplate(){
	// 	return new RestTemplate();
	// }
	//OR CREATE A SEPARATE CONFIG CLASS 
}

package com.ayushsingh.user_service.user_service;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ayushsingh.user_service.entities.Hotel;
import com.ayushsingh.user_service.entities.Rating;
import com.ayushsingh.user_service.external.services.RatingService;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	// @Autowired
	// private RatingService ratingService;
	// // @Test
	// void createRating() {

	// 	Rating rating = Rating.builder().rating(10).feedback("Wonderful").userId("ddb8e2a9-ac6f-460d-a43e-eae23d18450c")
	// 			.hotelId("1093aa3f-8529-4330-8ce8-caa82546200b").build();
	// 	Map<?, ?> response = ratingService.createRating(rating);
	// 	System.out.println("new rating: "+response);
	// }

}

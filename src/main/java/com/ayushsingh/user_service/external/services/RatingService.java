package com.ayushsingh.user_service.external.services;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ayushsingh.user_service.entities.Rating;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {
    
    @GetMapping("/microservices/rating/get-all-ratings-by-user")
    public Map<?,?> getRatings(@RequestParam("userId")String userId);


    //These three methods can also be used if required
    @PostMapping("/microservices/rating/save-rating")
    public Map<?,?> createRating(@RequestBody Rating /*Use Map<String,Object if user-defined data type is not available */ rating);

    @PutMapping("/microservices/rating/update-rating")
    public Map<?,?> updateRating(@RequestBody Rating rating);

    @DeleteMapping("/microservices/rating/delete-rating")
    public Map<?,?> deleteRating(@RequestParam(name="ratingId") String ratingId);
}

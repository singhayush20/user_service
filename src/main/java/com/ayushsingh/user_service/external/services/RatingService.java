package com.ayushsingh.user_service.external.services;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {
    
    @GetMapping("/microservices/rating/get-all-ratings-by-user")
    public Map<?,?> getRatings(@RequestParam("userId")String userId);
}

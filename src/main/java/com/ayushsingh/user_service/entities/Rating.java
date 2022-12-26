package com.ayushsingh.user_service.entities;

import lombok.Data;
//This entity will be handled by anther microservice therefore
//there is no need to make an entity here
@Data
public class Rating {
    
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;
}

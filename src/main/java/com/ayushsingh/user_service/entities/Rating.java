package com.ayushsingh.user_service.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//This entity will be handled by anther microservice therefore
//there is no need to make an entity here
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;

    private Hotel hotel;
}

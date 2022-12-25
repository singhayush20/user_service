package com.ayushsingh.user_service.dto;

import java.util.List;

import com.ayushsingh.user_service.entities.Rating;

import lombok.Data;

@Data
public class UserDto {
    
    private String userId;
    private String name;
    private String email;
    private String about;
    private String phoneNo;
    private List<Rating> ratings;
}

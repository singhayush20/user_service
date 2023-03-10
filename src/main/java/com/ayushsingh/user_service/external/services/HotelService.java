package com.ayushsingh.user_service.external.services;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ayushsingh.user_service.entities.Hotel;
import com.ayushsingh.user_service.exceptions.SuccessResponse;


@FeignClient(name="HOTEL-SERVICE")
public interface HotelService {
    @GetMapping("/microservices/hotel/get-hotel-by-id")
    public ResponseEntity<SuccessResponse<Hotel>> getHotel(@RequestParam(name="hotelId") String hotelId);
}

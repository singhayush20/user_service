package com.ayushsingh.user_service.external.services;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="HOTEL-SERVICE")
public interface HotelService {
    @GetMapping("/microservices/hotel/get-hotel-by-id")
    public Map<?, ?> getHotel(@RequestParam(name="hotelId") String hotelId);
}

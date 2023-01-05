package com.ayushsingh.user_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ayushsingh.user_service.ServiceImpl.UserServiceImpl;
import com.ayushsingh.user_service.constants.AppConstants;
import com.ayushsingh.user_service.dto.UserDto;
import com.ayushsingh.user_service.exceptions.SuccessResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/microservices/user")
public class UserController {
    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping(value = "/save-user")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto) {
        UserDto uDto = this.userServiceImpl.saveUser(userDto);
        SuccessResponse<UserDto> successResponse = new SuccessResponse<>(AppConstants.SUCCESS_CODE,
                AppConstants.SUCCESS_MESSAGE, uDto);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-user")
    public ResponseEntity<?> deleteUser(@RequestParam(name = "userId") String userId) {
        boolean ans = this.userServiceImpl.deleteUser(userId);
        SuccessResponse<Boolean> successResponse = new SuccessResponse<>(AppConstants.SUCCESS_CODE,
                AppConstants.SUCCESS_MESSAGE, ans);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/get-all-users")
    public ResponseEntity<?> getAllUsers() {
        List<UserDto> userDtos = this.userServiceImpl.getAllUsers();
        SuccessResponse<List<UserDto>> successResponse = new SuccessResponse<>(AppConstants.SUCCESS_CODE,
                AppConstants.SUCCESS_MESSAGE, userDtos);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
    int retryCount=1;

    // this api is using the other two services- hotel and rating service
    // therefore we implement the circuit breaker
    // @CircuitBreaker(name = "RATINGHOTELCIRCUITBREAKER", fallbackMethod = "ratingHotelFallback")
    @Retry(name="RATINGHOTELRETRY",fallbackMethod = "ratingHotelFallback") //using same method for understanding
    @GetMapping(value = "/get-user-by-id")
    public ResponseEntity<?> getUserById(@RequestParam(name = "userId") String userId) {
        System.out.println("Retry count: "+retryCount++);

        UserDto userDto = this.userServiceImpl.getUser(userId);
        SuccessResponse<UserDto> successResponse = new SuccessResponse<>(AppConstants.SUCCESS_CODE,
                AppConstants.SUCCESS_MESSAGE, userDto);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/update-user")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto) {
        UserDto uDto = this.userServiceImpl.updateUser(userDto);
        SuccessResponse<UserDto> successResponse = new SuccessResponse<>(AppConstants.SUCCESS_CODE,
                AppConstants.SUCCESS_MESSAGE, uDto);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    // create fallback method for circuit breaker with same return type as that of the method handling 
    //the endpoint, parameters are the same as the original method including an Exception
    public ResponseEntity<?> ratingHotelFallback(String userId, Exception e) {
        System.out.println("fallback: Error while fetching user details: " + e.getMessage());
        // ApiResponse apiResponse=new ApiResponse()
        UserDto user=UserDto.builder().email("dummy@gmail.com").name("Dummy").about("Dummy user because some services down").build();
        SuccessResponse<UserDto> failureResponse=new SuccessResponse<>(AppConstants.FAILURE_CODE, AppConstants.FAILURE_MESSAGE,user );
        return new ResponseEntity<>(failureResponse, HttpStatus.OK);
    }
}

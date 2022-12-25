package com.ayushsingh.user_service.exceptions;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import org.springframework.validation.FieldError;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ayushsingh.user_service.constants.AppConstants;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class) // add comma separated list of Exception classes
    public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(AppConstants.ERROR_CODE, message, AppConstants.ERROR_MESSAGE);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DuplicateResourceException.class) // add comma separated list of Exception classes
    public ResponseEntity<ApiResponse> duplicateResourceFoundException(DuplicateResourceException ex) {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(AppConstants.ERROR_CODE, message, AppConstants.ERROR_MESSAGE);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Map<String, String>> handleIOException(IOException ex) {
        Map<String, String> errors = new HashMap<>();
        String fieldName = "errorMessage";
        String error = ex.getMessage();
        errors.put(fieldName, error);
        return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(JWTAuthenticationException.class)
    public ResponseEntity<Map<String, String>> handleAuthException(JWTAuthenticationException ex) {
        Map<String, String> errors = new HashMap<>();
        String fieldName = "errorMessage";
        String error = ex.getMessage();
        String status=ex.getStatus();
        errors.put(fieldName, error);
        errors.put("status",status);
        return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
    }

    
    // @ExceptionHandler(ConstraintViolationException.class)
    // public ResponseEntity<Map<String, String>> constraintViolationExceptionHandler(ConstraintViolationException ex) {
    //     Map<String, String> errors = new HashMap<>();
        
    //     String fieldName = ex.getMessage();
    //     errors.put("error message", fieldName);

        

    //     return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
    // }

    @ExceptionHandler(InvalidTokenInHeaderException.class) // add comma separated list of Exception classes
    public ResponseEntity<ApiResponse> invalidTokenException(InvalidTokenInHeaderException ex) {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(AppConstants.ERROR_CODE, message, AppConstants.ERROR_MESSAGE);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoAdminPermissionException.class)
    public ResponseEntity<ApiResponse> noPermissionException(NoAdminPermissionException ex) {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(AppConstants.ERROR_CODE, message, AppConstants.ERROR_MESSAGE);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse> noParameterException(MissingServletRequestParameterException ex) {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(AppConstants.ERROR_CODE, message, AppConstants.ERROR_MESSAGE);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }
   
}

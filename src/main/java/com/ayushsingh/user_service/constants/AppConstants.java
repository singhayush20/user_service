package com.ayushsingh.user_service.constants;


public class AppConstants {
    //Status and Message
    public static final String ERROR_CODE="2002";
    public static final String ERROR_MESSAGE="Error";
    public static final String NOT_FOUND_MESSAGE="Not Found";
    public static final String SUCCESS_CODE="2000";
    public static final String FAILURE_CODE="2001";
    public static final String SUCCESS_MESSAGE="Success";
    public static final String FAILURE_MESSAGE="Failure";

    //database
    public static final String NORMAL_ROLE="ROLE_NORMAL";
    public static final String ADMIN_ROLE="ROLE_ADMIN";
    public static final Long ADMIN_ROLE_ID=44L;
    public static final Long NORMAL_ROLE_ID=45L;

    //authentication
    public static final String AUTHORIZATION="Authorization";
    public static final String SECRET_KEY = "secret";
    public static final String BEARER="Bearer";
    public static final String ADMIN_KEY="adminkey";

    //URLs
    public static final String FETCH_HOTELS_URL = "http://HOTEL-SERVICE/microservices/hotel/get-hotel-by-id?hotelId=";

    public static final String FETCH_RATINGS_URL = "http://RATING-SERVICE/microservices/rating/get-all-ratings-by-user?userId=";
    

}

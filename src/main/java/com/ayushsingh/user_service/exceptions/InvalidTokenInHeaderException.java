package com.ayushsingh.user_service.exceptions;




public class InvalidTokenInHeaderException extends RuntimeException{
  
    String message;
    public InvalidTokenInHeaderException(String t,String m){
        message=m+" "+t;
    }
    
}

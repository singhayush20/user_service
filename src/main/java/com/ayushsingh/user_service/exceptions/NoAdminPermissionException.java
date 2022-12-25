package com.ayushsingh.user_service.exceptions;




public class NoAdminPermissionException extends RuntimeException{
    
    private String message;
    public NoAdminPermissionException(String message){
        this.message=message;
    }
    public String getMessage() {
        return message;
    }

    
}

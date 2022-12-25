package com.ayushsingh.user_service.exceptions;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResponse<T> {
    T data;
    String message;
    String code;
    public SuccessResponse(){

    }
    public SuccessResponse(String statusCode,String message,T ob){
        data=ob;
        this.message=message;
        this.code=statusCode;
    }
}

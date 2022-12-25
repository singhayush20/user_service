package com.ayushsingh.user_service.exceptions;


public class DuplicateResourceException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private String fieldValue;
    public DuplicateResourceException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s with %s : %s",resourceName,fieldName,fieldValue+" already exists"));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
    public String getResourceName() {
        return resourceName;
    }
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getFieldValue() {
        return fieldValue;
    }
    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }
    
}

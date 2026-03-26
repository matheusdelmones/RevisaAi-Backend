package com.RevisaAi.JavaProjects.exception;

public class ResourceNotFoundException extends RuntimeException {

    private String ErrorMessage;

    public ResourceNotFoundException(String ErrorMessage){
        super(ErrorMessage);
        this.ErrorMessage = ErrorMessage;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

}

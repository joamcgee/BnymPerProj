package com.ProjectForBNYM.ExceptionHandling;

//throwing an exception for resource not found in the controller
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public ResourceNotFoundException(String msg) {
        super(msg);

    }

}

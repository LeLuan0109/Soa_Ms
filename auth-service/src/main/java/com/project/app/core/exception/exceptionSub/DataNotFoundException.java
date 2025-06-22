package com.project.app.core.exception.exceptionSub;

public class DataNotFoundException extends Exception{
    public DataNotFoundException(String message) {
        super(message);
    }
    public DataNotFoundException( Throwable cause ) {
        super(cause);
    }
    public DataNotFoundException(String message , Throwable cause) {
        super(message , cause);
    }
}
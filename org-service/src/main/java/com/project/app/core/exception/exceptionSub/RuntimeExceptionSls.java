package com.project.app.core.exception.exceptionSub;

public class RuntimeExceptionSls extends RuntimeException{
    public RuntimeExceptionSls(String message) {
        super(message);
    }
    public RuntimeExceptionSls(String message, Throwable cause) {
        super(message, cause);
    }
}

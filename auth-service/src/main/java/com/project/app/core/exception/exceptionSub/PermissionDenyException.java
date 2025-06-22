package com.project.app.core.exception.exceptionSub;
public class PermissionDenyException extends Exception{
    public PermissionDenyException(String message) {
        super(message);
    }
}
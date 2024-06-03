package com.bu200.exception;

public class ProjectExistException extends RuntimeException {
    public ProjectExistException(String message){
        super(message);
    }
}

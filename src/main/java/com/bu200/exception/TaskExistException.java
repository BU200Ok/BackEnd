package com.bu200.exception;

public class TaskExistException extends RuntimeException {
    public TaskExistException(String message) {
        super(message);
    }
}

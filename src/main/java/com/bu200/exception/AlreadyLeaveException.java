package com.bu200.exception;

public class AlreadyLeaveException extends RuntimeException {
    public AlreadyLeaveException(String message) {
        super(message);
    }
}

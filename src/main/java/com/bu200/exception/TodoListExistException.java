package com.bu200.exception;

public class TodoListExistException extends RuntimeException{
    public TodoListExistException(String message){
        super(message);
    }

}

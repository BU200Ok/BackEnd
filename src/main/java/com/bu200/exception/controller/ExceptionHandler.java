package com.bu200.exception.controller;

import com.bu200.common.response.ResponseDTO;
import com.bu200.common.response.Tool;
import com.bu200.exception.AlreadyGoException;
import com.bu200.exception.AlreadyLeaveException;
import com.bu200.exception.HaveToGoException;
import com.bu200.exception.TodoListExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ExceptionHandler {

    private final Tool tool;

    public ExceptionHandler(Tool tool) {
        this.tool = tool;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ResponseDTO> alreadyGoException(AlreadyGoException ex){
        return tool.res(HttpStatus.CONFLICT, "이미 출근되셨습니다.", ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ResponseDTO> haveToGo(HaveToGoException ex){
        return tool.res(HttpStatus.CONFLICT, "출근을 하고 퇴근 처리를 해야합니다.", ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ResponseDTO> alreadyLeaveException(AlreadyLeaveException ex){
        return tool.res(HttpStatus.CONFLICT, "이미 퇴근처리 되었습니다.", ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ResponseDTO> TodoListExistException(TodoListExistException ex){
        return tool.res(HttpStatus.CONFLICT, "중복된 투두리스트 존재!", ex.getMessage());
    }



}

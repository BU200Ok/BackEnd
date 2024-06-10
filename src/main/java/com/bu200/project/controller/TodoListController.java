package com.bu200.project.controller;

import com.bu200.common.response.ResponseDTO;
import com.bu200.common.response.Tool;
import com.bu200.project.dto.AddTodoListDTO;
import com.bu200.project.dto.TodoListDTO;
import com.bu200.project.entity.TodoList;
import com.bu200.project.service.TodoListService;
import com.bu200.security.dto.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects/{projectCode}/tasks/{taskType}/{taskTypeDetail}/{taskCode}")
public class TodoListController {

    private final TodoListService todoListService;
    private final Tool tool;

    public TodoListController(TodoListService todoListService, Tool tool) {
        this.todoListService = todoListService;
        this.tool = tool;
    }

    @GetMapping("/todolists")
    public ResponseEntity<ResponseDTO> getTodoLists(@PathVariable Long taskCode){
        List<TodoListDTO> todoLists = todoListService.getTodoLists(taskCode);

        return tool.res(HttpStatus.OK, "투두리스트들입니다.", todoLists);
    }

    @GetMapping("/myTodolists")
    public ResponseEntity<ResponseDTO> getMyTodoLists(@PathVariable Long taskCode,
                                                      @AuthenticationPrincipal CustomUserDetails user){
        List<TodoListDTO> todoLists = todoListService.getMyTodoLists(taskCode, Long.valueOf(user.getCode()));

        return tool.res(HttpStatus.OK, "내 투두리스트들입니다.", todoLists);
    }

    @PostMapping("/todolists/add")
    public ResponseEntity<ResponseDTO> addMyTodoLists(@PathVariable Long taskCode,
                                                      @AuthenticationPrincipal CustomUserDetails user,
                                                      @RequestBody AddTodoListDTO addTodoListDTO){
        if(todoListService.checkDuplicateTodoList(taskCode, addTodoListDTO)){
            return tool.resErr("중복된 내용의 투두리스트 존재.");
        }
        TodoListDTO todoListDTO = todoListService.addTodoList(taskCode, Long.valueOf(user.getCode()), addTodoListDTO);

        return tool.res(HttpStatus.OK, "투두리스트 추가 완료", todoListDTO);
    }
}

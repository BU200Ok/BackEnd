package com.bu200.project.controller;

import com.bu200.common.response.ResponseDTO;
import com.bu200.common.response.Tool;
import com.bu200.project.dto.AddTodoListDTO;
import com.bu200.project.dto.RemoveTodoListDTO;
import com.bu200.project.dto.TodoListDTO;
import com.bu200.project.service.ProjectTaskTodoListService;
import com.bu200.security.dto.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{projectCode}/{taskType}/{taskCode}/todolist")
public class ProjectTaskTodoListController {
    private final ProjectTaskTodoListService projectTaskTodoListService;
    private final Tool tool;

    public ProjectTaskTodoListController(ProjectTaskTodoListService projectTaskTodoListService, Tool tool) {
        this.projectTaskTodoListService = projectTaskTodoListService;
        this.tool = tool;
    }

    @GetMapping("/all-todolist")
    public ResponseEntity<ResponseDTO> getAllTodoList(@PathVariable Long projectCode,
                                                      @PathVariable Long taskCode){
        List<TodoListDTO> todoListDTOS = projectTaskTodoListService.getAllTodoList(taskCode);

        return tool.res(HttpStatus.OK, "모든 투두리스트", todoListDTOS);
    }

    @GetMapping("/mytodolist")
    public ResponseEntity<ResponseDTO> getMyTodoList(@AuthenticationPrincipal CustomUserDetails user,
                                                     @PathVariable Long taskCode){
        Long accountCode = Long.valueOf(user.getCode());
        List<TodoListDTO> todoListDTOS = projectTaskTodoListService.getMyTodoList(accountCode, taskCode);

        return tool.res(HttpStatus.OK, "나의 투두리스트입니다.",  todoListDTOS);
    }

    @PostMapping("/add-todolist")
    public ResponseEntity<ResponseDTO> addTodoList(@AuthenticationPrincipal CustomUserDetails user,
                                                   @PathVariable Long taskCode,
                                                   @RequestBody AddTodoListDTO addTodoListRequestDTO){
        AddTodoListDTO addTodoListDTO = projectTaskTodoListService.addMyTodoList(Long.valueOf(user.getCode()),taskCode, addTodoListRequestDTO);

        return tool.res(HttpStatus.OK, "투두리스트 추가 성공", addTodoListDTO);
    }

    @PostMapping("/remove-todolist")
    public ResponseEntity<ResponseDTO> removeTodoList(@RequestBody RemoveTodoListDTO removeTodoListDTO){
        RemoveTodoListDTO removeTodoListResponseDTO = projectTaskTodoListService.removeTodoList(removeTodoListDTO);

        return tool.res(HttpStatus.OK, "정상적으로 삭제되었습니다.", removeTodoListResponseDTO);
    }
}

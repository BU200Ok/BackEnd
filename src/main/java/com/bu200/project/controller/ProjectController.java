package com.bu200.project.controller;

import com.bu200.common.response.ResponseDTO;
import com.bu200.common.response.Tool;
import com.bu200.project.dto.*;
import com.bu200.project.service.ProjectService;
import com.bu200.project.service.ProjectTodoListService;
import com.bu200.security.dto.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private final ProjectTodoListService projectTodoListService;
    private final ProjectService projectService;
    private final Tool tool;

    public ProjectController(ProjectTodoListService projectTodoListService, ProjectService projectService, Tool tool) {
        this.projectTodoListService = projectTodoListService;
        this.projectService = projectService;
        this.tool = tool;
    }

    @GetMapping("/get-my-project")
    public ResponseEntity<ResponseDTO> getMyProject(@AuthenticationPrincipal CustomUserDetails user){
        List<ProjectDTO> project = projectService.findByAccountCode(Long.valueOf(user.getCode()));
        return tool.res(HttpStatus.OK,"success",project);
    }
    @GetMapping("/get-project")
    public ResponseEntity<ResponseDTO> getAllProject(){
        List<ProjectDTO> project = projectService.findAllByProjectOpenStatusTrue();
        return tool.res(HttpStatus.OK,"success",project);
    }

    @GetMapping("/{projectCode}/todo_list")
    public ResponseEntity<ResponseDTO> getTodoListPage(@AuthenticationPrincipal CustomUserDetails user, @PathVariable Long projectCode){
        TodoListPageDTO todoListPageDTO = projectTodoListService.getProjectTodoLists(Long.valueOf(user.getCode()), projectCode);
        return tool.res(HttpStatus.OK, "todoListPageDTO입니다.", todoListPageDTO);
    }
    @PostMapping("/{projectCode}/todo_list_add")
    public ResponseEntity<ResponseDTO> addTodoList(@AuthenticationPrincipal CustomUserDetails user, @PathVariable("projectCode") Long projectCode, @RequestBody AddTodoListRequestDTO addTodoListRequestDTO){
        TodoListDTO todoListDTO = projectTodoListService.addProjectTodoList(Long.valueOf(user.getCode()), projectCode, addTodoListRequestDTO);
        return tool.res(HttpStatus.OK, "todoList 저장 성공", todoListDTO);
    }

    @PostMapping("/{projectCode}/todo_list_remove")
    public ResponseEntity<ResponseDTO> removeTodoList(@AuthenticationPrincipal CustomUserDetails user, @PathVariable("projectCode") Long projectCode, RemoveTodoListDTO removeTodoListDTO){
        TodoListDTO todoListDTO = projectTodoListService.removeProjectTodoList(Long.valueOf(user.getCode()),projectCode,removeTodoListDTO);
        return tool.res(HttpStatus.OK, "todoList 삭제 성공", todoListDTO);
    }
}

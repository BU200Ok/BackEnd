package com.bu200.project.controller;

import com.bu200.common.response.ResponseDTO;
import com.bu200.common.response.Tool;
<<<<<<< HEAD
import com.bu200.project.dto.*;
=======
import com.bu200.project.entity.Project;
>>>>>>> 2eeb5b5cfd19c3a03023ef80c785127478127cfd
import com.bu200.project.service.ProjectService;
import com.bu200.project.service.ProjectTodoListService;
import com.bu200.security.dto.CustomUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
=======
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> 2eeb5b5cfd19c3a03023ef80c785127478127cfd

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
    public ResponseEntity<ResponseDTO> getMyProject(@AuthenticationPrincipal CustomUserDetails user, @RequestParam(defaultValue = "0") int page){
        System.out.println("페이지는"+page);
        Page<Project> project = projectService.findByAccountCode(Long.valueOf(user.getCode()),page);
        return tool.res(HttpStatus.OK,"success",project);
    }
    @GetMapping("/get-project")
    public ResponseEntity<ResponseDTO> getAllProject(@RequestParam(defaultValue = "0") int page){
        System.out.println("페이지는"+page);
        Page<Project> project = projectService.findAllByProjectOpenStatusTrue(page);
        return tool.res(HttpStatus.OK,"success",project);
    }
<<<<<<< HEAD

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
=======
    //필요한 데이터
    // 프로젝트
    // 멤버 이름, 멤버 부서, 멤버 직급 => 멤버의 List<Account> 엔티티
    // 프로젝트 제목, 프로젝트 마감기한 => 프로젝트 정보
    // 프로젝트 창립자, 프로젝트 창립자 부서, 프로젝트 창립자 직급 => 프로젝트 정보의 Account 엔티티

    //이거 리덕스로
    @GetMapping("/project-sidebar")
    public ResponseEntity<ResponseDTO> getProjectSidebarInformation(@RequestParam Long projectCode){
        return tool.res(HttpStatus.OK,"success",projectService.findById(projectCode));
>>>>>>> 2eeb5b5cfd19c3a03023ef80c785127478127cfd
    }
}

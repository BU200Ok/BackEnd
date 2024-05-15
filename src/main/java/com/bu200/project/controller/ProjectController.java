package com.bu200.project.controller;

import com.bu200.common.response.ResponseDTO;
import com.bu200.common.response.Tool;
import com.bu200.project.dto.ProjectDTO;
import com.bu200.project.dto.RemoveTodoListDTO;
import com.bu200.project.dto.TodoListDTO;
import com.bu200.project.dto.TodoListPageDTO;
import com.bu200.project.entity.Project;
import com.bu200.project.service.ProjectService;
import com.bu200.project.service.TodoListService;
import com.bu200.security.dto.CustomUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;
    private final TodoListService todoListService;
    private final Tool tool;

    public ProjectController(ProjectService projectService, TodoListService todoListService, Tool tool) {
        this.projectService = projectService;
        this.todoListService = todoListService;
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
    //이거 리덕스로
    @GetMapping("/project-sidebar")
    public ResponseEntity<ResponseDTO> getProjectSidebarInformation(@RequestParam Long projectCode){
        return tool.res(HttpStatus.OK,"success",projectService.findById(projectCode));
    }
    @GetMapping("/project-forum-data")
    public ResponseEntity<ResponseDTO> getProjectForumData(@RequestParam Long projectCode){
        return tool.res(HttpStatus.OK,"success",projectService.findByProjectCode(projectCode));
    }
    @GetMapping("/get-project-forum-post")
    public ResponseEntity<ResponseDTO> getProjectForumPost(@RequestParam Long projectForumCode){
        return tool.res(HttpStatus.OK,"success",projectService.findByProjectForumCode(projectForumCode));
    }
    @PostMapping("/project-create")
    public ResponseEntity<ResponseDTO> createProject(@RequestBody ProjectDTO projectDTO, @AuthenticationPrincipal CustomUserDetails user){
        Project project = projectService.createProject(projectDTO,user);
        return tool.res(HttpStatus.OK,"success",project);
    }



    //여기서부터 projectTodoList
    @GetMapping("/{projectCode}/{projectForumCode}/todolist")
    public ResponseEntity<ResponseDTO> getTodoList(@AuthenticationPrincipal CustomUserDetails user, @PathVariable Long projectCode, @PathVariable Long projectForumCode){
        TodoListPageDTO todoListPageDTO = todoListService.getTodoListPageDTO(Long.valueOf(user.getCode()), projectCode, projectForumCode);
        return tool.res(HttpStatus.OK, "success", todoListPageDTO);
    }

    @PostMapping("/{projectCode}/{projectForumCode}/addtodolist")
    public ResponseEntity<ResponseDTO> addTodoList(@AuthenticationPrincipal CustomUserDetails user, @PathVariable Long projectCode, @PathVariable Long projectForumCode, @RequestBody TodoListDTO requestTodoListDTO){
        requestTodoListDTO.setTodoListStart(LocalDate.now());
        System.out.println(1);
        TodoListDTO todoListDTO = todoListService.addTodoList(Long.valueOf(user.getCode()), projectForumCode, requestTodoListDTO);
        return tool.res(HttpStatus.OK, "success", todoListDTO);
    }

    @PostMapping("/{projectCode}/{projectForumCode}/removetodolist")
    public ResponseEntity<ResponseDTO> removeTodoList(@AuthenticationPrincipal CustomUserDetails user, @PathVariable Long projectForumCode, @RequestBody RemoveTodoListDTO removeTodoListDTO){
        TodoListDTO todoListDTO = todoListService.removeTodoList(removeTodoListDTO);
        return tool.res(HttpStatus.OK, "sucess", todoListDTO);
    }
}

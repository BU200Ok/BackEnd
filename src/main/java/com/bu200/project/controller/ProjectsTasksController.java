package com.bu200.project.controller;

import com.bu200.common.response.ResponseDTO;
import com.bu200.common.response.Tool;
import com.bu200.project.dto.*;
import com.bu200.project.service.ProjectService;
import com.bu200.project.service.TaskService;
import com.bu200.security.dto.CustomUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects/{projectCode}")
public class ProjectsTasksController {

    private final Tool tool;
    private final ProjectService projectService;
    private final TaskService taskService;

    public ProjectsTasksController(Tool tool, ProjectService projectService, TaskService taskService) {
        this.tool = tool;
        this.projectService = projectService;
        this.taskService = taskService;
    }
    //프로젝트의 정보를 가져온다.
    @GetMapping
    public ResponseEntity<ResponseDTO> project(@PathVariable Long projectCode,
                                               @AuthenticationPrincipal CustomUserDetails user){
            if (!projectService.hasAuthorityCheck(Long.valueOf(user.getCode()), projectCode)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseDTO(HttpStatus.CONFLICT, "권한이 없습니다.", null));
            }
        ProjectDetailDTO projectDetailDTO = projectService.projectDetail(projectCode);
        return tool.res(HttpStatus.OK, "성공적 프로젝트 정보", projectDetailDTO);
    }

//    검색
//    @GetMapping("/accounts/search")
//    public ResponseEntity<ResponseDTO> accountsSearch(@RequestParam(name = "keyword")String keyword){
//
//    }

    //todo inprogress done
    @GetMapping("/tasks/{taskType}")
    public ResponseEntity<ResponseDTO> taskTypeTasks(@PathVariable Long projectCode,
                                                     @PathVariable Integer taskType){
        TaskListDTO taskListDTO = taskService.getTaskList(projectCode, taskType);

        return tool.res(HttpStatus.OK, "taskType에 따른 이름목록입니다.", taskListDTO);
    }

    @PostMapping("/accounts/add")
    public ResponseEntity<ResponseDTO> addAccountProject(@RequestParam String memberName,
                                                         @PathVariable Long projectCode){
        if(projectService.checkDuplicateAccountProject(memberName, projectCode)){
            return tool.resErr("이미 참여중입니다.");
        }
        AddAccountProjectResponseDTO addAccountProjectResponseDTO = projectService.addAccount(memberName, projectCode);

        return tool.res(HttpStatus.OK, "참여 완료", addAccountProjectResponseDTO);
    }

    @GetMapping("/tasks/{taskType}/{taskTypeDetail}")
    public ResponseEntity<ResponseDTO> taskTypeDetailTasks(@PathVariable Long projectCode,
                                                           @PathVariable Integer taskType,
                                                           @PathVariable String taskTypeDetail,
                                                           @RequestParam(name = "page") Integer page){
        Pageable pageable = PageRequest.of(page,5);
        Page<TaskDTO> tasks = taskService.allTask(projectCode, taskType, taskTypeDetail, pageable);

        return tool.res(HttpStatus.OK, "테스크들입니다.", tasks);
    }

    @GetMapping("/tasks/{taskType}/{taskTypeDetail}/search")
    public ResponseEntity<ResponseDTO> searchTask(@PathVariable Long projectCode,
                                                  @PathVariable Integer taskType,
                                                  @PathVariable String taskTypeDetail,
                                                  @RequestParam(name = "keyword")String keyword,
                                                  @RequestParam(name = "page") Integer page){
        Pageable pageable = PageRequest.of(page, 5);
        Page<TaskDTO> tasks = taskService.searchTask(projectCode, taskType, taskTypeDetail, keyword, pageable);

        return tool.res(HttpStatus.OK, "찾은 task들입니다.", tasks);
    }

    @PostMapping("/tasks/{taskType}/{taskTypeDetail}/add")
    public ResponseEntity<ResponseDTO> addTask(@AuthenticationPrincipal CustomUserDetails user,
                                               @PathVariable Long projectCode,
                                               @PathVariable Integer taskType,
                                               @PathVariable String taskTypeDetail,
                                               @RequestBody AddTaskDTO addTaskDTO){
        addTaskDTO.setTaskType(taskType);
        addTaskDTO.setTaskDetail(taskTypeDetail);
        if(taskService.checkDuplicateTask(projectCode, addTaskDTO)){
            return tool.resErr("중복된 이름의 업무 존재");
        }

        TaskDTO saveTaskDTO = taskService.addTask(Long.valueOf(user.getCode()), projectCode, addTaskDTO);

        return tool.res(HttpStatus.OK, "저장완료", saveTaskDTO);
    }


}

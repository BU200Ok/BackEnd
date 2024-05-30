package com.bu200.project.controller;

import com.bu200.common.response.ResponseDTO;
import com.bu200.common.response.Tool;
import com.bu200.project.dto.AccountDTO;
import com.bu200.project.dto.ProjectDetailDTO;
import com.bu200.project.service.ProjectService;
import com.bu200.project.service.ProjectsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects/{projectCode}")
public class ProjectsTasksController {

    private final Tool tool;
    private final ProjectService projectService;

    public ProjectsTasksController(Tool tool, ProjectService projectService) {
        this.tool = tool;
        this.projectService = projectService;
    }
    //프로젝트의 정보를 가져온다.
    @GetMapping
    public ResponseEntity<ResponseDTO> project(@PathVariable Long projectCode){
        ProjectDetailDTO projectDetailDTO = projectService.projectDetail(projectCode);
        return tool.res(HttpStatus.OK, "성공적 프로젝트 정보", projectDetailDTO);
    }

    //검색
//    @GetMapping("/accounts/search")
//    public ResponseEntity<ResponseDTO> accountsSearch(@RequestParam(name = "keyword")String keyword){
//    }

//    @GetMapping("/tasks/{taskType}")
//    public ResponseEntity<ResponseDTO> taskTypeTasks(@PathVariable Long projectCode,
//                                                     @PathVariable Integer taskType){
//
//    }
//
//    @GetMapping("/tasks/{taskType}/{taskTypeDetail}")
//    public ResponseEntity<ResponseDTO> taskTypeDetailTasks(@PathVariable Long projectCode,
//                                                           @PathVariable Integer taskType,
//                                                           @PathVariable String taskTypeDetail,
//                                                           @RequestParam(name = "page") Integer page){
//
//    }
//
//    @GetMapping("/tasks/{taskType}/{taskTypeDetail}/search")
//    public ResponseEntity<ResponseDTO> searchTask(@PathVariable Long projectCode,
//                                                  @PathVariable Integer taskType,
//                                                  @PathVariable String taskTypeDetail,
//                                                  @RequestParam(name = "keyword")String keyword,
//                                                  @RequestParam(name = "page") Integer page){
//
//    }
//
//    @PostMapping("/tasks/{taskType}/{taskTypeDetail}/add")
//    public ResponseEntity<ResponseDTO> addTask(@PathVariable Long projectCode,
//                                               @PathVariable Integer taskType,
//                                               @PathVariable String taskTypeDetail,
//                                               @RequestBody AddTaskDTO addTaskDTO){
//
//    }
//

}

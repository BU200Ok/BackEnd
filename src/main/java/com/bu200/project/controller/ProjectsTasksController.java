package com.bu200.project.controller;

import com.bu200.common.response.ResponseDTO;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects/{projectCode}")
public class ProjectsTasksController {
//
//    @GetMapping
//    public ResponseEntity<ResponseDTO> project(@PathVariable Long projectCode){
//
//    }
//
//    //검색
//    @GetMapping("/accounts/search")
//    public ResponseEntity<ResponseDTO> accountsSearch(@RequestParam(name = "keyword")String keyword){
//
//    }
//
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

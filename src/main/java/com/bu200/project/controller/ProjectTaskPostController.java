package com.bu200.project.controller;

import com.bu200.common.response.ResponseDTO;
import com.bu200.common.response.Tool;
import com.bu200.project.dto.AddTaskFileDTO;
import com.bu200.project.dto.AddTaskPostDTO;
import com.bu200.project.dto.TaskPostDTO;
import com.bu200.project.service.TaskFileService;
import com.bu200.project.service.TaskPostService;
import com.bu200.security.dto.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/projects/{projectCode}/tasks/{taskType}/{taskTypeDetail}")
public class ProjectTaskPostController {

    private final TaskPostService taskPostService;
    private final TaskFileService taskFileService;
    private final Tool tool;
    public ProjectTaskPostController(TaskPostService taskPostService, TaskFileService taskFileService, Tool tool) {
        this.taskPostService = taskPostService;
        this.taskFileService = taskFileService;
        this.tool = tool;
    }

    @GetMapping("/{taskCode}/task-posts")
    public ResponseEntity<ResponseDTO> taskPostList(@PathVariable Long taskCode){
        List<TaskPostDTO> taskPostDTOS = taskPostService.getTaskPost(taskCode);
        System.out.println(1);
        return tool.res(HttpStatus.OK, "업무 게시글들입니다.", taskPostDTOS);
    }

    @PostMapping("/{taskCode}/task-posts/add")
    public ResponseEntity<ResponseDTO> addTaskPost(@AuthenticationPrincipal CustomUserDetails user,
                                                   @PathVariable Long taskCode,
                                                   @RequestBody AddTaskPostDTO addTaskPostDTO){
        AddTaskPostDTO addTaskPostResponseDTO = taskPostService.addTaskPost(Long.valueOf(user.getCode()), taskCode, addTaskPostDTO);

        return tool.res(HttpStatus.OK, "업무 저장 성공", addTaskPostResponseDTO);
    }

    @PostMapping("/{taskCode}/task-posts/{taskPostCode}/files/add")
    public ResponseEntity<ResponseDTO> addTaskFile(@PathVariable Long taskPostCode,
                                                   @RequestParam List<MultipartFile> files){
        for(MultipartFile file : files) tool.upload(file);
        List<AddTaskFileDTO> addTaskFileDTOS = taskFileService.addTaskFile(taskPostCode, files);

        return tool.res(HttpStatus.OK, "완성되었습니다.", addTaskFileDTOS);
    }
}

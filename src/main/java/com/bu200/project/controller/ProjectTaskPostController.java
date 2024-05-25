package com.bu200.project.controller;

import com.bu200.common.response.ResponseDTO;
import com.bu200.common.response.Tool;
import com.bu200.project.dto.AddTaskPostDTO;
import com.bu200.project.dto.AddTaskPostResponseDTO;
import com.bu200.project.dto.TaskPostDTO;
import com.bu200.project.service.ProjectTaskPostService;
import com.bu200.security.dto.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{projectCode}/{taskType}/{taskCode}/task-post")
public class ProjectTaskPostController {
    private final Tool tool;
    private final ProjectTaskPostService projectTaskPostService;

    public ProjectTaskPostController(Tool tool, ProjectTaskPostService projectTaskPostService) {
        this.tool = tool;
        this.projectTaskPostService = projectTaskPostService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getTaskPost(@PathVariable Long projectCode,
                                                   @PathVariable String taskType,
                                                   @PathVariable Long taskCode){
        List<TaskPostDTO> taskPostDTOS = projectTaskPostService.getTaskPost(projectCode, taskType, taskCode);
        return tool.res(HttpStatus.OK, "업무 게시글 목록입니다.", taskPostDTOS);
    }

    @PostMapping("/add-task-post")
    public ResponseEntity<ResponseDTO> addTaskPost(@AuthenticationPrincipal CustomUserDetails user,
                                                   @PathVariable Long taskCode,
                                                   @RequestBody AddTaskPostDTO addTaskPostDTO){
        AddTaskPostResponseDTO addTaskPostResponseDTO = projectTaskPostService.addTaskPost(Long.valueOf(user.getCode()), taskCode, addTaskPostDTO);

        return tool.res(HttpStatus.OK, "업무게시글 저장되었습니다.", addTaskPostResponseDTO);
    }

    //수정하기
}

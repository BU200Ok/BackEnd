package com.bu200.project.controller;

import com.bu200.common.response.ResponseDTO;
import com.bu200.common.response.Tool;
import com.bu200.project.dto.TaskPostDTO;
import com.bu200.project.service.TaskPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects/{projectCode}/tasks/{taskType}/{taskTypeDetail}")
public class ProjectTaskPostController {

    private final TaskPostService taskPostService;
    private final Tool tool;
    public ProjectTaskPostController(TaskPostService taskPostService, Tool tool) {
        this.taskPostService = taskPostService;
        this.tool = tool;
    }

    @GetMapping("/{taskCode}")
    public ResponseEntity<ResponseDTO> taskPostList(@PathVariable Long taskCode){
        List<TaskPostDTO> taskPostDTOS = taskPostService.getTaskPost(taskCode);

        return tool.res(HttpStatus.OK, "업무 게시글들입니다.", taskPostDTOS);
    }

    //파일을 dto에 담을지, params로 받을지 결정
//    @PostMapping("/{taskCode}/add")
//    public ResponseEntity<ResponseDTO> addTaskPost(@PathVariable Long taskCode,
//                                                   @RequestBody )
}

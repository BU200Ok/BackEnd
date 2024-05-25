package com.bu200.project.controller;

import com.bu200.common.response.ResponseDTO;
import com.bu200.common.response.Tool;
import com.bu200.project.dto.TaskDTO;
import com.bu200.project.service.ProjectTaskService;
import com.bu200.security.dto.CustomUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
public class ProjectTaskController {
    private final Tool tool;
    private final ProjectTaskService projectTaskService;

    public ProjectTaskController(Tool tool, ProjectTaskService projectTaskService) {
        this.tool = tool;
        this.projectTaskService = projectTaskService;
    }

    /**
     *  나의 프로젝트의 프로세스에 포함된 모든 업무를 가져온다.
     *  업무 타입
     * "analyze"
     * "design"
     * "impl"
     * "test"
     * "output"
     */
    @GetMapping("/{projectCode}/{taskType}")
    public ResponseEntity<ResponseDTO> getProjectTask(@AuthenticationPrincipal CustomUserDetails user,
                                                      @PathVariable String taskType,
                                                      @PathVariable Long projectCode,
                                                      @PageableDefault(size = 10)Pageable pageable){
        Page<TaskDTO> taskDTOS = projectTaskService.getTask(projectCode, taskType, pageable);
        return tool.res(HttpStatus.OK, "모든 업무입니다.", taskDTOS);
    }

//    @GetMapping("/{projectCode}/{taskType}/find-keyword")
//    public ResponseEntity<ResponseDTO> getKeywordProjectTask(@PathVariable String taskType,
//                                                             @PathVariable Long projectCode,
//                                                             @RequestParam String keyWord,
//                                                             @PageableDefault(size = 10)Pageable pageable){
//        Page<TaskDTO> taskDTOS = projectTaskService.getKeyWordTask(projectCode, taskType, keyWord, pageable);
//        return tool.res(HttpStatus.OK,"추가예정", taskDTOS);
//    }

    @PostMapping("/{projectCode}/{taskType}/add-task")
    public ResponseEntity<ResponseDTO> addTask(@PathVariable Long projectCode,
                                               @PathVariable String taskType,
                                               @RequestBody TaskDTO taskDTO){
        TaskDTO taskResponseDTO = projectTaskService.addTask(projectCode, taskType, taskDTO);
        return tool.res(HttpStatus.OK, "업무 추가완료", taskResponseDTO);
    }
}

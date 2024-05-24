package com.bu200.project.controller;

import com.bu200.common.response.ResponseDTO;
import com.bu200.common.response.Tool;
import com.bu200.project.service.ProjectTaskService;
import com.bu200.security.dto.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/{projectCode}/{process}")
    public ResponseEntity<ResponseDTO> getProjectTask(@AuthenticationPrincipal CustomUserDetails user,
                                                      @PathVariable String process){
        return tool.res(HttpStatus.OK, "더미값", "good");
    }
}

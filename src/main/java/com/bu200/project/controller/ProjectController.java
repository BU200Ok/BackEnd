package com.bu200.project.controller;

import com.bu200.common.response.ResponseDTO;
import com.bu200.common.response.Tool;
import com.bu200.project.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;
    private final Tool tool;

    public ProjectController(ProjectService projectService, Tool tool) {
        this.projectService = projectService;
        this.tool = tool;
    }

    @GetMapping("/test")
    public ResponseEntity<ResponseDTO> test(){
        String projectName = "Project Alpha";
        return tool.res(HttpStatus.OK,"success",projectService.findByProjectName(projectName));
    }
}

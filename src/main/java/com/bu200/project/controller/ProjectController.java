package com.bu200.project.controller;

import com.bu200.common.response.ResponseDTO;
import com.bu200.common.response.Tool;
import com.bu200.project.dto.ProjectDTO;
import com.bu200.project.service.ProjectService;
import com.bu200.security.dto.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;
    private final Tool tool;

    public ProjectController(ProjectService projectService, Tool tool) {
        this.projectService = projectService;
        this.tool = tool;
    }

    @GetMapping("/get-my-project")
    public ResponseEntity<ResponseDTO> getMyProject(@AuthenticationPrincipal CustomUserDetails user){
        List<ProjectDTO> project = projectService.findByAccountCode(Long.valueOf(user.getCode()));
        return tool.res(HttpStatus.OK,"success",project);
    }
    @GetMapping("/get-project")
    public ResponseEntity<ResponseDTO> getAllProject(){
        List<ProjectDTO> project = projectService.findAllByProjectOpenStatusTrue();
        return tool.res(HttpStatus.OK,"success",project);
    }
}

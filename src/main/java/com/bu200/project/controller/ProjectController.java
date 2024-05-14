package com.bu200.project.controller;

import com.bu200.common.response.ResponseDTO;
import com.bu200.common.response.Tool;
import com.bu200.project.dto.ProjectDTO;
import com.bu200.project.entity.Project;
import com.bu200.project.service.ProjectService;
import com.bu200.security.dto.CustomUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ResponseDTO> getMyProject(@AuthenticationPrincipal CustomUserDetails user, @RequestParam(defaultValue = "0") int page){
        System.out.println("페이지는"+page);
        Page<Project> project = projectService.findByAccountCode(Long.valueOf(user.getCode()),page);
        return tool.res(HttpStatus.OK,"success",project);
    }
    @GetMapping("/get-project")
    public ResponseEntity<ResponseDTO> getAllProject(@RequestParam(defaultValue = "0") int page){
        System.out.println("페이지는"+page);
        Page<Project> project = projectService.findAllByProjectOpenStatusTrue(page);
        return tool.res(HttpStatus.OK,"success",project);
    }
    //이거 리덕스로
    @GetMapping("/project-sidebar")
    public ResponseEntity<ResponseDTO> getProjectSidebarInformation(@RequestParam Long projectCode){
        return tool.res(HttpStatus.OK,"success",projectService.findById(projectCode));
    }
    @GetMapping("/project-forum-data")
    public ResponseEntity<ResponseDTO> getProjectForumData(@RequestParam Long projectCode){
        return tool.res(HttpStatus.OK,"success",projectService.findByProjectCode(projectCode));
    }
    @GetMapping("/get-project-forum-post")
    public ResponseEntity<ResponseDTO> getProjectForumPost(@RequestParam Long projectForumCode){
        return tool.res(HttpStatus.OK,"success",projectService.findByProjectForumCode(projectForumCode));
    }
    @PostMapping("/project-create")
    public ResponseEntity<ResponseDTO> createProject(@RequestBody ProjectDTO projectDTO, @AuthenticationPrincipal CustomUserDetails user){
        Project project = projectService.createProject(projectDTO,user);
        return tool.res(HttpStatus.OK,"success",project);
    }

}

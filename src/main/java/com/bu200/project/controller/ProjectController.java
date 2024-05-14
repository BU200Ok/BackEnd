package com.bu200.project.controller;

import com.bu200.common.response.ResponseDTO;
import com.bu200.common.response.Tool;
import com.bu200.project.entity.Project;
import com.bu200.project.service.ProjectService;
import com.bu200.security.dto.CustomUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    //필요한 데이터
    // 프로젝트
    // 멤버 이름, 멤버 부서, 멤버 직급 => 멤버의 List<Account> 엔티티
    // 프로젝트 제목, 프로젝트 마감기한 => 프로젝트 정보
    // 프로젝트 창립자, 프로젝트 창립자 부서, 프로젝트 창립자 직급 => 프로젝트 정보의 Account 엔티티

    //이거 리덕스로
    @GetMapping("/project-sidebar")
    public ResponseEntity<ResponseDTO> getProjectSidebarInformation(@RequestParam Long projectCode){
        return tool.res(HttpStatus.OK,"success",projectService.findById(projectCode));
    }
}

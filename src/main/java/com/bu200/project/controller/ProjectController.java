package com.bu200.project.controller;

import com.bu200.common.response.ResponseDTO;
import com.bu200.common.response.Tool;
import com.bu200.project.dto.AllProjectResponseDTO;
import com.bu200.project.dto.ProjectDTO;
import com.bu200.project.service.ProjectsService;
import com.bu200.security.dto.CustomUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private final Tool tool;
    private final ProjectsService projectsService;

    public ProjectController(Tool tool, ProjectsService projectsService) {
        this.tool = tool;
        this.projectsService = projectsService;
    }

    /**
     * 프로젝트들을 불러온다.
     * projectName, projectStart, projectEnd, projectStatus, departmentName, teamName
     * account들
     */
    @GetMapping("/get-project")
    public ResponseEntity<ResponseDTO> projects(@RequestParam(name = "page")Integer page){
        Pageable pageable = PageRequest.of(page, 6);
        Page<ProjectDTO> projects = projectsService.projects(pageable);

        return tool.res(HttpStatus.OK, "프로젝트들입니다.", projects);
    }

    /**
     * 위와 동일
     */
    @GetMapping("/get-my-project")
    public ResponseEntity<ResponseDTO> myProjects(@AuthenticationPrincipal CustomUserDetails user,
                                                  @RequestParam(name = "page")Integer page){
        Pageable pageable = PageRequest.of(page, 6);
        Page<ProjectDTO> projects = projectsService.myProjects(Long.valueOf(user.getCode()), pageable);

        return tool.res(HttpStatus.OK, "내 프로젝트들입니다.", projects);
    }
    /**
     *키워드를 포함한 프로젝트들을 불러온다.
     */
    @GetMapping("/get-search-all-project")
    public ResponseEntity<ResponseDTO> searchProjects(@RequestParam(name = "page")Integer page,
                                                      @RequestParam(name = "keyword") String keyword){
        Pageable pageable = PageRequest.of(page, 6);
        Page<ProjectDTO> projects = projectsService.searchProjects(keyword, pageable);

        return tool.res(HttpStatus.OK, "키워드가 포함된 프로젝트", projects);
    }

    @GetMapping("/get-search-my-project")
    public ResponseEntity<ResponseDTO> searchMyProjects(@AuthenticationPrincipal CustomUserDetails user,
                                                        @RequestParam(name = "page")Integer page,
                                                        @RequestParam(name = "keyword") String keyword){
        Pageable pageable = PageRequest.of(page, 6);
        Page<ProjectDTO> projects = projectsService.searchMyProjects(Long.valueOf(user.getCode()), keyword, pageable);

        return tool.res(HttpStatus.OK, "키워드가 포함된 내 프로젝트", projects);
    }
//
//    @PostMapping("/add")
//    public ResponseEntity<ResponseDTO> addProject(@AuthenticationPrincipal CustomUserDetails user,
//                                                  @RequestBody AddProjectRequestDTO addProjectRequestDTO){
//
//    }
}

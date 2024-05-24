package com.bu200.project.controller;

import com.bu200.common.response.ResponseDTO;
import com.bu200.common.response.Tool;
import com.bu200.login.repository.AccountRepository;
import com.bu200.project.dto.AddProjectDTO;
import com.bu200.project.dto.KeywordRequestDTO;
import com.bu200.project.dto.ProjectDTO;
import com.bu200.project.dto.ProjectListDTO;
import com.bu200.project.service.ProjectService;
import com.bu200.security.dto.CustomUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private final Tool tool;
    private final ProjectService projectService;

    public ProjectController(Tool tool, AccountRepository accountRepository, ProjectService projectService) {
        this.tool = tool;
        this.projectService = projectService;
    }

    //전체 프로젝트 가져오기
    @GetMapping("/find-all-project")
    public ResponseEntity<ResponseDTO> getAllProject(@AuthenticationPrincipal CustomUserDetails user,
                                                     @PageableDefault(size = 6)Pageable pageable){
        /**user가 포함된 팀의 모든 프로젝트를 가져온다.
         * 만약 user가 관리자라면, 모든 프로젝트를 가져온다.
         * 한 페이지당 6개의 프로젝트를 가져와야한다.
         * 가져올때 고려 요소
         * 1. openstatus : true
         * 2. priority : 오름차순
         * 3. 유저가 관리자인지 아닌지
         */

        Long userCode = Long.valueOf(user.getCode());
        Page<ProjectDTO> projectDTOS = projectService.getProject(userCode, pageable);

        return tool.res(HttpStatus.OK, "모든 프로젝트 목록입니다", projectDTOS);
    }

    //내 프로젝트 가져오기
    @GetMapping("/find-my-project")
    public ResponseEntity<ResponseDTO> getMyProject(@AuthenticationPrincipal CustomUserDetails user,
                                                    @PageableDefault(size = 6)Pageable pageable){
        Long userCode = Long.valueOf(user.getCode());
        Page<ProjectDTO> projectDTOS = projectService.getMyProject(userCode, pageable);

        return tool.res(HttpStatus.OK, "내 팀의 모든 프로젝트 목록입니다.", projectDTOS);
    }

    //프로젝트 검색
    @GetMapping("/{keyword}")
    public ResponseEntity<ResponseDTO> FindProject(@PathVariable String keyword,
                                                   @PageableDefault(size = 6)Pageable pageable){
        Page<ProjectDTO> projectDTOS = projectService.getKewordProject(keyword, pageable);
        return tool.res(HttpStatus.OK, "키워드가 포함된 프로젝트 목록입니다.", projectDTOS);
    }

    //프로젝트 만들기
    @PostMapping
    public ResponseEntity<ResponseDTO> addProject(@AuthenticationPrincipal CustomUserDetails user,
                                                  @RequestBody AddProjectDTO addProjectDTO){
        Long userCode = Long.valueOf(user.getCode());
        AddProjectDTO addProjectResponseDTO = projectService.addProject(userCode, addProjectDTO);

        return tool.res(HttpStatus.OK, "프로젝트 추가 완료", addProjectResponseDTO);
    }
}

package com.bu200.forum.controller;
import com.bu200.common.response.ResponseDTO;
import com.bu200.forum.dto.ForumDTO;
import com.bu200.forum.entity.Forum;
import com.bu200.forum.service.ForumService;
import com.bu200.security.dto.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/forum")
@Controller
public class ForumController {
    private final ForumService forumService;

    public ForumController(ForumService forumService) {
        this.forumService = forumService;
    }

    //공지사항
    @GetMapping("/public")
    public ResponseEntity<List<ForumDTO>> getAllDepartmentsForums(@AuthenticationPrincipal CustomUserDetails user) {
        List<ForumDTO> forums = forumService.getAllForums();
        return ResponseEntity.ok(forums);
    }

    // 부서별 게시판
    @GetMapping("/department")
    public ResponseEntity<List<ForumDTO>> getDepartmentForums(@AuthenticationPrincipal CustomUserDetails user) {
        boolean isAdmin = user.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return ResponseEntity.ok(forumService.getAllForums());
        } else {
            String departmentName = forumService.getDepartmentName(user.getUsername());
            List<ForumDTO> forums = forumService.getForumsByDepartment(departmentName);
            return ResponseEntity.ok(forums);
        }
    }


    // 게시글 작성
    @PostMapping("/create")
    public ResponseEntity<Forum> createForum(@RequestBody Forum forum) {
        Forum savedForum = forumService.saveForum(forum);
        return ResponseEntity.ok(savedForum);
    }


}

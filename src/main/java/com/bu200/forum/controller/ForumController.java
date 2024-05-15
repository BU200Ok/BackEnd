package com.bu200.forum.controller;
import com.bu200.common.response.ResponseDTO;
import com.bu200.forum.dto.ForumDTO;
import com.bu200.common.response.Tool;
import com.bu200.forum.entity.Forum;
import com.bu200.forum.service.ForumService;
import com.bu200.mypage.service.Dtos.MainPageDTO;
import com.bu200.mypage.service.FindMyPageMainService;
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
    private final FindMyPageMainService findMyPageMainService;
    private final Tool tool;

    public ForumController(ForumService forumService, FindMyPageMainService findMyPageMainService, Tool tool) {
        this.forumService = forumService;
        this.findMyPageMainService = findMyPageMainService;
        this.tool = tool;
    }

    //공지사항
    @GetMapping("/public")
    public ResponseEntity<ResponseDTO> getAllDepartmentsForums(@AuthenticationPrincipal CustomUserDetails user) {
        List<ForumDTO> forums = forumService.getAllForums();
        return tool.res(HttpStatus.OK, "모든 포럼 데이터", forums);
    }

    // 부서별 게시판
    @GetMapping("/department")
    public ResponseEntity<ResponseDTO> getDepartmentForums(@AuthenticationPrincipal CustomUserDetails user) {
        boolean isAdmin = user.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            List<ForumDTO> forums = forumService.getAllForums();
            return tool.res(HttpStatus.OK, "Admin의 모든 포럼 데이터", forums);
        } else {
            String departmentName = forumService.getDepartmentName(user.getUsername());
            List<ForumDTO> forums = forumService.getForumsByDepartment(departmentName);
            return tool.res(HttpStatus.OK, "해당 부서의 포럼 데이터", forums);
        }
    }



    //개인정보 가져오기
    @GetMapping("/myinfo")
    public ResponseEntity<ResponseDTO> myInfoLoding(@AuthenticationPrincipal CustomUserDetails user){
        System.out.println(user.getUsername());
        MainPageDTO mainPageDTO = findMyPageMainService.FindAccountData(user.getUsername());
        return tool.res(HttpStatus.OK, "mainpageDTO입니다.", mainPageDTO);
    }


    // 게시글 작성
    @PostMapping("/create")
    public ResponseEntity<Forum> createForum(@RequestBody Forum forum) {
        Forum savedForum = forumService.saveForum(forum);
        return ResponseEntity.ok(savedForum);
    }


}

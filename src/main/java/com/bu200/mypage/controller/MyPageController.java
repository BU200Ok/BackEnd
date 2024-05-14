package com.bu200.mypage.controller;

import com.bu200.common.response.ResponseDTO;
import com.bu200.common.response.Tool;
import com.bu200.mypage.service.Dtos.*;
import com.bu200.mypage.service.FindMyPageMainService;
import com.bu200.mypage.service.MyPageAttendanceService;
import com.bu200.mypage.service.SearchMypageProjectService;
import com.bu200.security.dto.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/mypage")
public class MyPageController {
    private final FindMyPageMainService findMyPageMainService;
    private final Tool tool;
    private final SearchMypageProjectService searchMypageProjectService;
    private final MyPageAttendanceService myPageAttendanceService;

    public MyPageController(Tool tool, FindMyPageMainService findMyPageMainService, SearchMypageProjectService searchMypageProjectService, MyPageAttendanceService myPageAttendanceService) {
        this.tool = tool;
        this.findMyPageMainService = findMyPageMainService;
        this.searchMypageProjectService = searchMypageProjectService;
        this.myPageAttendanceService = myPageAttendanceService;
    }

    //accountDTO(내 정보, 팀, 부서까지) + 프로젝트(description, status, 이미 완료한 프로젝트)
    @GetMapping("/mainpage")
    public ResponseEntity<ResponseDTO> myPageLoding(@AuthenticationPrincipal CustomUserDetails user){
        System.out.println(user.getUsername());
        MainPageDTO mainPageDTO = findMyPageMainService.FindAccountData(user.getUsername());
        return tool.res(HttpStatus.OK, "mainpageDTO입니다.", mainPageDTO);
    }

    //출퇴 관리
    @PostMapping("/mainpage/attendance-go-work")
    public ResponseEntity<ResponseDTO> myPageAttendanceGo(@AuthenticationPrincipal CustomUserDetails user, @RequestBody MyPageAttendanceGoRequestDTO myPageAttendanceGoRequestDTO){
        myPageAttendanceGoRequestDTO.setAttendanceGoWork(LocalDateTime.now());
        MyPageAttendanceResponseDTO myPageAttendanceResponseDTO = myPageAttendanceService.myPageAttendanceGoService(user.getUsername(), myPageAttendanceGoRequestDTO);
        return tool.res(HttpStatus.OK, "출근 정상 처리되었습니다.", myPageAttendanceResponseDTO);
    }
    @PostMapping("/mainpage/attendance-leave-work")
    public ResponseEntity<ResponseDTO> myPageAttendanceLeave(@AuthenticationPrincipal CustomUserDetails user, @RequestBody MyPageAttendanceLeaveRequestDTO myPageAttendanceLeaveRequestDTO){
        myPageAttendanceLeaveRequestDTO.setAttendanceLeaveWork(LocalDateTime.now());
        MyPageAttendanceResponseDTO myPageAttendanceResponseDTO = myPageAttendanceService.myPageAttendanceLeaveService(user.getUsername(), myPageAttendanceLeaveRequestDTO);
        return tool.res(HttpStatus.OK, "퇴근 정상 처리되었습니다.", myPageAttendanceResponseDTO);
    }

    //프로젝트 검색
    @GetMapping("/mainpage/search-project")
    public ResponseEntity<ResponseDTO> myPageProjectSearch(@AuthenticationPrincipal CustomUserDetails user, @RequestParam String keyWord){
        List<MainPageProjectSearchResponseDTO> mainPageProjectSearchResponseDTOS = searchMypageProjectService.myPageProjectSearchService(user.getUsername(), keyWord);
        return tool.res(HttpStatus.OK,"keyword:" + keyWord + "가 포함된 프로젝트 리스트입니다.", mainPageProjectSearchResponseDTOS);
    }



}

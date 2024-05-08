package com.bu200.security.controller;

import com.bu200.common.response.ResponseDTO;
import com.bu200.security.dto.CustomUserDetails;
import com.bu200.security.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@CrossOrigin
public class MainController {

    @GetMapping("/")
    public String mainPage(){
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("MainController 유저의 ID는 : "+accountId);
        return "안녕하세요 "+accountId + "님!";
    }
    @GetMapping("/user")
    public ResponseEntity<ResponseDTO> testMethod(@AuthenticationPrincipal CustomUserDetails user){
        System.out.println(user.getUsername());
        System.out.println(user.getEmail());
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"테스트 성공",user));
    }
}

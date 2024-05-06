package com.bu200.security.controller;

import com.bu200.common.response.ResponseDTO;
import com.bu200.security.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/test")
    public ResponseEntity<ResponseDTO> testMethod(){
        User user = new User();
        user.setAccountId("테스트 아이디");
        user.setAccountPassword("비밀번호");
        user.setAccountRole("ROLE_USER");
        user.setAccountCode(1);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"테스트 성공",user));
    }
}

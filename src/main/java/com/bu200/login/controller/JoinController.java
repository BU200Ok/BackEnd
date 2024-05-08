package com.bu200.login.controller;

import com.bu200.login.dto.JoinDTO;
import com.bu200.login.service.JoinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @PostMapping("/join")
    public String joinUser(JoinDTO joinDTO){
        System.out.println(joinDTO);
        joinService.joinUser(joinDTO);

        return "회원가입 성공";
    }


}

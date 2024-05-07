package com.bu200.login.controller;

import com.bu200.common.response.ResponseDTO;
import com.bu200.login.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/send-email-for-find-account")
    public ResponseEntity<ResponseDTO> sendMailForFindAccount(@RequestParam String email, HttpServletRequest request){
        String ip = request.getRemoteAddr();
        if(loginService.sendMailForFindAccount(email,ip)){
            System.out.println("성공");
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"성공",true));
        }else{
            System.out.println("실패");
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.BAD_GATEWAY,"실패",null));
        }
    }
    @GetMapping("/check-otp")
    public ResponseEntity<ResponseDTO> checkOTP(@RequestParam Integer code,HttpServletRequest request){
        String ip = request.getRemoteAddr();
        if(loginService.checkOTP(code,ip)){
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"성공",true));
        }else{
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"인증번호가 맞지 않아요",false));
        }
    }
    @GetMapping("/get-id")
    public  ResponseEntity<ResponseDTO> getIdFromFindAccount(@RequestParam String email){
        try{
            String id = loginService.getIdFromFindAccount(email);
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"아이디 찾기 성공",id));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"아이디가 없습니다.",null));

        }
    }
}

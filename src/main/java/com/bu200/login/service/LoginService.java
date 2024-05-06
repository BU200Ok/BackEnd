package com.bu200.login.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final JavaMailSender mailSender;
    public LoginService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    public boolean sendMailForFindAccount(String email) {
        try {
            MimeMessagePreparator messagePreparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                messageHelper.setFrom("wjdwltjq7289@naver.com");
                messageHelper.setTo(email);
                messageHelper.setSubject("이메일 인증 코드입니다.");
                messageHelper.setText("<h1>안녕</h1><p>이거는 <b>굵은</b> HTML 메세지야!</p>",true);
            };
            mailSender.send(messagePreparator);
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }
}

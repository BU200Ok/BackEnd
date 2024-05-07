package com.bu200.login.service;

import com.bu200.login.entity.FindAccount;
import com.bu200.login.repository.FindAccountRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class LoginService {
    private final JavaMailSender mailSender;
    private final FindAccountRepository findAccountRepository;
    public LoginService(JavaMailSender mailSender, FindAccountRepository findAccountRepository) {
        this.mailSender = mailSender;
        this.findAccountRepository = findAccountRepository;
    }
    @Transactional
    public boolean sendMailForFindAccount(String email, String ip) {
        try {
            int randomCode = 10000 + new Random().nextInt(90000);
            MimeMessagePreparator messagePreparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                messageHelper.setFrom("wjdwltjq7289@naver.com");
                messageHelper.setTo(email);
                messageHelper.setSubject("이메일 인증 코드입니다.");
                messageHelper.setText("<h1>안녕하세요!</h1><p>인증 코드는 <b>"+randomCode+"</b> 입니다!</p>",true);
            };
            FindAccount data = new FindAccount();
            data.setIp(ip);
            data.setSecretCode(randomCode);
            mailSender.send(messagePreparator);
            findAccountRepository.save(data);
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    @Transactional
    public boolean checkOTP(Integer code, String ip) {
        Long count = findAccountRepository.countByIpAndSecretCode(ip,code);
        if(count>0){
            findAccountRepository.deleteByIpAndSecretCode(ip,code);
            return true;
        }else{
            return false;
        }
    }
}

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
                messageHelper.setText("<div style=\"box-shadow: 0px 4px 4px rgba(0,0,0,0.25); border-radius: 20px; width: 50%; height: 400px; background-color: #ADC7A1; margin: 0 auto;\">\n" +
                        "    </br><h1 style=\"color: white;margin: 0 auto; text-align: center;\">회사 이름</h1>\n" +
                        "    <hr>\n" +
                        "    <br>\n" +
                        "    <div style=\"color: white; text-align: center;\">아래의 코드를 입력하세요.</div><br/>\n" +
                        "    <div style=\"box-shadow: 0px 4px 4px rgba(0,0,0,0.25); background-color: #f2eedf; width: 600px; height: 200px; border-radius: 10px; margin: 0 auto; display: flex; justify-content: center; align-items: center;\">\n" +
                        "       <h5 style=\"font-size: 50px; margin: 0 auto; text-align: center;\">"+randomCode+"</h5></br></br>\n" +
                        "    </div>",true);
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

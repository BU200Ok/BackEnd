package com.bu200.login.service;

import com.bu200.login.entity.Account;
import com.bu200.login.entity.FindAccount;
import com.bu200.login.repository.AccountRepository;
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
    private final AccountRepository accountRepository;
    public LoginService(JavaMailSender mailSender, FindAccountRepository findAccountRepository, AccountRepository accountRepository) {
        this.mailSender = mailSender;
        this.findAccountRepository = findAccountRepository;
        this.accountRepository = accountRepository;
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
                int[] digits = new int[5];
                String numberStr = Integer.toString(randomCode);
                for (int i = 0; i < numberStr.length(); i++) {digits[i] = numberStr.charAt(i) - '0';}
                messageHelper.setText("<div>"+randomCode+"</div>",true);
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

    public String getIdFromFindAccount(String email) {
        Account account = accountRepository.findByAccountEmail(email);
        return account.getAccountId();
    }
}

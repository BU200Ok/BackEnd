package com.bu200.security.service;

import com.bu200.security.dto.JoinDTO;
import com.bu200.security.entity.User;
import com.bu200.security.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public void joinUser(JoinDTO joinDTO){
        Boolean isExist = userRepository.existsByAccountId(joinDTO.getAccountId());
        if(isExist){    //이미 아이디가 존재한다면?
            System.out.println("이미 존재하는 아이디");
            return; //회원 가입 실패
        }
        //유저 정보가 없다면?
        User data = new User();
        data.setAccountId(joinDTO.getAccountId());
        data.setAccountPassword(bCryptPasswordEncoder.encode(joinDTO.getAccountPassword()));    //비밀번호 인코딩
        data.setAccountRole("ROLE_USER");  //모든 유저를 어드민으로

        userRepository.save(data);
    }
}

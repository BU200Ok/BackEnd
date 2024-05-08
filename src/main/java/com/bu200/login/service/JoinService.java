package com.bu200.login.service;

import com.bu200.login.dto.JoinDTO;
import com.bu200.login.entity.Account;
import com.bu200.login.entity.Team;
import com.bu200.login.repository.AccountRepository;
import com.bu200.login.repository.TeamRepository;
import com.bu200.security.entity.User;
import com.bu200.login.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JoinService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TeamRepository teamRepository;
    private final AccountRepository accountRepository;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, TeamRepository teamRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.teamRepository = teamRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void joinUser(JoinDTO joinDTO){
        Boolean isExist = accountRepository.existsByAccountId(joinDTO.getAccountId());
        if(isExist){    //이미 아이디가 존재한다면?
            System.out.println("이미 존재하는 아이디");
            return; //회원 가입 실패
        }
        Account data = new Account();
        data.setAccountId(joinDTO.getAccountId());
        data.setAccountPassword(bCryptPasswordEncoder.encode(joinDTO.getAccountPassword()));    //비밀번호 인코딩
        data.setAccountName(joinDTO.getAccountName());
        data.setAccountJoinDate(new Date());
        data.setAccountPosition(joinDTO.getAccountPosition());
        data.setAccountAddress(joinDTO.getAccountAddress());
        data.setAccountEmail(joinDTO.getAccountEmail());
        data.setTeam(teamRepository.findByTeamName(joinDTO.getTeamName()));
        if(joinDTO.getAccountRole().equals("관리자")){
            data.setAccountRole("ROLE_ADMIN");
        }else{
            data.setAccountRole("ROLE_USER");
        }


        accountRepository.save(data);
    }
}

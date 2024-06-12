package com.bu200.mypage.service;

import com.bu200.login.entity.Account;
import com.bu200.login.entity.Attendance;
import com.bu200.login.entity.Department;
import com.bu200.login.entity.Team;
import com.bu200.mypage.repository.FindAccountDataRepository;
import com.bu200.mypage.repository.FindMyPageProjectRepository;
import com.bu200.mypage.repository.MyPageAttendanceRepository;
import com.bu200.mypage.service.DTO.MainPageDTO;
import com.bu200.project.entity.Project;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
//mypage의 최상단 이름, 직책 등 가져오기
public class FindMyPageMainService {
    private final FindAccountDataRepository findAccountDataRepository;
    private final FindMyPageProjectRepository findMyPageProjectRepository;
    private final MyPageAttendanceRepository myPageAttendanceRepository;
    private final ModelMapper modelMapper;

    public FindMyPageMainService(FindAccountDataRepository findAccountDataRepository, FindMyPageProjectRepository findMyPageProjectRepository, MyPageAttendanceRepository myPageAttendanceRepository, ModelMapper modelMapper) {
        this.findAccountDataRepository = findAccountDataRepository;
        this.findMyPageProjectRepository = findMyPageProjectRepository;
        this.myPageAttendanceRepository = myPageAttendanceRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public MainPageDTO FindAccountData(String accountId){
        Account account = findAccountDataRepository.findAccountWithHighPriorityProject(accountId);
        MainPageDTO mainPageDTO = new MainPageDTO();
        modelMapper.map(account, mainPageDTO);
        modelMapper.map(account.getTeam(), mainPageDTO);
        modelMapper.map(account.getTeam().getDepartment(), mainPageDTO);
        if(!account.getProjects().isEmpty()) {
            modelMapper.map(account.getProjects().get(0), mainPageDTO);
        }
        return mainPageDTO;
    }
}

package com.bu200.mypage.service;

import com.bu200.login.entity.Account;
import com.bu200.login.entity.Attendance;
import com.bu200.mypage.repository.FindAccountDataRepository;
import com.bu200.mypage.repository.FindMyPageProjectRepository;
import com.bu200.mypage.repository.MyPageAttendanceRepository;
import com.bu200.mypage.service.Dtos.MainPageDTO;
import com.bu200.project.entity.Project;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
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

            Account account = findAccountDataRepository.findByAccountId(accountId);
            if(account == null){
                throw new EntityNotFoundException("accountId:" + accountId + "인 계정이 없습니다.");
            }
            List<Project> projects = findMyPageProjectRepository.findByAccount_AccountIdAndProjectOpenStatusIsTrueOrderByProjectPriorityDesc(accountId);
            if(projects.isEmpty()){
            throw new EntityNotFoundException("accountId:" + accountId + "인 프로젝트가 없습니다.");
            }
            Project project = projects.get(0);
            Attendance attendance = myPageAttendanceRepository.findFirstByAccount_AccountIdOrderByAttendanceGoWorkDesc(accountId);
            MainPageDTO mainPageDTO = new MainPageDTO();
            modelMapper.map(account, mainPageDTO);
            modelMapper.map(project, mainPageDTO);

            return mainPageDTO;
    }
}

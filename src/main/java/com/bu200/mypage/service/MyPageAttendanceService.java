package com.bu200.mypage.service;

import com.bu200.login.entity.Account;
import com.bu200.login.entity.Attendance;
import com.bu200.login.entity.FindAccount;
import com.bu200.login.repository.AccountRepository;
import com.bu200.mypage.repository.MyPageAttendanceRepository;
import com.bu200.mypage.service.Dtos.MyPageAttendanceGoRequestDTO;
import com.bu200.mypage.service.Dtos.MyPageAttendanceLeaveRequestDTO;
import com.bu200.mypage.service.Dtos.MyPageAttendanceResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class MyPageAttendanceService {
    private final MyPageAttendanceRepository myPageAttendanceRepository;
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    public MyPageAttendanceService(MyPageAttendanceRepository myPageAttendanceRepository, AccountRepository accountRepository, ModelMapper modelMapper) {
        this.myPageAttendanceRepository = myPageAttendanceRepository;
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public MyPageAttendanceResponseDTO myPageAttendanceGoService(String accountId, MyPageAttendanceGoRequestDTO myPageAttendanceGoRequestDTO) {
        /*
        마지막 시간 attendance table에서 불러오기
        MyPageAttendanceGoRequestDTO의 attendanceDate와
        1. 다르면 통과
        -새로운 필드로 저장
        -후에 leavework update
        2. 같으면 이미 출근했읍니다. exception
        즉, 출근 버튼은 새로운 필드를 만들거나, 이미 출근했습니다.
         */
        Attendance findAttendance = myPageAttendanceRepository.findFirstByAccount_AccountIdOrderByAttendanceGoWorkDesc(accountId);   //attendance테이블에서 마지막 출근 시간을 가져온다.
        Attendance saveAttendance = new Attendance();                                                                                   //저장할 엔티티를 생성
        Account account = accountRepository.findByAccountId(accountId);                                                                 //새로운 attendance 필드를 추가하기 위해 account엔티티를 가져온다.\
        MyPageAttendanceResponseDTO myPageAttendanceResponseDTO = new MyPageAttendanceResponseDTO();                                    //컨트롤러로 리턴할 엔티티를 생성

        if(findAttendance == null) {                        //처음 출근하는 경우 새로 만들어 저장
            modelMapper.map(myPageAttendanceGoRequestDTO, saveAttendance);
            saveAttendance.setAccount(account);

            myPageAttendanceRepository.save(saveAttendance); //저장
            modelMapper.map(saveAttendance, myPageAttendanceResponseDTO);   //반환 값 세팅

        }else{                                              //처음이 아닌 경우
            if(findAttendance.getAttendanceLeaveWork() == null){
                //마지막으로 출근한 날의 퇴근 정보가 없으면(퇴근 정보가 없이, 출근 요청이 들어온 경우), 이미 출근 했습니다!출력
            }else{
                modelMapper.map(myPageAttendanceGoRequestDTO, saveAttendance);
                saveAttendance.setAccount(account);
                myPageAttendanceRepository.save(saveAttendance);
                modelMapper.map(saveAttendance, myPageAttendanceResponseDTO);
            }
        }
        //현재 Attendance에 date, status, go_work, account를 저장후 dto에 담아 return
        return myPageAttendanceResponseDTO;
    }


    @Transactional
    public MyPageAttendanceResponseDTO myPageAttendanceLeaveService(String accountId, MyPageAttendanceLeaveRequestDTO myPageAttendanceLeaveRequestDTO){
        //
        Attendance findAttendance = myPageAttendanceRepository.findFirstByAccount_AccountIdOrderByAttendanceGoWorkDesc(accountId);
        MyPageAttendanceResponseDTO myPageAttendanceResponseDTO = new MyPageAttendanceResponseDTO();                                    //컨트롤러로 리턴할 엔티티를 생성
        if(findAttendance == null){
         //첫출근 즉, 출근부터해라(출근하세요출력)
        }else {
            if(findAttendance.getAttendanceLeaveWork() == null){
                modelMapper.map(myPageAttendanceLeaveRequestDTO, findAttendance);
                modelMapper.map(findAttendance, myPageAttendanceResponseDTO);
            }else{
                //이미 퇴근하셨습니다. 출력
            }

        }
        return myPageAttendanceResponseDTO;
    }
}

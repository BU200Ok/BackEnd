package com.bu200.mypage.service;

import com.bu200.mypage.service.Dtos.MyPageAttendanceRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyPageAttendanceService {



    @Transactional
    public boolean myPageAttendanceCheck(String username, MyPageAttendanceRequestDTO myPageAttendanceRequestDTO) {
        /*
        마지막 시간 attendance확인
        status확인
        마지막 시간 attendance의 status와 dto의 status가 갖다면, 에러발생
         */
        return true;
    }
}

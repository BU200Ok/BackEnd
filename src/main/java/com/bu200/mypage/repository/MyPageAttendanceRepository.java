package com.bu200.mypage.repository;

import com.bu200.login.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyPageAttendanceRepository extends JpaRepository<Attendance, Long> {

    //출근 시간이 제일 최근인 필드를 찾는 쿼리
    Attendance findFirstByAccount_AccountIdOrderByAttendanceGoWorkDesc(String accountId);
}

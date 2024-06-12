package com.bu200.attendance.repository;

import com.bu200.attendance.entity.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Page<Attendance> findAllByAccountCode(Long aLong, Pageable pageable);
    int countByAttendanceStatusAndAccountCode(String status,Long accountCode);
}

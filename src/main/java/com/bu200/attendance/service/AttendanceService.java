package com.bu200.attendance.service;

import com.bu200.attendance.dto.AnnualDTO;
import com.bu200.attendance.dto.AttendanceStatusDTO;
import com.bu200.attendance.entity.Annual;
import com.bu200.attendance.entity.Attendance;
import com.bu200.attendance.repository.AnnualRepository;
import com.bu200.attendance.repository.AttendanceRepository;
import com.bu200.login.repository.UserRepository;
import com.bu200.security.dto.CustomUserDetails;
import com.bu200.security.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;
    private final AnnualRepository annualRepository;

    public AttendanceService(AttendanceRepository attendanceRepository, UserRepository userRepository, AnnualRepository annualRepository) {
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
        this.annualRepository = annualRepository;
    }

    public Page<Attendance> findByAccountCode(Integer code,int page) {
        Pageable pageable = PageRequest.of(page,20);
        return attendanceRepository.findAllByAccountCode(Long.valueOf(code),pageable);
    }

    public AttendanceStatusDTO findMyAttendanceStatus(CustomUserDetails user) {
        User userEntity = userRepository.findById(user.getCode()).orElseThrow();
        LocalDate joinDate = userEntity.getAccountJoinDate();
        AttendanceStatusDTO attendanceStatusDTO = new AttendanceStatusDTO();
        System.out.println(joinDate);
        int baseAnnualLeave = 15;
        LocalDate currentDate = LocalDate.now();
        int yearsEmployed = (int) ChronoUnit.YEARS.between(joinDate, currentDate);  //일한지 몇 년?
        int additionalLeave = 0;
        if (yearsEmployed >= 3) {
            additionalLeave += 1;
            int extraYears = yearsEmployed - 3;
            additionalLeave += (extraYears / 2);
        }
        List<Annual> annual = annualRepository.findAllByYear(Long.valueOf(user.getCode()));
        List<AnnualDTO> annualDTOS = new ArrayList<>();
        int totalUsedDay = 0;
        for(Annual a : annual){
            int usedDay = (int) ChronoUnit.DAYS.between(a.getAnnualStart(),a.getAnnualEnd());
            totalUsedDay += usedDay;
            AnnualDTO annualDTO = new AnnualDTO();
            annualDTO.setAnnualStart(a.getAnnualStart());
            annualDTO.setAnnualEnd(a.getAnnualEnd());
            annualDTO.setAnnualType(a.getAnnualType());
            annualDTO.setAnnualCount(usedDay);
            annualDTOS.add(annualDTO);
        }
        attendanceStatusDTO.setRemainingAnnualLeave((baseAnnualLeave+additionalLeave)-totalUsedDay);
        attendanceStatusDTO.setTotalCommute(attendanceRepository.countByAttendanceStatusAndAccountCode("출근",Long.valueOf(user.getCode())));
        attendanceStatusDTO.setTotalLateness(attendanceRepository.countByAttendanceStatusAndAccountCode("지각",Long.valueOf(user.getCode())));
        attendanceStatusDTO.setAnnuals(annualDTOS);
        attendanceStatusDTO.setTotalAnnualOfYear(baseAnnualLeave+additionalLeave);
        return attendanceStatusDTO;
    }
}

package com.bu200.attendance.entity;

import com.bu200.login.entity.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "AttendanceEntity")
@Table(name = "attendance")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_code")
    private Long attendanceCode;
    @Column(name = "attendance_date")
    private LocalDate attendanceDate;
    @Column(name = "account_code")
    private Long accountCode;
    @Column(name = "attendance_go_work")
    private LocalDateTime attendanceGoWork;
    @Column(name = "attendance_leave_work")
    private LocalDateTime attendanceLeaveWork;
    @Column(name = "attendance_status")
    private String attendanceStatus;
}

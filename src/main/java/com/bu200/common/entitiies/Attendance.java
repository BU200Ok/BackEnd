package com.bu200.common.entitiies;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity @Table(name = "attendance")
@Getter @RequiredArgsConstructor
public class Attendance {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "attendance_code")
    private Long attendanceCode;

    @Column(name = "attendance_go_work")
    private LocalDateTime attendanceGoWork;

    @Column(name = "attendance_leave_work")
    private LocalDateTime attendanceLeaveWork;

    @Column(name = "attendance_status")
    private String attendanceStatus;

    @OneToOne
    @JoinColumn(name = "account_code")
    private Account accountCode;
}

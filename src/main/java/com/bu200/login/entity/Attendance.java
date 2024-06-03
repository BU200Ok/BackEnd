package com.bu200.login.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Table(name = "attendance")
@Getter @Setter @NoArgsConstructor
public class Attendance {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_code")
    private Long attendanceCode;

    @Column(name = "attendance_date")
    private LocalDate attendanceDate;

    @Column(name = "attendance_leave_work")
    private LocalDateTime attendanceLeaveWork;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_code")
    private Account account;

    @Column(name = "attendance_go_work")
    private LocalDateTime attendanceGoWork;

    @Column(name = "attendance_status")
    private String attendanceStatus;
}

package com.bu200.login.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity @Table(name = "department")
@Getter @RequiredArgsConstructor
public class Department {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_code")
    private Long departmentCode;

    @Column(name = "department_name")
    private String departmentName;
}

package com.bu200.common.entitiies;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity @Table(name = "project_management")
@Getter @RequiredArgsConstructor
public class ProjectManagement {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_management_code")
    private Long projectManagementCode;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_start")
    private LocalDateTime projectStart;

    @Column(name = "project_end")
    private LocalDateTime projectEnd;

    @Column(name = "project_status")
    private String projectStatus;

    @Column(name = "project_description")
    private String projectDescription;

    @Column(name = "project_open_status")
    private String projectOpenStatus;

    @Column(name = "project_priority")
    private Integer projectPriority;

    @ManyToOne
    @JoinColumn(name = "account_code")
    private Account accountCode;
}

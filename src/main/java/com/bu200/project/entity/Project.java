package com.bu200.project.entity;

import com.bu200.login.entity.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity @Table(name = "project")
@Getter @Setter @NoArgsConstructor
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_code")
    private Long projectCode;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_start")
    private Date projectStart;

    @Column(name = "project_end")
    private Date projectEnd;

    @Column(name = "project_priority")
    private Integer projectPriority;

    @Column(name = "project_open_status")
    private String projectOpenStatus;

    @Column(name = "project_operations")
    private String projectOperations;

    @Column(name = "project_description")
    private String projectDescription;

    @Column(name = "project_status")
    private String projectStatus;

    @ManyToOne
    @JoinColumn(name = "account_code")
    private Account account;
}

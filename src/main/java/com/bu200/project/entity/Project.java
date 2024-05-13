package com.bu200.project.entity;


import com.bu200.login.entity.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity@Table(name = "project")
@Setter@Getter@NoArgsConstructor@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_code")
    private Long projectCode;
    @Column(name = "project_name")
    private String projectName;
    @Column(name = "project_start")
    private Date projectStart;
    @Column(name = "project_end")
    private Date projectEnd;
    @Column(name = "project_status")
    private String projectStatus;
    @Column(name = "project_description")
    private String projectDescription;
    @Column(name = "project_open_status")
    private String projectOpenStatus;
    @Column(name = "project_priority")
    private Integer projectPriority;

    @JoinColumn(name = "account_code")
    @OneToOne
    private Account account;

    @OneToMany(mappedBy = "projectCode")
    private List<ProjectMember> member;
}

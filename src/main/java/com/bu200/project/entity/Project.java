package com.bu200.project.entity;

import com.bu200.login.entity.Account;
import com.bu200.login.entity.Team;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity @Table(name = "project")
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_code")
    private Long projectCode;

    @Column(name = "project_name")
    private String projectName;

    @CreationTimestamp
    @Column(name = "project_start")
    private LocalDate projectStart;

    @Column(name = "project_end")
    private LocalDate projectEnd;

    @Column(name = "project_priority")
    private Integer projectPriority;

    @Column(name = "project_open_status")
    private boolean projectOpenStatus;

    @Column(name = "project_description")
    private String projectDescription;

    @Column(name = "project_status")
    private String projectStatus;

    @ManyToOne(fetch = FetchType.LAZY)  //작성자 즉, 관리자
    @JoinColumn(name = "account_code")
    private Account account;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<AccountProject> accountProjects;
}

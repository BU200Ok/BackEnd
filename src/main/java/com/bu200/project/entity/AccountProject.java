package com.bu200.project.entity;

import com.bu200.login.entity.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "account_project")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AccountProject {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_project_code")
    private Long accountProjectCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_code")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_code")
    private Project project;
}

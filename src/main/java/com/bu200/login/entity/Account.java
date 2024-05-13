package com.bu200.login.entity;

import com.bu200.project.entity.Project;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "account")
@Getter
@Setter
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_code")
    private Long accountCode;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "account_password")
    private String accountPassword;

    @Column(name = "account_role")
    private String accountRole;

    @Column(name = "account_email")
    private String accountEmail;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_address")
    private String accountAddress;

    @Column(name = "account_join_date")
    private Date accountJoinDate;

    @Column(name = "account_position")
    private String accountPosition;

    @Column(name = "account_resign_date")
    private Date accountResignDate;

    @ManyToOne
    @JoinColumn(name = "team_code")
    private Team team;

}

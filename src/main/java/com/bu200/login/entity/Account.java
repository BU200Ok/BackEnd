package com.bu200.login.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "account")
@Getter
@Setter
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_code")
    private Integer accountCode;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_code")
    private Team team;

}

package com.bu200.login.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "account")
@RequiredArgsConstructor
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_code")
    private Long accountCode;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "account_password")
    private String accountPassword;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_email")
    private String accountEmail;

//    @Column(name = "account_address")
//    private String accountAddress;

    @Column(name = "account_join_date")
    @Temporal(TemporalType.DATE)   //java.util.Date
    private Date accountJoinDate;

//    @Column(name = "account_resign_date")
//    @Temporal(TemporalType.DATE)
//    private Date accountResignDate;

    @Column(name = "account_position")
    private String accountPostion;

//    @Column(name = "account_right")
//    private String accountRight;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "team_code")
//    private Team teamCode;
}

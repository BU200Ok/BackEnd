package com.bu200.security.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "account")
@Getter
@Setter
@ToString
public class User {
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
    private LocalDate accountJoinDate;
    @Column(name = "account_position")
    private String accountPosition;
    @Column(name = "account_resign_date")
    private Date accountResignDate;
}

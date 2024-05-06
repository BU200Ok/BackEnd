package com.bu200.security.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "account")
@Getter
@Setter
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
}

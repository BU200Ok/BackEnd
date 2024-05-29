package com.bu200.login.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity@Table(name = "find_account")
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class FindAccount {
    @Id
    @Column(name = "find_account_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    @Column(name = "find_account_ip")
    private String ip;
    @Column(name = "find_account_secret_code")
    private Integer secretCode;
}

package com.bu200.common.entitiies;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Entity @Table(name = "annual")
@Getter @RequiredArgsConstructor
public class Annual {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "annual_code")
    private Long annualCode;

    @Column(name = "annual_type")
    private String annualType;

    @Temporal(TemporalType.DATE)
    @Column(name = "annual_start")
    private Date annualStart;

    @Temporal(TemporalType.DATE)
    @Column(name = "annual_end")
    private Date annualEnd;

    @Column(name = "annual_status")
    private String annualStatus;

    @OneToOne
    @JoinColumn(name = "account_code")
    private Account accountCode;
}

package com.bu200.attendance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "annual")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Annual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "annual_code")
    private Long annualCode;
    @Column(name = "annual_type")
    private String annualType;
    @Column(name = "annual_start")
    private LocalDate annualStart;
    @Column(name = "annual_end")
    private LocalDate annualEnd;
    @Column(name = "account_code")
    private Long accountCode;
}

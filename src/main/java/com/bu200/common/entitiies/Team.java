package com.bu200.common.entitiies;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity @Table(name = "TEAM")
@Getter @RequiredArgsConstructor
public class Team {
    @Id @Column(name = "team_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long teamCode;

    @Column(name = "team_name")
    private String teamName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_code")
    @Column(name = "department_code")
    private Department departmentCode;
}

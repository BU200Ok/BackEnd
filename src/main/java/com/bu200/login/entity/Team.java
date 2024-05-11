package com.bu200.login.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "team")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id @Column(name = "team_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long teamCode;

    @Column(name = "team_name")
    private String teamName;

    @ManyToOne
    @JoinColumn(name = "department_code")
    private Department department;
}

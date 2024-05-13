package com.bu200.project.entity;

import com.bu200.login.entity.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "project_member")
@AllArgsConstructor
@NoArgsConstructor
@Setter@Getter
public class ProjectMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_member_code")
    private Long projectMemberCode;
    @Column(name = "project_code")
    private Long projectCode;

    @JoinColumn(name = "account_code")
    @ManyToOne
    private Account accountCode;
}

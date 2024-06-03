package com.bu200.project.entity;

import com.bu200.login.entity.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "account_task")
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class AccountTask {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_task_code")
    private Long taskCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_code")
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_code")
    private Account account;
}

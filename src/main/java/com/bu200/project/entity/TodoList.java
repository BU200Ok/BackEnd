package com.bu200.project.entity;

import com.bu200.login.entity.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity @Table(name = "todolist")
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class TodoList {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todolist_code")
    private Long todoListCode;

    @Column(name = "todolist_content")
    private String todoListContent;

    @CreationTimestamp
    @Column(name = "todolist_start")
    private Timestamp todoListStart;

    @Column(name = "todolist_end")
    private LocalDate todoListEnd;

    @Column(name = "todolist_status")
    private Boolean todoListStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_code")
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_code")
    private Account account;
}

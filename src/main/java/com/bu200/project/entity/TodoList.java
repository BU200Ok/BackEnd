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

@Entity @Table(name = "todo_list")
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class TodoList {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_list_code")
    private Long todoListCode;

    @Column(name = "todo_list_content")
    private String todoListContent;

    @CreationTimestamp
    @Column(name = "todo_list_start")
    private Timestamp todoListStart;

    @Column(name = "todo_list_end")
    private LocalDate todoListEnd;

    @Column(name = "todo_list_status")
    private Boolean todoListStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_code")
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_code")
    private Account account;
}

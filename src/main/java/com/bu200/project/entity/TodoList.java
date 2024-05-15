package com.bu200.project.entity;

import com.bu200.login.entity.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity @Table(name = "todo_list")
@Getter @Setter @NoArgsConstructor
public class TodoList {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_list_code")
    private Long todoListCode;

    @Column(name = "todo_list_name")
    private String todoListName;

    @Column(name = "todo_list_detail")
    private String todoListDetail;

    @Column(name = "todo_list_start")
    private LocalDate todoListStart;

    @Column(name = "todo_list_end")
    private LocalDate todoListEnd;

    @Column(name = "todo_list_done")
    private boolean todoListDone;

    @JoinColumn(name = "project_forum_code")
    private Long projectForumCode;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_code")
    private Account account;
}

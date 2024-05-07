package com.bu200.common.entitiies;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Entity @Table(name = "todo_list")
@Getter @RequiredArgsConstructor
public class ToDoList {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "todo_list_code")
    private Long todoListCode;

    @Column(name = "todo_list_content")
    private String todoListContent;

    @Temporal(TemporalType.DATE)
    @Column(name = "todo_list_start")
    private Date todoListStart;

    @Temporal(TemporalType.DATE)
    @Column(name = "todo_list_end")
    private Date todoListEnd;

    @Column(name = "todo_list_status")
    private boolean todoListStatus;




}

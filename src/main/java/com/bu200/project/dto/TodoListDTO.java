package com.bu200.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter@Setter@NoArgsConstructor
public class TodoListDTO {
    public TodoListDTO(Long todoListCode, String todoListContent, Timestamp todoListStart, LocalDate todoListEnd, boolean todoListStatus, String accountName) {
        this.todoListCode = todoListCode;
        this.todoListContent = todoListContent;
        this.todoListStart = todoListStart;
        this.todoListEnd = todoListEnd;
        this.todoListStatus = todoListStatus;
        this.accountName = accountName;
    }

    private Long todoListCode;
    private String todoListContent;
    private Timestamp todoListStart;
    private LocalDate todoListEnd;
    private boolean todoListStatus;

    private String accountName;
}

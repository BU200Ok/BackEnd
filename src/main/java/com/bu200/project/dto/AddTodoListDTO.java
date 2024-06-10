package com.bu200.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter@Setter@NoArgsConstructor
public class AddTodoListDTO {   //받아야하는 부분 o
    private Long todoListCode;
    private String todoListContent; //o
    private Timestamp todoListStart;
    private LocalDate todoListEnd;  //o
    private boolean todoListStatus = true;

    public AddTodoListDTO(Long todoListCode, String todoListContent, Timestamp todoListStart, LocalDate todoListEnd, boolean todoListStatus) {
        this.todoListCode = todoListCode;
        this.todoListContent = todoListContent;
        this.todoListStart = todoListStart;
        this.todoListEnd = todoListEnd;
        this.todoListStatus = todoListStatus;
    }
}

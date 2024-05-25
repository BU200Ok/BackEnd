package com.bu200.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class TodoListDTO {
    private Long todoListCode;
    private String todoListContent;
    private Timestamp todoListStart;
    private LocalDate todoListEnd;
    private boolean todoListStatus;

    private String accountName;
}

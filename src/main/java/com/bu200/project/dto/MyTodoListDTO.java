package com.bu200.project.dto;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class MyTodoListDTO {
    private Long todoListCode;
    private String todoListName;
    private String todoListDetail;
    private LocalDate todoListStart;
    private LocalDate todoListEnd;
}

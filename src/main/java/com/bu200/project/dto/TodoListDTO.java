package com.bu200.project.dto;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class TodoListDTO {
    private Long todoListCode;
    private String todoListName;
    private String todoListDetail;
    private LocalDate todoListStart;
    private LocalDate todoListEnd;  //end는 project가 끝나는 날
}

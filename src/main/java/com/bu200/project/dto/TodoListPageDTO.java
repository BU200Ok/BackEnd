package com.bu200.project.dto;

import com.bu200.project.entity.ProjectMember;
import com.bu200.project.entity.TodoList;
import lombok.*;

import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class TodoListPageDTO {
    List<TodoListDTO> todoListDTOS;
    List<MyTodoListDTO> myTodoListDTOS;
}

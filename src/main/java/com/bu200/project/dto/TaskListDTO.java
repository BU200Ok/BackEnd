package com.bu200.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class TaskListDTO {
    List<String> todoTasks;
    List<String> inProgressTasks;
    List<String> doneTasks;
}

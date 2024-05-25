package com.bu200.project.repository;

import com.bu200.project.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {
    List<TodoList> getAllByTask_TaskCodeAndTodoListStatusIsTrue(Long taskCode);

    TodoList getTodoListByTodoListContentAndTodoListStatusIsTrue(String todoListContent);

    List<TodoList> getAllByTask_TaskCodeAndTodoListStatusIsTrueAndAccount_AccountCode(Long taskCode, Long accountCode);


    TodoList getTodoListByTodoListCode(Long todoListCode);
}

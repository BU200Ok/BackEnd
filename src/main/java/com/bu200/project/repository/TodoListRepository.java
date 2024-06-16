package com.bu200.project.repository;

import com.bu200.project.dto.TodoListDTO;
import com.bu200.project.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Long> {

    @Query("select new com.bu200.project.dto.TodoListDTO" +
            "(tl.todoListCode, tl.todoListContent, tl.todoListStart, tl.todoListEnd, tl.todoListStatus" +
            ", a.accountName) from TodoList tl " +
            "join tl.account a " +
            "where tl.todoListStatus = true " +
            "and tl.task.taskCode = :taskCode")
    List<TodoListDTO> getTodoListDTOS(@Param("taskCode")Long taskCode);

    @Query("select new com.bu200.project.dto.TodoListDTO" +
            "(tl.todoListCode, tl.todoListContent, tl.todoListStart, tl.todoListEnd, tl.todoListStatus" +
            ", a.accountName) from TodoList tl " +
            "join tl.account a " +
            "where tl.todoListStatus = true " +
            "and tl.task.taskCode = :taskCode " +
            "and tl.account.accountCode = :accountCode")
    List<TodoListDTO> getMyTodoListsDTOS(@Param("taskCode") Long taskCode,
                                         @Param("accountCode") Long accountCode);

    TodoList findTodoListByTask_TaskCodeAndTodoListContent(Long taskCode, String todoListContent);
}

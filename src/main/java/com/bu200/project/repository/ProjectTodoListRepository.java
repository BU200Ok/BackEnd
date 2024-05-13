package com.bu200.project.repository;

import com.bu200.project.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectTodoListRepository extends JpaRepository<TodoList, Long> {
    List<TodoList> findTodoListsByProject_ProjectCodeAndTodoListDoneFalse(Long projectCode);
   //프로젝트에 속한, 프로젝트의 accoutid가 내꺼, done이 false인것
    List<TodoList> findTodoListsByProject_ProjectCodeAndTodoListDoneFalseAndAccount_AccountCode(Long projectCode, Long accountCode);

    TodoList findByAccount_AccountCodeAndProject_ProjectCodeAndTodoListNameAndTodoListDoneFalse(Long accountCode, Long projectCode, String todoListName);

    TodoList findByTodoListNameAndTodoListDoneFalse(String todoListName);
}

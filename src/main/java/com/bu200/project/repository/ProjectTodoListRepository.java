package com.bu200.project.repository;

import com.bu200.project.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectTodoListRepository extends JpaRepository<TodoList, Long> {
    //아직 끝내지 못한 모든 투두리스트를 가져온다.
    List<TodoList> findTodoListsByProjectForumCodeAndTodoListDoneFalse(Long projectForumCode);
    //아직 끝내지 못한 나의 모든 투두리스트를 가져온다.
    List<TodoList> findTodoListsByProjectForumCodeAndAccount_AccountCodeAndTodoListDoneFalse(Long projectForumCode, Long AccountCode);

    //
    TodoList findTodoListByProjectForumCodeAndTodoListNameAndTodoListDoneFalse(Long projectForumCode, String todoListName);

    TodoList findByTodoListCode(Long todoListCode);
}

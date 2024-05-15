package com.bu200.project.service;

import com.bu200.exception.TodoListExistException;
import com.bu200.login.entity.Account;
import com.bu200.project.dto.MyTodoListDTO;
import com.bu200.project.dto.RemoveTodoListDTO;
import com.bu200.project.dto.TodoListDTO;
import com.bu200.project.dto.TodoListPageDTO;
import com.bu200.project.entity.ProjectForum;
import com.bu200.project.entity.TodoList;
import com.bu200.project.repository.ProjectTodoListRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoListService {
    private final ProjectTodoListRepository projectTodoListRepository;
    private final ModelMapper modelMapper;

    public TodoListService(ProjectTodoListRepository projectTodoListRepository, ModelMapper modelMapper) {
        this.projectTodoListRepository = projectTodoListRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public TodoListPageDTO getTodoListPageDTO(Long accountCode, Long projectCode, Long projectForumCode) {
        List<TodoList> allTodoList = projectTodoListRepository.findTodoListsByProjectForumCodeAndTodoListDoneFalse(projectForumCode);
        List<TodoListDTO> todoListDTOS = allTodoList.stream()
                .map(todoList-> modelMapper.map(todoList, TodoListDTO.class))
                .collect(Collectors.toList());

        List<TodoList> myTodoList = projectTodoListRepository.findTodoListsByProjectForumCodeAndAccount_AccountCodeAndTodoListDoneFalse(projectForumCode, accountCode);
        List<MyTodoListDTO> myTodoListDTOS = myTodoList.stream()
                .map(todoList -> modelMapper.map(todoList, MyTodoListDTO.class))
                .collect(Collectors.toList());

        return new TodoListPageDTO(todoListDTOS, myTodoListDTOS);
    }

    @Transactional
    public TodoListDTO addTodoList(Long accountCode, Long projectForumCode, TodoListDTO requestTodoListDTO) {
        //투두리스트 이름 중복검사
        System.out.println(2);
        TodoList todoList = projectTodoListRepository.findTodoListByProjectForumCodeAndTodoListNameAndTodoListDoneFalse(projectForumCode,requestTodoListDTO.getTodoListName());
        if(todoList != null){
            throw new TodoListExistException("중복된 이름의 투두리스트가 존재합니다.");
        }


        TodoList saveTodoList = modelMapper.map(requestTodoListDTO, TodoList.class);//code, name, detail, start, end
        saveTodoList.setProjectForumCode(projectForumCode);
        Account account = new Account();
        account.setAccountCode(accountCode);
        saveTodoList.setAccount(account);

        return modelMapper.map(projectTodoListRepository.save(saveTodoList), TodoListDTO.class);
    }

    @Transactional
    public TodoListDTO removeTodoList(RemoveTodoListDTO removeTodoListDTO) {
        TodoList todoList = projectTodoListRepository.findByTodoListCode(removeTodoListDTO.getTodoListCode());
        todoList.setTodoListDone(true);
        return modelMapper.map(todoList, TodoListDTO.class);
    }
}

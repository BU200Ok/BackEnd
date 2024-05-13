package com.bu200.project.service;

import com.bu200.common.response.ResponseDTO;
import com.bu200.login.entity.Account;
import com.bu200.login.repository.AccountRepository;
import com.bu200.project.dto.*;
import com.bu200.project.entity.Project;
import com.bu200.project.entity.TodoList;
import com.bu200.project.repository.ProjectMemberRepository;
import com.bu200.project.repository.ProjectRepository;
import com.bu200.project.repository.ProjectTodoListRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectTodoListService {
    private final ProjectTodoListRepository projectTodoListRepository;
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private final ProjectRepository projectRepository;

    public ProjectTodoListService(ProjectTodoListRepository projectTodoListRepository, AccountRepository accountRepository, ProjectMemberRepository projectMemberRepository, ModelMapper modelMapper, ProjectRepository projectRepository) {
        this.projectTodoListRepository = projectTodoListRepository;
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
        this.projectRepository = projectRepository;
    }

    @Transactional(readOnly = true)
    public TodoListPageDTO getProjectTodoLists(Long projectCode, Long accountCode) {
        //이 projectCode인 아직 진행중인 모든 todoList들을 가져온다.
        List<TodoList> todoLists = projectTodoListRepository.findTodoListsByProject_ProjectCodeAndTodoListDoneFalse(projectCode);
        List<TodoListDTO> todoListDTOS = todoLists.stream()
                .map(todoList -> modelMapper.map(todoList, TodoListDTO.class))
                .collect(Collectors.toList());
        //MyTodoList들을 찾아온다.
        List<TodoList> myTodoLists = projectTodoListRepository.findTodoListsByProject_ProjectCodeAndTodoListDoneFalseAndAccount_AccountCode(projectCode,accountCode);
        List<MyTodoListDTO> myTodoListDTOS = myTodoLists.stream()
                .map(todoList -> modelMapper.map(todoList, MyTodoListDTO.class))
                .collect(Collectors.toList());

        return new TodoListPageDTO(todoListDTOS, myTodoListDTOS);
    }

    @Transactional
    public TodoListDTO addProjectTodoList(Long projectCode, Long accountCode, AddTodoListRequestDTO addTodoListRequestDTO){
        //중복검사
        TodoList existTodoList = projectTodoListRepository.findByTodoListNameAndTodoListDoneFalse(addTodoListRequestDTO.getTodoListName());
        if(existTodoList != null) {
            System.out.println("중복된 아이디 존재");
        }
            TodoList todoList = new TodoList();
            modelMapper.map(addTodoListRequestDTO, todoList);   //name, detail, start, done
            Account account = accountRepository.findByAccountCode(accountCode);
            todoList.setAccount(account);
            Project project = projectRepository.findByProjectCode(projectCode);
            todoList.setProject(project);
            todoList = projectTodoListRepository.save(todoList);

            TodoListDTO todoListDTO = modelMapper.map(todoList, TodoListDTO.class);

            return todoListDTO;

    }

    public TodoListDTO removeProjectTodoList(Long accountCode, Long projectCode, RemoveTodoListDTO removeTodoListDTO) {
        TodoList todoList = projectTodoListRepository.findByAccount_AccountCodeAndProject_ProjectCodeAndTodoListNameAndTodoListDoneFalse(accountCode,projectCode,removeTodoListDTO.getTodoListName());
        todoList.setTodoListDone(true);
        TodoListDTO todoListDTO = modelMapper.map(todoList, TodoListDTO.class);
        return todoListDTO;
    }
}
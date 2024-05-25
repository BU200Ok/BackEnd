package com.bu200.project.service;

import com.bu200.exception.TodoListExistException;
import com.bu200.login.entity.Account;
import com.bu200.login.repository.AccountRepository;
import com.bu200.project.dto.AddTodoListDTO;
import com.bu200.project.dto.RemoveTodoListDTO;
import com.bu200.project.dto.TodoListDTO;
import com.bu200.project.entity.Task;
import com.bu200.project.entity.TodoList;
import com.bu200.project.repository.TaskRepository;
import com.bu200.project.repository.TodoListRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectTaskTodoListService {
    private final TodoListRepository todoListRepository;
    private final TaskRepository taskRepository;
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public ProjectTaskTodoListService(TodoListRepository todoListRepository, TaskRepository taskRepository, AccountRepository accountRepository, ModelMapper modelMapper) {
        this.todoListRepository = todoListRepository;
        this.taskRepository = taskRepository;
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public List<TodoListDTO> getAllTodoList(Long taskCode){
        List<TodoList> todoLists = todoListRepository.getAllByTask_TaskCodeAndTodoListStatusIsTrue(taskCode);

        return todoLists.stream()
                .map(todoList -> {
                    TodoListDTO todoListDTO = modelMapper.map(todoList, TodoListDTO.class);
                    todoListDTO.setAccountName(todoList.getAccount().getAccountName());
                    return todoListDTO;
                }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TodoListDTO> getMyTodoList(Long taskCode, Long accountCode){
        List<TodoList> todoLists = todoListRepository.getAllByTask_TaskCodeAndTodoListStatusIsTrueAndAccount_AccountCode(taskCode, accountCode);

        return todoLists.stream()
                .map(todoList -> {
                    TodoListDTO todoListDTO = modelMapper.map(todoList, TodoListDTO.class);
                    todoListDTO.setAccountName(todoList.getAccount().getAccountName());
                    return todoListDTO;
                }).collect(Collectors.toList());
    }

    @Transactional
    public AddTodoListDTO addMyTodoList(Long accountCode, Long taskCode, AddTodoListDTO addTodoListDTO){
        TodoList findTodoList = todoListRepository.getTodoListByTodoListContentAndTodoListStatusIsTrue(addTodoListDTO.getTodoListContent());
        if(findTodoList != null){
            throw new TodoListExistException("이미 똑같은 투두리스트 존재");
        }

        Account findAccount = accountRepository.findByAccountCode(accountCode);
        Task findTask = taskRepository.findByTaskCode(taskCode);
        TodoList saveTodoList = modelMapper.map(addTodoListDTO, TodoList.class);
        saveTodoList.setTask(findTask);
        saveTodoList.setAccount(findAccount);

        saveTodoList = todoListRepository.save(saveTodoList);

        return modelMapper.map(saveTodoList, AddTodoListDTO.class);
    }

    public RemoveTodoListDTO removeTodoList(RemoveTodoListDTO removeTodoListDTO) {
        TodoList findTodoList = todoListRepository.getTodoListByTodoListCode(removeTodoListDTO.getTodoListCode());
        findTodoList.setTodoListStatus(false);

        return modelMapper.map(findTodoList, RemoveTodoListDTO.class);
    }
}

package com.bu200.project.service;

import com.bu200.login.entity.Account;
import com.bu200.login.repository.AccountRepository;
import com.bu200.project.dto.AddTodoListDTO;
import com.bu200.project.dto.TodoListDTO;
import com.bu200.project.entity.Task;
import com.bu200.project.entity.TodoList;
import com.bu200.project.repository.TaskRepository;
import com.bu200.project.repository.TodoListRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TodoListService {
    private final TodoListRepository todoListRepository;
    private final ModelMapper modelMapper;
    private final TaskRepository taskRepository;
    private final AccountRepository accountRepository;

    public TodoListService(TodoListRepository todoListRepository, ModelMapper modelMapper, TaskRepository taskRepository, AccountRepository accountRepository) {
        this.todoListRepository = todoListRepository;
        this.modelMapper = modelMapper;
        this.taskRepository = taskRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional(readOnly = true)
    public List<TodoListDTO> getTodoLists(Long taskCode) {
        List<TodoListDTO> todoListDTOS = todoListRepository.getTodoListDTOS(taskCode);

        return todoListDTOS;
    }

    @Transactional(readOnly = true)
    public List<TodoListDTO> getMyTodoLists(Long taskCode, Long accountCode) {
        List<TodoListDTO> todoListDTOS = todoListRepository.getMyTodoListsDTOS(taskCode, accountCode);

        return todoListDTOS;
    }
    @Transactional
    public TodoListDTO addTodoList(Long taskCode, Long accountCode, AddTodoListDTO addTodoListDTO) {
        TodoList saveTodoList = modelMapper.map(addTodoListDTO, TodoList.class);
        Account saveAccount = accountRepository.findByAccountCode(accountCode);
        Task saveTask = taskRepository.findTaskByTaskCode(taskCode);
        saveTodoList.setAccount(saveAccount);
        saveTodoList.setTask(saveTask);

        saveTodoList = todoListRepository.save(saveTodoList);

        return modelMapper.map(saveTodoList, TodoListDTO.class);
    }

    public Boolean checkDuplicateTodoList(Long taskCode, AddTodoListDTO addTodoListDTO){
        TodoList findTodoList = todoListRepository.findTodoListByTask_TaskCodeAndTodoListContent(taskCode, addTodoListDTO.getTodoListContent());
        if(findTodoList != null){
            return true;
        }
        return false;
    }
}

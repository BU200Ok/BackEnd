package com.bu200.project.service;

import com.bu200.login.entity.Account;
import com.bu200.login.repository.AccountRepository;
import com.bu200.project.dto.AddTaskDTO;
import com.bu200.project.dto.TaskDTO;
import com.bu200.project.dto.TaskListDTO;
import com.bu200.project.entity.AccountProject;
import com.bu200.project.entity.AccountTask;
import com.bu200.project.entity.Project;
import com.bu200.project.entity.Task;
import com.bu200.project.repository.AccountTaskRepository;
import com.bu200.project.repository.ProjectRepository;
import com.bu200.project.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final ModelMapper modelMapper;
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final AccountTaskRepository accountTaskRepository;
    private final AccountRepository accountRepository;

    public TaskService(ModelMapper modelMapper, TaskRepository taskRepository, ProjectRepository projectRepository, AccountTaskRepository accountTaskRepository, AccountRepository accountRepository) {
        this.modelMapper = modelMapper;
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.accountTaskRepository = accountTaskRepository;
        this.accountRepository = accountRepository;
    }


    @Transactional(readOnly = true)
    public TaskListDTO getTaskList(Long projectCode, Integer taskType) {
        List<String> todoTasks = taskRepository.findTaskNames(projectCode, "진행예정", taskType);
        List<String> inProgressTasks = taskRepository.findTaskNames(projectCode, "진행중", taskType);
        List<String> doneTasks = taskRepository.findTaskNames(projectCode, "진행완료", taskType);

        return new TaskListDTO(todoTasks, inProgressTasks, doneTasks);
    }

    @Transactional(readOnly = true)
    public Page<TaskDTO> searchTask(Long projectCode, Integer taskType, String taskTypeDetail, String keyword, Pageable pageable) {
        Page<Long> taskCodes = taskRepository.findKeywordTaskCodes(projectCode, taskType, taskTypeDetail, keyword, pageable);

        List<Task> tasks = taskRepository.findAllTasks(taskCodes.getContent());

        List<TaskDTO> taskDTOS = tasks.stream()
                .map(task -> {
                    TaskDTO taskDTO = modelMapper.map(task, TaskDTO.class); //task정보를 맵핑
                    List<String> accountNames = task.getAccountTasks().stream()
                            .map(accountTask -> accountTask.getAccount().getAccountName())
                            .collect(Collectors.toList());
                    taskDTO.setAccountName(accountNames);
                    return taskDTO;
                }).collect(Collectors.toList());

        return new PageImpl<>(taskDTOS, pageable, taskCodes.getTotalElements());
    }

    //account_task저장
    //task 저장
    @Transactional
    public TaskDTO addTask(Long accountCode, Long projectCode, AddTaskDTO addTaskDTO) {
        Project saveProject = projectRepository.findByProjectCode(projectCode);

        Task saveTask = modelMapper.map(addTaskDTO, Task.class);
        saveTask.setProject(saveProject);

        saveTask = taskRepository.save(saveTask);


        Account saveAccount = accountRepository.findByAccountCode(accountCode);
        AccountTask accountTask = new AccountTask();
        accountTask.setAccount(saveAccount);
        accountTask.setTask(saveTask);
        accountTaskRepository.save(accountTask);

        return modelMapper.map(saveTask, TaskDTO.class);
    }

    public boolean checkDuplicateTask(Long projectCode, AddTaskDTO addTaskDTO){
        Task findTask = taskRepository.findTaskByProject_ProjectCodeAndTaskTypeAndTaskDetailAndTaskName
                (projectCode, addTaskDTO.getTaskType(), addTaskDTO.getTaskDetail(), addTaskDTO.getTaskName());
        if(findTask != null){
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public Page<TaskDTO> allTask(Long projectCode, Integer taskType, String taskTypeDetail, Pageable pageable) {
        Page<Long> taskCodes = taskRepository.findAllTaskCodes(projectCode, taskType, taskTypeDetail, pageable);
        System.out.println(taskCodes.getContent());
        List<Task> tasks = taskRepository.findAllTasks(taskCodes.getContent());

        List<TaskDTO> taskDTOS = tasks.stream()
                .map(task -> {
                    TaskDTO taskDTO = modelMapper.map(task, TaskDTO.class); //task정보를 맵핑
                    List<String> accountNames = task.getAccountTasks().stream()
                            .map(accountTask -> accountTask.getAccount().getAccountName())
                            .collect(Collectors.toList());
                    taskDTO.setAccountName(accountNames);
                    return taskDTO;
                }).collect(Collectors.toList());

        return new PageImpl<>(taskDTOS, pageable, taskCodes.getTotalElements());
    }
}

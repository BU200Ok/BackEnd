package com.bu200.project.service;

import com.bu200.login.entity.Account;
import com.bu200.login.repository.AccountRepository;
import com.bu200.project.dto.AddTaskPostDTO;
import com.bu200.project.dto.AddTaskPostResponseDTO;
import com.bu200.project.dto.TaskPostDTO;
import com.bu200.project.entity.Task;
import com.bu200.project.entity.TaskPost;
import com.bu200.project.repository.TaskPostRepository;
import com.bu200.project.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectTaskPostService {
    private final ModelMapper modelMapper;
    private final TaskPostRepository taskPostRepository;
    private final AccountRepository accountRepository;
    private final TaskRepository taskRepository;
    public ProjectTaskPostService(ModelMapper modelMapper, TaskPostRepository taskPostRepository, AccountRepository accountRepository, TaskRepository taskRepository) {
        this.modelMapper = modelMapper;
        this.taskPostRepository = taskPostRepository;
        this.accountRepository = accountRepository;
        this.taskRepository = taskRepository;
    }

    @Transactional(readOnly = true)
    public List<TaskPostDTO> getTaskPost(Long projectCode, String taskType, Long taskCode) {
        List<TaskPost> taskPosts = taskPostRepository.findByProjectTaskTypeAndTaskCodeOrderByRecent(projectCode, taskType, taskCode);
        List<TaskPostDTO> taskPostDTOS = taskPosts.stream()
                .map(taskPost -> {
                    TaskPostDTO taskPostDTO = modelMapper.map(taskPost, TaskPostDTO.class);
                    taskPostDTO.setAccountName(taskPost.getAccount().getAccountName());
                    return taskPostDTO;
                })
                .collect(Collectors.toList());

        return taskPostDTOS;
    }

    public AddTaskPostResponseDTO addTaskPost(Long accountCode, Long taskCode, AddTaskPostDTO addTaskPostDTO) {
        TaskPost saveTaskPost = modelMapper.map(addTaskPostDTO, TaskPost.class);
        Account account = accountRepository.findByAccountCode(accountCode);
        Task task = taskRepository.findByTaskCode(taskCode);
        saveTaskPost.setTask(task);
        saveTaskPost.setAccount(account);

        saveTaskPost = taskPostRepository.save(saveTaskPost);
        AddTaskPostResponseDTO addTaskPostResponseDTO = modelMapper.map(saveTaskPost, AddTaskPostResponseDTO.class);
        addTaskPostResponseDTO.setAccountName(saveTaskPost.getAccount().getAccountName());

        return addTaskPostResponseDTO;
    }
}

package com.bu200.project.service;

import com.bu200.login.entity.Account;
import com.bu200.login.repository.AccountRepository;
import com.bu200.project.dto.AddTaskPostDTO;
import com.bu200.project.dto.TaskFileDTO;
import com.bu200.project.dto.TaskPostDTO;
import com.bu200.project.entity.Task;
import com.bu200.project.entity.TaskFile;
import com.bu200.project.entity.TaskPost;
import com.bu200.project.repository.TaskFileRepository;
import com.bu200.project.repository.TaskPostRepository;
import com.bu200.project.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskPostService {
    //taskCode만 받아오면 taskPost찾아올 수 있음
    private final TaskPostRepository taskPostRepository;
    private final TaskRepository taskRepository;
    private final TaskFileRepository taskFileRepository;
    private final ModelMapper modelMapper;
    private final AccountRepository accountRepository;


    public TaskPostService(TaskPostRepository taskPostRepository, TaskRepository taskRepository, TaskFileRepository taskFileRepository, ModelMapper modelMapper, AccountRepository accountRepository) {
        this.taskPostRepository = taskPostRepository;
        this.taskRepository = taskRepository;
        this.taskFileRepository = taskFileRepository;
        this.modelMapper = modelMapper;
        this.accountRepository = accountRepository;
    }

    //파일들과 함께 taskPost를 받아온다.
    //taskFile, taskPost, account, team
    @Transactional(readOnly = true)
    public List<TaskPostDTO> getTaskPost(Long taskCode){
        List<TaskPost> taskPosts = taskPostRepository.findAllTaskPost(taskCode);

        List<TaskPostDTO> taskPostDTOS = taskPosts.stream()
                .map(taskPost -> {
                    TaskPostDTO taskPostDTO = modelMapper.map(taskPost, TaskPostDTO.class);
                    taskPostDTO.setTaskPostTime(taskPost.getTaskPostTime().toLocalDateTime().toLocalDate());
                    modelMapper.map(taskPost.getAccount(), taskPostDTO);
                    modelMapper.map(taskPost.getAccount().getTeam(), taskPostDTO);
                    List<TaskFileDTO> taskFileDTOS = taskPost.getTaskFiles().stream()
                            .map(taskFile -> modelMapper.map(taskFile, TaskFileDTO.class))
                            .collect(Collectors.toList());
                    taskPostDTO.setTaskFileDTOS(taskFileDTOS);
                    return taskPostDTO;
                }).collect(Collectors.toList());

        return taskPostDTOS;
    }

    @Transactional
    public AddTaskPostDTO addTaskPost(Long accountCode, Long taskCode, AddTaskPostDTO taskPostDTO) {
        TaskPost saveTaskPost = modelMapper.map(taskPostDTO, TaskPost.class);
        Task saveTask = taskRepository.findTaskByTaskCode(taskCode);
        Account saveAccount = accountRepository.findByAccountCode(accountCode);

        saveTaskPost.setTask(saveTask);
        saveTaskPost.setAccount(saveAccount);
        saveTaskPost = taskPostRepository.save(saveTaskPost);

        modelMapper.map(saveTaskPost, taskPostDTO);

        return taskPostDTO;
    }
}

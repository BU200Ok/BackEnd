package com.bu200.project.service;

import com.bu200.project.dto.TaskFileDTO;
import com.bu200.project.dto.TaskPostDTO;
import com.bu200.project.entity.TaskPost;
import com.bu200.project.repository.TaskPostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskPostService {
    //taskCode만 받아오면 taskPost찾아올 수 있음
    private final TaskPostRepository taskPostRepository;
    private final ModelMapper modelMapper;


    public TaskPostService(TaskPostRepository taskPostRepository, ModelMapper modelMapper) {
        this.taskPostRepository = taskPostRepository;
        this.modelMapper = modelMapper;
    }

    //파일들과 함께 taskPost를 받아온다.
    //taskFile, taskPost, account, team
    @Transactional(readOnly = true)
    public List<TaskPostDTO> getTaskPost(Long taskCode){
        List<TaskPost> taskPosts = taskPostRepository.findAllTaskPost(taskCode);

        List<TaskPostDTO> taskPostDTOS = taskPosts.stream()
                .map(taskPost -> {
                    TaskPostDTO taskPostDTO = modelMapper.map(taskPost, TaskPostDTO.class);
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


}

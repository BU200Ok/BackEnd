package com.bu200.project.service;

import com.bu200.exception.TaskExistException;
import com.bu200.project.dto.TaskDTO;
import com.bu200.project.entity.Task;
import com.bu200.project.repository.ProjectRepository;
import com.bu200.project.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectTaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    public ProjectTaskService(TaskRepository taskRepository, ProjectRepository projectRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public Page<TaskDTO> getTask(Long projectCode, String taskType, Pageable pageable) {
        Page<Task> tasks;
        tasks = taskRepository.findAllByProject_ProjectCodeAndTaskType(projectCode, taskType, pageable);
        Page<TaskDTO> taskDTOS = tasks.map(task -> modelMapper.map(task, TaskDTO.class));
        return taskDTOS;
    }
//
//    public Page<TaskDTO> getKeyWordTask(Long projectCode, String taskType, String keyWord, Pageable pageable) {
//
//    }

    @Transactional
    public TaskDTO addTask(Long projectCode, String taskType, TaskDTO taskDTO) {
        Task findTask = taskRepository.findByProject_ProjectCodeAndTaskNameAndTaskType(projectCode, taskDTO.getTaskName(), taskType);
        if(findTask != null){
            throw new TaskExistException("이미 업무가 있습미낟.");
        }
        taskDTO.setTaskType(taskType);
        Task saveTask = modelMapper.map(taskDTO, Task.class);
        saveTask.setProject(projectRepository.findByProjectCode(projectCode));
        saveTask = taskRepository.save(saveTask);



        return modelMapper.map(saveTask, TaskDTO.class);
    }
}

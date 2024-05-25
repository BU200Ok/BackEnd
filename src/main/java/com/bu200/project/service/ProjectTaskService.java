package com.bu200.project.service;

import com.bu200.exception.TaskExistException;
import com.bu200.project.dto.TaskDTO;
import com.bu200.project.entity.Task;
import com.bu200.project.repository.ProjectRepository;
import com.bu200.project.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    public Page<TaskDTO> getKeyWordTask(Long projectCode, String taskType, String keyWord, Pageable pageable) {
        Specification<Task> specification = Specification.where(hasProjectCodeAndTaskTypeAndContainingKeyword(projectCode, taskType, keyWord));
        switch (keyWord) {
            case "long" ->
                specification = specification.and(orderByTaskNameLengthDesc());
            case "short" ->
                specification = specification.and(orderByTaskNameLengthAsc());
            case "progress" ->
                specification = specification.and(taskTypeIsInProgress());
            case "completed" ->
                specification = specification.and(taskTypeIsCompleted());
            case "hold" ->
                specification = specification.and(taskTypeIsOnHold());
            case "start" ->
                specification = specification.and(orderByTaskStartAsc());
        }
        Page<Task> tasks = taskRepository.findAll(specification, pageable);

        return tasks.map(task -> modelMapper.map(task,TaskDTO.class));
    }

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









    //option 검색 메소드임==========================================================================================================
    //일단 projectCode, taskType을 포함한 모든 task를 가져오는 쿼리 메소드
    public static Specification<Task> hasProjectCodeAndTaskTypeAndContainingKeyword(Long projectCode, String taskType, String keyword){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(root.get("projectCode"), projectCode),
                criteriaBuilder.equal(root.get("taskType"), taskType),
                criteriaBuilder.equal(root.get("keyword"), keyword)
        ));
    }
    //taskName이 긴 순
    public static Specification<Task> orderByTaskNameLengthAsc(){
        return (root, query, criteriaBuilder) -> {
            query.orderBy(criteriaBuilder.asc(criteriaBuilder.length(root.get("taskName"))));
            return criteriaBuilder.conjunction();
        };
    }
    //taskName이 짧은 순
    public static Specification<Task> orderByTaskNameLengthDesc(){
        return (root, query, criteriaBuilder) -> {
            query.orderBy(criteriaBuilder.desc(criteriaBuilder.length(root.get("taskName"))));
            return criteriaBuilder.conjunction();
        };
    }

    //taskType이 진행인것만
    public static Specification<Task> taskTypeIsInProgress() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("taskType"), "진행");
    }

    //taskType이 완료인것만
    public static Specification<Task> taskTypeIsCompleted() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("taskType"), "완료");
    }

    //taskType이 보류인것만
    public static Specification<Task> taskTypeIsOnHold() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("taskType"), "보류");
    }

    //taskStart가 빠른 순
    public static Specification<Task> orderByTaskStartAsc() {
        return (root, query, criteriaBuilder) -> {
            query.orderBy(criteriaBuilder.asc(root.get("taskStart")));
            return criteriaBuilder.conjunction();
        };
    }
}

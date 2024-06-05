package com.bu200.project.service;

import com.bu200.project.dto.AddTaskFileDTO;
import com.bu200.project.dto.TaskFileDTO;
import com.bu200.project.entity.TaskFile;
import com.bu200.project.entity.TaskPost;
import com.bu200.project.repository.TaskFileRepository;
import com.bu200.project.repository.TaskPostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaskFileService {
    private final ModelMapper modelMapper;
    private final TaskFileRepository taskFileRepository;
    private final TaskPostRepository taskPostRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public TaskFileService(ModelMapper modelMapper, TaskFileRepository taskFileRepository, TaskPostRepository taskPostRepository) {
        this.modelMapper = modelMapper;
        this.taskFileRepository = taskFileRepository;
        this.taskPostRepository = taskPostRepository;
    }

    @Transactional
    public List<AddTaskFileDTO> addTaskFile(Long taskPostCode, List<MultipartFile> files) {
        TaskPost saveTaskPost = taskPostRepository.findTaskPostByTaskPostCode(taskPostCode);
        List<AddTaskFileDTO> addTaskFileDTOS = new ArrayList<>();

        for(MultipartFile file : files) {
            TaskFile saveTaskFile = new TaskFile();
            String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());            //파일 이름
            String changedFileName = UUID.randomUUID() + "_" + originalFileName;
            saveTaskFile.setTaskFileName(originalFileName);
            saveTaskFile.setTaskFileRename(changedFileName);
            saveTaskFile.setTaskFileSize(file.getSize());
            saveTaskFile.setTaskFileType(file.getContentType());
            String filePath = uploadDir + File.separator + changedFileName;
            saveTaskFile.setTaskFilePath(filePath);
            saveTaskFile.setTaskPost(saveTaskPost);
            addTaskFileDTOS.add(modelMapper.map(saveTaskFile, AddTaskFileDTO.class));

            taskFileRepository.save(saveTaskFile);
        }

        return addTaskFileDTOS;
    }
}

package com.bu200.project.service;

import com.bu200.project.dto.ReferenceRoomFileDTO;
import com.bu200.project.repository.TaskFileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReferenceRoomService {
    private final TaskFileRepository taskFileRepository;
    private final ModelMapper modelMapper;

    public ReferenceRoomService(TaskFileRepository taskFileRepository, ModelMapper modelMapper) {
        this.taskFileRepository = taskFileRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public List<ReferenceRoomFileDTO> getFiles(Long taskCode, Pageable pageable) {
        Page<Long> taskFileCodePages = taskFileRepository.getTaskFilesCode(taskCode, pageable);
        List<Long> taskFileCodes = taskFileCodePages.getContent();
        return taskFileRepository.getTaskFiles(taskFileCodes);
    }
}

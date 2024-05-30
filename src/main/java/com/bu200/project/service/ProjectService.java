package com.bu200.project.service;

import com.bu200.project.dto.AccountDTO;
import com.bu200.project.dto.ProjectDetailDTO;
import com.bu200.project.entity.Project;
import com.bu200.project.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ModelMapper modelMapper;
    private final ProjectRepository projectRepository;


    public ProjectService(ModelMapper modelMapper, ProjectRepository projectRepository) {
        this.modelMapper = modelMapper;
        this.projectRepository = projectRepository;
    }

    @Transactional(readOnly = true)
    public ProjectDetailDTO projectDetail(Long projectCode){
        ProjectDetailDTO projectDetailDTO = projectRepository.findProjectDetail(projectCode);
        List<AccountDTO> accountDTOS = projectRepository.findProjectDeTailAccount(projectCode);

        projectDetailDTO.setAccounts(accountDTOS);

        return projectDetailDTO;
        }
}

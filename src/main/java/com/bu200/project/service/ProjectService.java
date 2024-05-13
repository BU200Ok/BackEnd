package com.bu200.project.service;

import com.bu200.project.dto.ProjectDTO;
import com.bu200.project.entity.Project;
import com.bu200.project.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ProjectDTO findByProjectName(String projectName) {
        Project projectEntity = projectRepository.findByProjectName(projectName);
        ProjectDTO projectDTO = new ProjectDTO();
        List<String> memberName = new ArrayList<>();
        for (int i = 0; i < projectEntity.getMember().size(); i++) {
            memberName.add(projectEntity.getMember().get(i).getAccountCode().getAccountName());
        }
        //setter
        projectDTO.setMembers(memberName);
        projectDTO.setAccountName(projectEntity.getAccount().getAccountName());
        projectDTO.setProjectCode(projectEntity.getProjectCode());
        projectDTO.setProjectDescription(projectEntity.getProjectDescription());
        projectDTO.setProjectEnd(projectEntity.getProjectEnd());
        projectDTO.setProjectName(projectEntity.getProjectName());
        projectDTO.setProjectOpenStatus(projectEntity.getProjectOpenStatus());
        projectDTO.setProjectPriority(projectEntity.getProjectPriority());
        projectDTO.setProjectStart(projectEntity.getProjectStart());
        projectDTO.setProjectStatus(projectEntity.getProjectStatus());
        return projectDTO;
    }
}

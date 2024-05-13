package com.bu200.project.service;

import com.bu200.common.response.Tool;
import com.bu200.login.entity.Account;
import com.bu200.project.dto.ProjectDTO;
import com.bu200.project.entity.Project;
import com.bu200.project.entity.ProjectMember;
import com.bu200.project.repository.ProjectMemberRepository;
import com.bu200.project.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final ModelMapper modelMapper;
    private final Tool tool;

    public ProjectService(ProjectRepository projectRepository, ProjectMemberRepository projectMemberRepository, ModelMapper modelMapper, Tool tool) {
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.modelMapper = modelMapper;
        this.tool = tool;
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

    public List<ProjectDTO> findByAccountCode(Long userCode) {
        Account account = new Account();
        account.setAccountCode(userCode);
        List<ProjectMember> member = projectMemberRepository.findByAccountCode(account);
        List<Long> memberCode = new ArrayList<>();
        for (ProjectMember projectMember : member) {
            memberCode.add(projectMember.getProjectCode());
        }
        List<Project> projects = projectRepository.findAllByProjectCodeIn(memberCode);
        for(Project pr : projects){
            System.out.println(pr.getProjectName());
        }
        return tool.convert(projects,ProjectDTO.class);
    }

    public List<ProjectDTO> findAllByProjectOpenStatusTrue() {
        List<Project> projectsEntity = projectRepository.findAllByProjectOpenStatusTrue();
        for (int i = 0; i < projectsEntity.size(); i++) {
            for (int j = 0; j < projectsEntity.get(i).getMember().size(); j++) {
                System.out.println(projectsEntity.get(i).getMember().get(j).getAccountCode());
            }
        }
        return tool.convert(projectsEntity,ProjectDTO.class);
    }
}

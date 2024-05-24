package com.bu200.project.service;

import com.bu200.exception.ProjectExistException;
import com.bu200.login.entity.Account;
import com.bu200.login.entity.Team;
import com.bu200.login.repository.AccountRepository;
import com.bu200.project.dto.AddProjectDTO;
import com.bu200.project.dto.ProjectDTO;
import com.bu200.project.entity.Project;
import com.bu200.project.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    public ProjectService(ProjectRepository projectRepository, AccountRepository accountRepository, ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }


    /**모든 프로젝트를 가져온다.
     * 한 페이지당 6개의 프로젝트를 가져와야한다.
     * 가져올때 고려 요소
     * 1. openstatus : true
     * 2. priority : 오름차순 => 오름차순으로 가져오기만 하면 된다.
     */
    @Transactional(readOnly = true)
    public Page<ProjectDTO> getProject(Long accountCode, Pageable pageable){
        Page<Project> findProjects = projectRepository.findAllByProjectOpenStatusIsTrueOrderByProjectPriorityAsc(pageable);

        //page<엔티티>를 page<dto>로 바꿈
        Page<ProjectDTO> projectDTOS = findProjects.map(myProject -> {
            ProjectDTO projectDTO = modelMapper.map(myProject, ProjectDTO.class);
            projectDTO.setTeamName(myProject.getTeam().getTeamName());
            projectDTO.setDepartmentName(myProject.getTeam().getDepartment().getDepartmentName());
            return projectDTO;
        });

        return projectDTOS;
    }

    //내 팀의 프로젝트를 가져온다.
    @Transactional(readOnly = true)
    public Page<ProjectDTO> getMyProject(Long accountCode, Pageable pageable) {
        Account findAccount = accountRepository.findByAccountCode(accountCode);
        Long myTeamCode = findAccount.getTeam().getTeamCode();

        Page<Project> findMyProjects = projectRepository.findAllByAccount_Team_TeamCodeAndProjectOpenStatusIsTrueOrderByProjectPriorityAsc(myTeamCode, pageable);
        Page<ProjectDTO> projectDTOS = findMyProjects.map(myProject -> {
            ProjectDTO projectDTO = modelMapper.map(myProject, ProjectDTO.class);
            projectDTO.setTeamName(myProject.getTeam().getTeamName());
            projectDTO.setDepartmentName(myProject.getTeam().getDepartment().getDepartmentName());
            return projectDTO;
        });


        return projectDTOS;
    }
    @Transactional(readOnly = true)
    public Page<ProjectDTO> getKewordProject(String keyword, Pageable pageable) {
        Page<Project> projects = projectRepository.findAllByProjectNameContainingAndProjectOpenStatusIsTrue(keyword, pageable);
        Page<ProjectDTO> projectDTOS = projects.map(myProject -> {
            ProjectDTO projectDTO = modelMapper.map(myProject, ProjectDTO.class);
            projectDTO.setTeamName(myProject.getTeam().getTeamName());
            projectDTO.setDepartmentName(myProject.getTeam().getDepartment().getDepartmentName());
            return projectDTO;
        });
        return projectDTOS;
    }

    @Transactional
    public AddProjectDTO addProject(Long userCode, AddProjectDTO addProjectDTO) {
        Project findProject = projectRepository.findByProjectNameAndProjectOpenStatusIsTrue(addProjectDTO.getProjectName());
        if(findProject != null){
            throw new ProjectExistException("이미 존재하는 프로젝트");
        }

        Account findAccount = accountRepository.findByAccountCode(userCode);
        addProjectDTO.setTeam(findAccount.getTeam());
        addProjectDTO.setAccount(findAccount);

        Project saveProject = modelMapper.map(addProjectDTO, Project.class);

        return modelMapper.map(saveProject, AddProjectDTO.class);
    }
}

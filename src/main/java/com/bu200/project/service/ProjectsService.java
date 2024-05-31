package com.bu200.project.service;

import com.bu200.login.entity.Account;
import com.bu200.login.repository.AccountRepository;
import com.bu200.project.dto.AccountDTO;
import com.bu200.project.dto.AddProjectRequestDTO;
import com.bu200.project.dto.AddProjectResponseDTO;
import com.bu200.project.dto.ProjectDTO;
import com.bu200.project.entity.AccountProject;
import com.bu200.project.entity.Project;
import com.bu200.project.repository.AccountProjectRepository;
import com.bu200.project.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectsService {
    private final ModelMapper modelMapper;
    private final AccountProjectRepository accountProjectRepository;
    private final ProjectRepository projectRepository;
    private final AccountRepository accountRepository;

    public ProjectsService(ModelMapper modelMapper, AccountProjectRepository accountProjectRepository, ProjectRepository projectRepository, AccountRepository accountRepository) {
        this.modelMapper = modelMapper;
        this.accountProjectRepository = accountProjectRepository;
        this.projectRepository = projectRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional(readOnly = true)
    public Page<ProjectDTO> projects(Pageable pageable) {
        Page<Long> projectCodesPage = projectRepository.findProjectCodes(pageable); //1번
        List<Long> projectCodes = projectCodesPage.getContent();

        List<Project> projects = projectRepository.findAllProjectWithOtherDetails(projectCodes);    //2번

        List<ProjectDTO> projectDTOs = projects.stream().map(project -> {
            List<AccountDTO> accountDTOS = project.getAccountProjects().stream()
                    .map(accountProject -> {
                        AccountDTO accountDTO = modelMapper.map(accountProject.getAccount(), AccountDTO.class);
                        if(accountProject.getAccount().getTeam() != null){
                            accountDTO.setTeamName(accountProject.getAccount().getTeam().getTeamName());
                            if(accountProject.getAccount().getTeam().getDepartment() != null){
                                accountDTO.setDepartmentName(accountProject.getAccount().getTeam().getDepartment().getDepartmentName());
                            }
                        }
                        return accountDTO;
                    })
                    .collect(Collectors.toList());

            ProjectDTO projectDTO = modelMapper.map(project, ProjectDTO.class);
            projectDTO.setTeamName(project.getAccount().getTeam().getTeamName());
            projectDTO.setDepartmentName(project.getAccount().getTeam().getDepartment().getDepartmentName());
            projectDTO.setAccounts(accountDTOS);

            return projectDTO;
        }).collect(Collectors.toList());

        return new PageImpl<>(projectDTOs, pageable, projectCodesPage.getTotalElements());
    }


    @Transactional(readOnly = true)
    public Page<ProjectDTO> myProjects(Long accountCode, Pageable pageable){
        Page<Long> projectCodesPage = projectRepository.findMyProjectCodes(accountCode, pageable);
        List<Long> projectCodes = projectCodesPage.getContent();

        List<Project> projects = projectRepository.findAllProjectWithOtherDetails(projectCodes);    //2번

        List<ProjectDTO> projectDTOs = projects.stream().map(project -> {
            List<AccountDTO> accountDTOS = project.getAccountProjects().stream()
                    .map(accountProject -> {
                        AccountDTO accountDTO = modelMapper.map(accountProject.getAccount(), AccountDTO.class);
                        if(accountProject.getAccount().getTeam() != null){
                            accountDTO.setTeamName(accountProject.getAccount().getTeam().getTeamName());
                            if(accountProject.getAccount().getTeam().getDepartment() != null){
                                accountDTO.setDepartmentName(accountProject.getAccount().getTeam().getDepartment().getDepartmentName());
                            }
                        }
                        return accountDTO;
                    })
                    .collect(Collectors.toList());

            ProjectDTO projectDTO = modelMapper.map(project, ProjectDTO.class);
            projectDTO.setTeamName(project.getAccount().getTeam().getTeamName());
            projectDTO.setDepartmentName(project.getAccount().getTeam().getDepartment().getDepartmentName());
            projectDTO.setAccounts(accountDTOS);

            return projectDTO;
        }).collect(Collectors.toList());

        return new PageImpl<>(projectDTOs, pageable, projectCodesPage.getTotalElements());
    }

    public Page<ProjectDTO> searchProjects(String keyword, Pageable pageable) {
        Page<Long> projectCodesPage = projectRepository.searchProjectsByKeyword(keyword, pageable);
        List<Long> projectCodes = projectCodesPage.getContent();

        List<Project> projects = projectRepository.findAllProjectWithOtherDetails(projectCodes);    //2번

        List<ProjectDTO> projectDTOs = projects.stream().map(project -> {
            List<AccountDTO> accountDTOS = project.getAccountProjects().stream()
                    .map(accountProject -> {
                        AccountDTO accountDTO = modelMapper.map(accountProject.getAccount(), AccountDTO.class);
                        if(accountProject.getAccount().getTeam() != null){
                            accountDTO.setTeamName(accountProject.getAccount().getTeam().getTeamName());
                            if(accountProject.getAccount().getTeam().getDepartment() != null){
                                accountDTO.setDepartmentName(accountProject.getAccount().getTeam().getDepartment().getDepartmentName());
                            }
                        }
                        return accountDTO;
                    })
                    .collect(Collectors.toList());

            ProjectDTO projectDTO = modelMapper.map(project, ProjectDTO.class);
            projectDTO.setTeamName(project.getAccount().getTeam().getTeamName());
            projectDTO.setDepartmentName(project.getAccount().getTeam().getDepartment().getDepartmentName());
            projectDTO.setAccounts(accountDTOS);

            return projectDTO;
        }).collect(Collectors.toList());

        return new PageImpl<>(projectDTOs, pageable, projectCodesPage.getTotalElements());
    }

    public Page<ProjectDTO> searchMyProjects(Long accountCode, String keyword, Pageable pageable){
        Page<Long> projectCodesPage = projectRepository.searchMyProjectsByKeyword(keyword, accountCode, pageable);
        List<Long> projectCodes = projectCodesPage.getContent();

        List<Project> projects = projectRepository.findAllProjectWithOtherDetails(projectCodes);    //2번

        List<ProjectDTO> projectDTOs = projects.stream().map(project -> {
            List<AccountDTO> accountDTOS = project.getAccountProjects().stream()
                    .map(accountProject -> {
                        AccountDTO accountDTO = modelMapper.map(accountProject.getAccount(), AccountDTO.class);
                        if(accountProject.getAccount().getTeam() != null){
                            accountDTO.setTeamName(accountProject.getAccount().getTeam().getTeamName());
                            if(accountProject.getAccount().getTeam().getDepartment() != null){
                                accountDTO.setDepartmentName(accountProject.getAccount().getTeam().getDepartment().getDepartmentName());
                            }
                        }
                        return accountDTO;
                    })
                    .collect(Collectors.toList());

            ProjectDTO projectDTO = modelMapper.map(project, ProjectDTO.class);
            projectDTO.setTeamName(project.getAccount().getTeam().getTeamName());
            projectDTO.setDepartmentName(project.getAccount().getTeam().getDepartment().getDepartmentName());
            projectDTO.setAccounts(accountDTOS);

            return projectDTO;
        }).collect(Collectors.toList());

        return new PageImpl<>(projectDTOs, pageable, projectCodesPage.getTotalElements());
    }

    @Transactional
    public AddProjectResponseDTO addProject(Long accountCode, AddProjectRequestDTO addProjectRequestDTO) {
        Project saveProject = modelMapper.map(addProjectRequestDTO, Project.class);
        Account findAccount = accountRepository.findByAccountCode(accountCode);
        saveProject.setAccount(findAccount);

        saveProject = projectRepository.save(saveProject);

        accountProjectRepository.save(new AccountProject(null, findAccount, saveProject));

        return modelMapper.map(saveProject, AddProjectResponseDTO.class);
    }


    //프로젝트 추가 중복 검사 로직
    public boolean checkDuplicateName(String projectName) {
        Project findProject = projectRepository.findByProjectNameAndProjectOpenStatusIsTrue(projectName);
        if(findProject != null){
            return true;
        }
        return false;
    }
}

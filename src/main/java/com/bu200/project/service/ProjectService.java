package com.bu200.project.service;

import com.bu200.login.entity.Account;
import com.bu200.login.repository.AccountRepository;
import com.bu200.project.dto.*;
import com.bu200.project.entity.AccountProject;
import com.bu200.project.entity.Project;
import com.bu200.project.repository.AccountProjectRepository;
import com.bu200.project.repository.ProjectRepository;
import com.bu200.project.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService {
    private final ModelMapper modelMapper;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final AccountRepository accountRepository;
    private final AccountProjectRepository accountProjectRepository;


    public ProjectService(ModelMapper modelMapper, ProjectRepository projectRepository, TaskRepository taskRepository, AccountRepository accountRepository, AccountProjectRepository accountProjectRepository) {
        this.modelMapper = modelMapper;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.accountRepository = accountRepository;
        this.accountProjectRepository = accountProjectRepository;
    }

    @Transactional(readOnly = true)
    public ProjectDetailDTO projectDetail(Long projectCode){
        ProjectDetailDTO projectDetailDTO = projectRepository.findProjectDetail(projectCode);
        List<AccountDTO> accountDTOS = projectRepository.findProjectDeTailAccount(projectCode);

        projectDetailDTO.setAccounts(accountDTOS);

        return projectDetailDTO;
    }

    @Transactional
    public AddAccountProjectResponseDTO addAccount(AddAccountProjectRequestDTO addAccountProjectRequestDTO, Long projectCode) {
        Account findAccount = accountRepository.findByAccountId(addAccountProjectRequestDTO.getAccountId());
        Project findProject = projectRepository.findByProjectCode(projectCode);

        accountProjectRepository.save(new AccountProject(null, findAccount, findProject));

        return modelMapper.map(findAccount, AddAccountProjectResponseDTO.class);
    }

    public boolean checkDuplicateAccountProject(AddAccountProjectRequestDTO addAccountProjectRequestDTO, Long projectCode){
        AccountProject findAccountProject = accountProjectRepository.findByAccount_AccountIdAndProject_ProjectCode
                (addAccountProjectRequestDTO.getAccountId(), projectCode);

        if(findAccountProject != null){
            return true;
        }
        return false;
    }
}

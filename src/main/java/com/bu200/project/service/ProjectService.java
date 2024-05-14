package com.bu200.project.service;

import com.bu200.common.response.Tool;
import com.bu200.login.entity.Account;
import com.bu200.project.entity.Project;
import com.bu200.project.entity.ProjectForum;
import com.bu200.project.entity.ProjectForumPost;
import com.bu200.project.entity.ProjectMember;
import com.bu200.project.repository.ProjectForumPostRepository;
import com.bu200.project.repository.ProjectForumRepository;
import com.bu200.project.repository.ProjectMemberRepository;
import com.bu200.project.repository.ProjectRepository;
import com.bu200.security.dto.CustomUserDetails;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectForumRepository projectForumRepository;
    private final ProjectForumPostRepository projectForumPostRepository;
    private final ModelMapper modelMapper;
    private final Tool tool;
    private Pageable pageable;

    public ProjectService(ProjectRepository projectRepository, ProjectMemberRepository projectMemberRepository, ProjectForumRepository projectForumRepository, ProjectForumPostRepository projectForumPostRepository, ModelMapper modelMapper, Tool tool) {
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.projectForumRepository = projectForumRepository;
        this.projectForumPostRepository = projectForumPostRepository;
        this.modelMapper = modelMapper;
        this.tool = tool;
    }

    public Page<Project> findByAccountCode(Long userCode, int page) {
        pageable = PageRequest.of(page,6);
        Account account = new Account();
        account.setAccountCode(userCode);
        List<ProjectMember> member = projectMemberRepository.findByAccountCode(account);
        List<Long> memberCode = new ArrayList<>();
        for (ProjectMember projectMember : member) {
            memberCode.add(projectMember.getProjectCode());
        }
        Page<Project> projects = projectRepository.findAllByProjectCodeIn(memberCode,pageable);
        for(Project pr : projects){
            System.out.println(pr.getProjectName());
        }
        return projects;
    }

    public Page<Project> findAllByProjectOpenStatusTrue(int page) {
        pageable = PageRequest.of(page,6);
        return projectRepository.findAllByProjectOpenStatusTrue(pageable);
    }

    public Project findById(Long projectCode) {
        return projectRepository.findById(projectCode).orElseThrow();
    }

    public List<ProjectForum> findByProjectCode(Long projectCode) {
        try{
            return projectForumRepository.findAllByProjectCodeOrderByProjectForumModifyDateDesc(projectCode);

        } catch (Exception e){
            return null;
        }
    }

    public Project createProject(ProjectDTO projectDTO, CustomUserDetails user) {
        Project project = modelMapper.map(projectDTO,Project.class);
        Account account = new Account();
        account.setAccountCode(Long.valueOf(user.getCode()));
        project.setAccount(account);
        return projectRepository.save(project);
    }

    public List<ProjectForumPost> findByProjectForumCode(Long projectForumCode) {
        return projectForumPostRepository.findAllByProjectForumCodeOrderByProjectForumPostWriteDateDesc(projectForumCode);
    }
}

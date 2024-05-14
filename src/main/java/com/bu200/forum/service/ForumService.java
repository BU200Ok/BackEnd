package com.bu200.forum.service;

import com.bu200.forum.dto.ForumDTO;
import com.bu200.forum.entity.Forum;
import com.bu200.forum.repository.ForumRepository;
import com.bu200.login.repository.AccountRepository;
import com.bu200.login.repository.UserRepository;
import com.bu200.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ForumService {
    private final ForumRepository forumRepository;
    private final AccountRepository accountRepository;

    public ForumService(ForumRepository forumRepository, AccountRepository accountRepository) {
        this.forumRepository = forumRepository;
        this.accountRepository = accountRepository;
    }

    // 모든 부서의 게시판 조회
    public List<ForumDTO> getAllForums() {
        List<Forum> forums = forumRepository.findAll(); // 데이터베이스에서 모든 포럼을 조회
        return forums.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 로그인한 사용자의 부서 게시판 조회
    public List<ForumDTO> getForumsByDepartment(String departmentName) {
        List<Forum> forums = forumRepository.findAllByDepartmentList(departmentName);
        return forums.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    //로그인한 사용자 부서 조회
    public String getDepartmentName(String userName) {
        return accountRepository.findDepartmentNameByUserName(userName);
    }


    private ForumDTO convertToDto(Forum forum) {
        ForumDTO dto = new ForumDTO();
        dto.setForumCode(forum.getForumCode());
        dto.setForumTitle(forum.getForumTitle());
        dto.setForumContent(forum.getForumContent());
        dto.setForumType(forum.getForumType());
        dto.setForumCreateTime(forum.getForumCreateTime());
        return dto;
    }


    public Forum saveForum(Forum forum) {
        forum.setForumCreateTime(LocalDateTime.now());
        forum.setForumModify(LocalDateTime.now());
        return forumRepository.save(forum);
    }

}

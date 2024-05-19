package com.bu200.forum.service;

import com.bu200.forum.dto.ForumDTO;
import com.bu200.forum.entity.Forum;
import com.bu200.forum.repository.ForumRepository;
import com.bu200.login.entity.Account;
import com.bu200.login.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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


    // 사용자가 작성한 게시글 조회
    public List<ForumDTO> findForumsByAccountCode(String userName) {
        List<Forum> forums = forumRepository.findByAccount_AccountId(userName);
        return forums.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    //AccountCode 찾기
    public Account findByAccountCode(Long accountCode) {
        return accountRepository.findById(accountCode).orElse(null);
    }

    // 저장
    public Forum saveForum(ForumDTO forumDTO) {
        Account account = findByAccountCode(forumDTO.getAccountCode());
        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }

        Forum forum = convertToEntity(forumDTO);
        forum.setAccount(account);

        forum.setForumCreateTime(LocalDateTime.now());
        forum.setForumModify(LocalDateTime.now());
        return forumRepository.save(forum);
    }


    //삭제
    public void deleteForum(Long forumCode, Long accountCode) {
        if (!forumRepository.existsByIdAndAccount_AccountCode(forumCode, accountCode)) {
            throw new IllegalArgumentException("게시글을 찾을 수 없습니다.");
        }
        forumRepository.deleteByIdAndAccount_AccountCode(forumCode, accountCode);
    }


    //DTO 변환
    private ForumDTO convertToDto(Forum forum) {
        ForumDTO dto = new ForumDTO();
        dto.setForumCode(forum.getForumCode());
        dto.setForumTitle(forum.getForumTitle());
        dto.setForumContent(forum.getForumContent());
        dto.setForumType(forum.getForumType());
        dto.setForumCreateTime(forum.getForumCreateTime());
        return dto;
    }

    //Entity 변환
    private Forum convertToEntity(ForumDTO forumDTO) {
        Forum forum = new Forum();
        forum.setForumTitle(forumDTO.getForumTitle());
        forum.setForumContent(forumDTO.getForumContent());
        forum.setForumType(forumDTO.getForumType());
        forum.setForumCreateTime(forumDTO.getForumCreateTime());
        // 필요한 다른 필드 설정
        return forum;
    }

}

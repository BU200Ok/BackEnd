package com.bu200.comment.service;

import com.bu200.comment.dto.CommentCreateDTO;
import com.bu200.comment.dto.CommentDTO;
import com.bu200.comment.entity.Comment;
import com.bu200.comment.repository.CommentRepository;
import com.bu200.forum.dto.ForumDTO;
import com.bu200.forum.entity.Forum;
import com.bu200.forum.repository.ForumRepository;
import com.bu200.login.entity.Account;
import com.bu200.login.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ForumRepository forumRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, ForumRepository forumRepository, AccountRepository accountRepository) {
        this.commentRepository = commentRepository;
        this.forumRepository = forumRepository;
        this.accountRepository = accountRepository;
    }
    // 댓글 생성
    public CommentDTO createComment(CommentCreateDTO commentCreateDTO) {
        Comment comment = new Comment();
        comment.setCommentContent(commentCreateDTO.getCommentContent());
        comment.setCommentCreateTime(LocalDateTime.now());
        comment.setCommentModify(LocalDateTime.now());

        Forum forum = forumRepository.findById(commentCreateDTO.getForumCode())
                .orElseThrow(() -> new IllegalArgumentException("Invalid forum code"));
        Account account = accountRepository.findById(commentCreateDTO.getAccountCode())
                .orElseThrow(() -> new IllegalArgumentException("Invalid account code"));

        comment.setForum(forum);
        comment.setAccount(account);

        Comment savedComment = commentRepository.save(comment);
        return convertToDto(savedComment);
    }

    //조회
    public List<CommentDTO> getCommentsByForumCode(Long forumCode) {
        List<Comment> comments = commentRepository.findByForumForumCode(forumCode);
        return comments.stream()
                .map(CommentDTO::new)
                .collect(Collectors.toList());
    }

    private CommentDTO convertToDto(Comment comment) {
        return new CommentDTO();
    }

    //삭제
    public void deleteComment(Long commentCode) {
        Comment comment = commentRepository.findById(commentCode)
                .orElseThrow(() -> new IllegalArgumentException("Invalid comment code"));
        commentRepository.delete(comment);
    }
}

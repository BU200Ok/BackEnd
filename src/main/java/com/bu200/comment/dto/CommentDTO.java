package com.bu200.comment.dto;

import com.bu200.comment.entity.Comment;
import com.bu200.forum.dto.ForumDTO;
import com.bu200.forum.entity.Forum;
import com.bu200.login.entity.Account;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@ToString
@Getter
@Setter
public class CommentDTO {
    private Long commentCode;
    private String commentContent;
    private LocalDateTime commentCreateTime;
    private LocalDateTime commentModify;
    private ForumDTO forum;
    private Long account;
    private String accountName;
    private String accountPosition;
    private String accountDepartment;

//    public CommentDTO(Comment comment) {
//        this.commentCode = comment.getCommentCode();
//        this.commentContent = comment.getCommentContent();
//        this.commentCreateTime = comment.getCommentCreateTime();
//        this.commentModify = comment.getCommentModify();
//        this.forum = new ForumDTO(comment.getForum());
//
//        Account account = comment.getAccount();
//        this.account = account.getAccountCode();
//        this.accountName = account.getAccountName();
//        this.accountPosition = account.getAccountPosition();
//    }
    public CommentDTO(Comment comment) {
        this.commentCode = comment.getCommentCode();
        this.commentContent = comment.getCommentContent();
        this.commentCreateTime = comment.getCommentCreateTime();
        this.commentModify = comment.getCommentModify();
        this.forum = new ForumDTO(comment.getForum());

        Account account = comment.getAccount();
        if (account != null) {
            this.account = account.getAccountCode();
            this.accountName = account.getAccountName(); // 확인 후 수정
            this.accountPosition = account.getAccountPosition(); // 확인 후 수정
        }
    }

}

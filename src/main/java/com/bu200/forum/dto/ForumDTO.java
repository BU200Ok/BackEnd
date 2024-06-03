package com.bu200.forum.dto;

import com.bu200.forum.entity.Forum;
import lombok.*;
import org.aspectj.apache.bcel.classfile.Code;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ForumDTO {
    private Long forumCode;
    private String forumType;
    private String forumTitle;
    private String forumContent;
    private LocalDateTime forumCreateTime;
    private Long accountCode;

    public ForumDTO(Forum forum) {
        this.forumCode = forum.getForumCode();
        this.forumType = forum.getForumType();
        this.forumTitle = forum.getForumTitle();
        this.forumContent = forum.getForumContent();
        this.forumCreateTime = forum.getForumCreateTime();
        this.accountCode = forum.getAccount() != null ? forum.getAccount().getAccountCode() : null;
    }

}


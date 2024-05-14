package com.bu200.forum.dto;

import com.bu200.forum.entity.Forum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

    public ForumDTO(Long forumCode, String forumType, String forumTitle, String forumContent, LocalDateTime forumCreateTime) {
        this.forumCode = forumCode;
        this.forumType = forumType;
        this.forumTitle = forumTitle;
        this.forumContent = forumContent;
        this.forumCreateTime = forumCreateTime;
    }


    public ForumDTO(Forum forum) {
    }
}



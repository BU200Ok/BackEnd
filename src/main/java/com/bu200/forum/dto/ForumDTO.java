package com.bu200.forum.dto;

import com.bu200.forum.entity.Forum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ForumDTO {
    private Long forumCode;
    private String forumType;
    private String forumTitle;
    private String forumContent;
    private LocalDateTime forumCreateTime;
    private Long accountCode;

    public ForumDTO(Forum forum) {
    }
}



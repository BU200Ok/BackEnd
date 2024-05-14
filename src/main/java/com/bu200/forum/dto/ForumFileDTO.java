package com.bu200.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForumFileDTO {
    private Long forumFileCode;
    private String forumFileName;
    private String forumFileUrl;
    private LocalDateTime forumFileCreateTime;
    private Long forumCode;

}


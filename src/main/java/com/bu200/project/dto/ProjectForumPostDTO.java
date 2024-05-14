package com.bu200.project.dto;

import com.bu200.login.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectForumPostDTO {
    private Long projectForumPostCode;
    private String projectForumPostContent;
    private String projectForumPostImage;
    private LocalDate projectForumPostWriteDate;
    private AccountDTO account;
    private Long projectForumCode;
}

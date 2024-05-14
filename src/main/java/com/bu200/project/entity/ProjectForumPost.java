package com.bu200.project.entity;

import com.bu200.login.entity.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "project_forum_post")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectForumPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_forum_post_code")
    private Long projectForumPostCode;
    @Column(name = "project_forum_post_content")
    private String projectForumPostContent;
    @Column(name = "project_forum_post_image")
    private String projectForumPostImage;
    @Column(name = "project_forum_post_write_date")
    private LocalDate projectForumPostWriteDate;
    @JoinColumn(name = "user_code")
    @ManyToOne
    private Account account;
    @JoinColumn(name = "project_forum_code")
    private Long projectForumCode;
}

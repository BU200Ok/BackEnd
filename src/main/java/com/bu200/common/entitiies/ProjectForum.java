package com.bu200.common.entitiies;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity @Table(name = "project_forum")
@Getter @RequiredArgsConstructor
public class ProjectForum {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_forum_code")
    private Long projectForumCode;

    @Column(name = "project_forum_type")
    private String projectForumType;

    @Column(name = "project_forum_title")
    private String projectForumTitle;

    @Column(name = "project_forum_content")
    private String projectForumContent;

    @CreationTimestamp
    @Column(name = "project_forum_create_time")
    private Timestamp projectForumCreateTime;

    @Column(name = "project_status")
    private String projectStatus;

    @Column(name = "project_dead_line")
    private LocalDateTime projectDeadLine;

    @ManyToOne
    @JoinColumn(name = "project_management_key")
    private ProjectManagement projectManagementKey;

}

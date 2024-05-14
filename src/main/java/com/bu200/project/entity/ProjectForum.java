package com.bu200.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "project_forum")
@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
public class ProjectForum {
    @Id
    @Column(name = "project_forum_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectForumCode;
    @Column(name = "project_forum_name")
    private String projectForumName;
    @Column(name = "project_forum_create_time")
    private LocalDateTime projectForumCreateTime;
    @Column(name = "project_forum_modify_date")
    private LocalDate projectForumModifyDate;
    @Column(name = "project_forum_deleted")
    private boolean projectForumDeleted;
    @JoinColumn(name = "project_code")
    private Long projectCode;
    @Column(name = "project_forum_status")
    private String projectForumStatus;
}

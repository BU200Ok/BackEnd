package com.bu200.forum.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "forum_file")
public class ForumFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "forum_file_code")
    private Long forumFileCode;

    @Column(name = "forum_file_name", nullable = false)
    private String forumFileName;

    @Column(name = "forum_file_url", nullable = false)
    private String forumFileUrl;

    @Column(name = "forum_file_create_time", nullable = false)
    private LocalDateTime forumFileCreateTime;

    @Column(name = "forum_file_type", nullable = false)
    private String forumFileType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forum_code")
    private Forum forum;
}

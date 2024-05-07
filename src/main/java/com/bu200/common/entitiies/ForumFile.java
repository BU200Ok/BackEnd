package com.bu200.common.entitiies;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity @Table(name = "forum_file")
@Getter @RequiredArgsConstructor
public class ForumFile {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "forum_file_code")
    private Long forumFileCode;

    @Column(name = "forum_file_name")
    private String forumFileName;

    @Column(name = "forum_file_size")
    private Integer forumFileSize;

    @CreationTimestamp
    @Column(name = "forum_file_create_time")
    private Timestamp forumFileCreateTime;

    @Column(name = "forum_file_type")
    private String forumFileType;

    @Column(name = "forum_file_url")
    private String forumFileUrl;

    @Column(name = "forum_code")
    private Long forum_code;
}

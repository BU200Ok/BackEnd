package com.bu200.forum.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "forum_file")
@AllArgsConstructor
@NoArgsConstructor
public class ForumFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="forum_file_code")
    private Long forumFileCode;
    @Column(name="forum_file_name")
    private String forumFileName;
    @Column(name="forum_file_url")
    private String forumFileUrl;
    @Column(name="forum_file_create_time")
    private LocalDateTime forumFileCreateTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forum_code")
    private Forum forumCode;

}

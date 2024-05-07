package com.bu200.common.entitiies;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity @Table(name = "FORUM")
@Getter @RequiredArgsConstructor
public class Forum {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "forum_code")
    private Long forumCode;

    @Column(name = "forum_type")
    private String forumType;

    @Column(name = "forum_title")
    private String forumTitle;

    @Column(name = "forum_content")
    private String forumContent;

    @CreationTimestamp
    @Column(name = "forum_create_time")
    private Timestamp forumCreateTime;

    @UpdateTimestamp
    @Column(name = "forum_modify")
    private Timestamp forumModify;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_code")
    @Column(name = "account_code")
    private Account accountCode;

    @OneToMany(mappedBy = "forum_code", fetch = FetchType.LAZY)
    private List<Comment> comments; //초기화 필요 x

    @OneToMany(mappedBy = "forum_code", fetch = FetchType.LAZY)
    private List<ForumFile> forumFiles;
}

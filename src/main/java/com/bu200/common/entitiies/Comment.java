package com.bu200.common.entitiies;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity @Table(name = "comment")
@Getter @RequiredArgsConstructor
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_code")
    private Long commentCode;

    @Column(name = "comment_content")
    private String commentContent;

    @CreationTimestamp
    @Column(name = "comment_create_time")
    private Timestamp commentCreateTime;

    @Column(name = "comment_delete_time")
    private String commentDeleteTime;

    @UpdateTimestamp
    @Column(name = "comment_modify")
    private Timestamp commentModify;

    @ManyToOne
    @JoinColumn(name = "account_code")
    @Column(name = "account_code")
    private Account accountCode;

    @Column(name = "forum_code")
    private Long forumCode;
}

package com.bu200.forum.entity;

import com.bu200.login.entity.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity @Table(name = "forum")
@Getter @Setter
@NoArgsConstructor
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
    @Column(name = "forum_create_time")
    private Timestamp forumModify;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_code")
    private Account account;
}

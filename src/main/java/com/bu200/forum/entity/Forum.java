package com.bu200.forum.entity;

import com.bu200.login.entity.Account;
import com.bu200.login.entity.Department;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "forum")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Forum {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="forum_code")
        private Long forumCode;

        @Column(name="forum_type")
        private String forumType;

        @Column(name="forum_title")
        private String forumTitle;

        @Column(name="forum_content")
        private String forumContent;

        @Column(name="forum_isdelete")
        private boolean forumIsDelete;

        @Column(name="forum_create_time")
        @LastModifiedDate
        private LocalDateTime forumCreateTime;

        @Column(name="forum_modify")
        @LastModifiedDate
        private LocalDateTime forumModify;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "account_code")
        private Account account;

}



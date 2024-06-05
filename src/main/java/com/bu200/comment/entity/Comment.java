package com.bu200.comment.entity;

import com.bu200.forum.entity.Forum;
import com.bu200.login.entity.Account;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_code")
    private Long commentCode;

    @Column(name="comment_content")
    private String commentContent;


    @Column(name = "comment_create_time")
    @CreatedDate
    private LocalDateTime commentCreateTime;


    @Column(name = "comment_modify")
    @LastModifiedDate
    private LocalDateTime commentModify;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forum_code")
    private Forum forum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_code")
    private Account account;


}
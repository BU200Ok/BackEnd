package com.bu200.project.entity;

import com.bu200.login.entity.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Entity @Table(name = "task_post")
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class TaskPost {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_post_code")
    private Long taskPostCode;

    @Column(name = "task_post_detail")
    private String taskPostDetail;

    @CreationTimestamp
    @Column(name = "task_post_time")
    private Timestamp taskPostTime;

    @UpdateTimestamp
    @Column(name = "task_post_modify_time")
    private Timestamp taskPostModifyTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_code")
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_code")
    private Account account;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "taskPost")
    private List<TaskFile> taskFiles;
}

package com.bu200.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity@Table(name = "task_file")
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class TaskFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_file_code")
    private Long taskFileCode;

    @Column(name = "task_file_name")
    private String taskFileName;

    @Column(name = "task_file_rename")
    private String taskFileRename;

    @Column(name = "task_file_size")
    private Long taskFileSize;

    @CreationTimestamp
    @Column(name = "task_file_create_time")
    private Timestamp taskFileCreateTime;

    @Column(name = "task_file_type")
    private String taskFileType;

    @Column(name = "task_file_path")
    private String taskFilePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_post_code")
    private TaskPost taskPost;
}

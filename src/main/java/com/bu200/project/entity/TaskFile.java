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
    @Column(name = "output_code")
    private Long outputCode;

    @Column(name = "output_name")
    private String outputName;

    @Column(name = "output_rename")
    private String outputRename;

    @Column(name = "output_size")
    private Long outputsize;

    @CreationTimestamp
    @Column(name = "output_create_time")
    private Timestamp outputCreateTime;

    @Column(name = "output_type")
    private String outputType;

    @Column(name = "output_url")
    private String outputUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_code")
    private Project project;
}

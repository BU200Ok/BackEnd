package com.bu200.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
@Getter@Setter@NoArgsConstructor
public class ReferenceRoomFileDTO {
    //파일이 올려져 있는 업무 게시글
    private Long taskPostCode;
    private String taskPostDetail;
    private Timestamp taskPostTime;
    //파일을 올린 사람 이름
    private String AccountName;

    //파일 정보
    private String taskFileName;
    private String taskFileRename;

    public ReferenceRoomFileDTO(Long taskPostCode, String taskPostDetail, Timestamp taskPostTime, String accountName, String taskFileName, String taskFileRename) {
        this.taskPostCode = taskPostCode;
        this.taskPostDetail = taskPostDetail;
        this.taskPostTime = taskPostTime;
        AccountName = accountName;
        this.taskFileName = taskFileName;
        this.taskFileRename = taskFileRename;
    }
}

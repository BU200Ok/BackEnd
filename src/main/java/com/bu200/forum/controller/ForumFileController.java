package com.bu200.forum.controller;

import com.bu200.common.response.Tool;
import com.bu200.forum.entity.ForumFile;
import com.bu200.forum.service.ForumFileDownService;
import com.bu200.forum.service.ForumFileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/forum-file")
public class ForumFileController {
    private final Tool tool;
    private final ForumFileUploadService forumFileUploadService;
    private final ForumFileDownService forumFileDownService;
    private static final Logger logger = LoggerFactory.getLogger(ForumFileController.class);
    @Value("${file.upload-dir}")
    private String directPath;

    public ForumFileController(Tool tool, ForumFileUploadService forumFileUploadService, ForumFileDownService forumFileDownService) {
        this.tool = tool;
        this.forumFileUploadService = forumFileUploadService;
        this.forumFileDownService = forumFileDownService;
    }

    // 저장
    @PostMapping("/uploads/{forumCode}")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Long forumCode){
        ForumFile forumFile = forumFileUploadService.saveFile(file, forumCode);
        if (forumFile != null) {
            return tool.res(HttpStatus.OK, "파일 업로드 성공", forumFile.getForumFileUrl());
        } else {
            return tool.resErr("파일 업로드 중 오류가 발생");
        }
    }

    // 특정 포럼 코드에 해당하는 파일 정보만 가져오기
    @GetMapping("/{forumCode}/files")
    public ResponseEntity<?> getFilesByForumCode(@PathVariable Long forumCode) {
        List<ForumFile> files = forumFileDownService.getFilesByForumCode(forumCode);
        List<Map<String, String>> fileInfos = files.stream()
                .map(file -> Map.of("changedFileName", file.getForumFileName()))
                .toList();
        return tool.res(HttpStatus.OK, "파일 목록 가져오기 성공", fileInfos);
    }

    // 파일 상세 정보 가져오기
    @GetMapping("/info")
    public ResponseEntity<?> getFileInfo(@RequestParam String changedFileName) {
        try {
            Map<String, Object> fileInfo = forumFileDownService.getFileInfo(changedFileName);
            return ResponseEntity.ok(fileInfo);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 정보를 읽는 도중 오류가 발생했습니다.");
        }
    }

    //다운로드
    @GetMapping("/download")
    public ResponseEntity<?> downloadFile(@RequestParam String changedFileName) {
        try {
            ByteArrayResource resource = forumFileDownService.downloadFile(changedFileName);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + changedFileName + "\"");

            return ResponseEntity.ok().headers(headers).body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("파일이 없습니다.");
        }
    }

}

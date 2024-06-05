package com.bu200.forum.controller;

import com.bu200.common.response.Tool;
import com.bu200.forum.entity.ForumFile;
import com.bu200.forum.service.ForumFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class ForumFileController {
    private final Tool tool;
    private final ForumFileService forumFileService;
    @Value("${file.upload-dir}")
    private String directPath;

    public ForumFileController(Tool tool, ForumFileService forumFileService) {
        this.tool = tool;
        this.forumFileService = forumFileService;
    }
    @PostMapping("/uploads/{forumCode}")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Long forumCode){
        ForumFile forumFile = forumFileService.saveFile(file, forumCode);
        if (forumFile != null) {
            return tool.res(HttpStatus.OK, "파일 업로드 성공", forumFile.getForumFileUrl());
        } else {
            return tool.resErr("파일 업로드 중 오류가 발생");
        }
    }

}

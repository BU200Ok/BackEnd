package com.bu200.forum.controller;

import com.bu200.common.response.ResponseDTO;
import com.bu200.common.response.Tool;
import com.bu200.forum.entity.ForumFile;
import com.bu200.forum.service.ForumFileService;
import com.bu200.project.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
            return tool.resErr("파일 업로드 중 오류가 발생했습니다.");
        }
    }

    @GetMapping("/download")
    public ResponseEntity<ResponseDTO> downloadFile(@RequestParam String fileName) throws IOException {
        Path path = Paths.get(directPath + fileName);
        if(Files.exists(path)){
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

            return tool.res(HttpStatus.OK, "파일입니다.", resource);
        }else{
            return tool.resErr("파일이 없습니다.");
        }
    }
}

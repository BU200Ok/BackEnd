package com.bu200.forum.service;


import com.bu200.common.response.Tool;
import com.bu200.forum.entity.Forum;
import com.bu200.forum.entity.ForumFile;
import com.bu200.forum.repository.ForumFileRepository;
import com.bu200.forum.repository.ForumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ForumFileUploadService {
    private final Tool tool;
    private final ForumRepository forumRepository;
    private final ForumFileRepository forumFileRepository;

    @Value("${file.upload-dir}")
    private String directPath;

    @Autowired
    public ForumFileUploadService(Tool tool, ForumRepository forumRepository, ForumFileRepository forumFileRepository) {
        this.tool = tool;
        this.forumRepository = forumRepository;
        this.forumFileRepository = forumFileRepository;
    }

    public ForumFile saveFile(MultipartFile file, Long forumCode) {
        String changedFileName = tool.upload(file); // 파일 이름 변경 후 저장
        if (changedFileName != null) {
            Forum forum = forumRepository.findById(forumCode)
                    .orElseThrow(() -> new IllegalArgumentException("forumCode 없음"));

            ForumFile forumFile = new ForumFile();
            forumFile.setForumFileName(file.getOriginalFilename()); // 원본 파일 이름 저장
            forumFile.setForumFileUrl(changedFileName); // 변경된 파일 이름 저장
            forumFile.setForumFileCreateTime(LocalDateTime.now());
            forumFile.setForumFileType(file.getContentType());
            forumFile.setForum(forum);

            return forumFileRepository.save(forumFile);
        } else {
            throw new RuntimeException("파일 업로드 실패");
        }
    }

}


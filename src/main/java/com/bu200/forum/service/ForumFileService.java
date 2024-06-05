package com.bu200.forum.service;

import com.bu200.common.response.Tool;
import com.bu200.forum.entity.Forum;
import com.bu200.forum.entity.ForumFile;
import com.bu200.forum.repository.ForumFileRepository;
import com.bu200.forum.repository.ForumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
public class ForumFileService {
    private final Tool tool;
    private final ForumRepository forumRepository;
    private final ForumFileRepository forumFileRepository;

    @Autowired
    public ForumFileService(Tool tool, ForumRepository forumRepository, ForumFileRepository forumFileRepository) {
        this.tool = tool;
        this.forumRepository = forumRepository;
        this.forumFileRepository = forumFileRepository;
    }

    public ForumFile saveFile(MultipartFile file, Long forumCode){
        String fileName = tool.upload(file);
        if (fileName != null) {
            Forum forum = forumRepository.findById(forumCode)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid forum code"));

            ForumFile forumFile = new ForumFile();
            forumFile.setForumFileName(file.getOriginalFilename());
            forumFile.setForumFileUrl(fileName);
            forumFile.setForumFileCreateTime(LocalDateTime.now());
            forumFile.setForumFileType(file.getContentType());
            forumFile.setForum(forum);

            return forumFileRepository.save(forumFile);
        } else {
            throw new RuntimeException("File upload failed");
        }
    }

}


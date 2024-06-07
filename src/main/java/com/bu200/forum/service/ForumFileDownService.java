package com.bu200.forum.service;

import com.bu200.common.response.Tool;
import com.bu200.forum.entity.ForumFile;
import com.bu200.forum.repository.ForumFileRepository;
import com.bu200.forum.repository.ForumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ForumFileDownService {
    private static final Logger logger = LoggerFactory.getLogger(ForumFileDownService.class);
    private final ForumFileRepository forumFileRepository;
    @Value("${file.upload-dir}")
    private String directPath;

    public ForumFileDownService(ForumFileRepository forumFileRepository) {
        this.forumFileRepository = forumFileRepository;
    }

    //파일 정보
    public Map<String, Object> getFileInfo(String changedFileName) throws IOException {
        Path path = Paths.get(directPath + File.separator + changedFileName);
        System.out.println("File path: " + path.toString());

        if (Files.exists(path)) {
            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
            Map<String, Object> fileInfo = new HashMap<>();
            fileInfo.put("fileName", changedFileName);
            fileInfo.put("size", attr.size());
            fileInfo.put("lastModifiedTime", attr.lastModifiedTime().toString());
            fileInfo.put("creationTime", attr.creationTime().toString());
            fileInfo.put("isDirectory", attr.isDirectory());
            fileInfo.put("isRegularFile", attr.isRegularFile());

            return fileInfo;
        } else {
            throw new IOException("파일이 존재하지 않습니다.");
        }
    }


    public List<ForumFile> getFilesByForumCode(Long forumCode) {
        return forumFileRepository.findByForum_ForumCode(forumCode);
    }

    public ByteArrayResource downloadFile(String originalFileName) throws IOException {
        Optional<ForumFile> forumFileOpt = forumFileRepository.findByForumFileName(originalFileName);
        if (forumFileOpt.isPresent()) {
            String changedFileName = forumFileOpt.get().getForumFileUrl();
            Path path = Paths.get(directPath + File.separator + changedFileName);
            System.out.println("File path: " + path.toString()); // 경로 출력

            if (Files.exists(path)) {
                return new ByteArrayResource(Files.readAllBytes(path));
            } else {
                throw new IOException("파일이 존재하지 않습니다.");
            }
        } else {
            throw new IOException("파일이 데이터베이스에 존재하지 않습니다.");
        }
    }
}

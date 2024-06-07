package com.bu200.forum.repository;

import com.bu200.forum.entity.ForumFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ForumFileRepository extends JpaRepository<ForumFile, Long> {
    List<ForumFile> findByForum_ForumCode(Long forumCode);

    Optional<ForumFile> findByForumFileName(String forumFileName);
}

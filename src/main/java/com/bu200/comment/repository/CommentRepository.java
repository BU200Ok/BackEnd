package com.bu200.comment.repository;

import com.bu200.comment.entity.Comment;
import com.bu200.forum.entity.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Override
    List<Comment> findAll();
    List<Comment> findByForumForumCode(Long forumCode);
}

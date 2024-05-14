package com.bu200.forum.repository;

import com.bu200.forum.entity.Forum;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface GetForumRepository extends JpaRepository<Forum, Long> {
    //게시글 리스트를 불러온다.
    Page<Forum> findAll(Pageable pageable);

    //게시글 리스트를 검색해서 불러온다.
    Page<Forum> findByTitleContaining(String keyword, Pageable pageable);

    //
}

package com.bu200.forum.repository;

import com.bu200.forum.entity.Forum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ForumRepository extends JpaRepository<Forum,Long> {
    // 모든 게시판
    List<Forum> findAll();

    // 내가 쓴 글 확인
    List<Forum> findByAccount_AccountId(String userName);

    //게시글 조회
    Optional<Forum> findByForumCode(Long forumCode);

    //페이지
    Page<Forum> findAll(Pageable pageable);


    //부서 게시판 목록
    @Query("SELECT f FROM Forum f JOIN FETCH f.account a JOIN FETCH a.team t JOIN FETCH t.department d WHERE d.departmentName = :departmentName")
    List<Forum> findAllByDepartmentList(@Param("departmentName") String departmentName);

    boolean existsByForumCodeAndAccount_AccountId(Long forumCode, String accountId);
    void deleteByForumCodeAndAccount_AccountId(Long forumCode, String accountId);


}

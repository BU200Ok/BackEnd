package com.bu200.forum.repository;

import com.bu200.forum.entity.Forum;
import com.bu200.login.entity.Account;
import com.bu200.security.entity.User;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    //부서 게시판 목록
    @Query("SELECT f FROM Forum f JOIN FETCH f.account a JOIN FETCH a.team t JOIN FETCH t.department d WHERE d.departmentName = :departmentName")
    List<Forum> findAllByDepartmentList(@Param("departmentName") String departmentName);

    //forumCode, accountCode 해당하는 게시글 존재여부
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Forum f WHERE f.forumCode = :forumCode AND f.account.accountCode = :accountCode")
    boolean existsByIdAndAccount_AccountCode(@Param("forumCode") Long forumCode, @Param("accountCode") Long accountCode);

    //삭제
    @Query("DELETE FROM Forum f WHERE f.forumCode = :forumCode AND f.account.accountCode = :accountCode")
    void deleteByIdAndAccount_AccountCode(@Param("forumCode") Long forumCode, @Param("accountCode") Long accountCode);


}

package com.bu200.project.repository;

import com.bu200.project.dto.AccountDTO;
import com.bu200.project.dto.ProjectDetailDTO;
import com.bu200.project.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    @Query("SELECT p.projectCode FROM Project p " +
            "WHERE p.projectOpenStatus = true " +
            "ORDER BY p.projectPriority ASC")
    Page<Long> findProjectCodes(Pageable pageable);

    @Query("select distinct p from Project p " +
            "join fetch p.account a " +
            "join fetch a.team t " +
            "join fetch t.department d " +
            "left join fetch p.accountProjects ap " +
            "left join fetch ap.account ap_a " +
            "where p.projectCode in :projectCodes")
    List<Project> findAllProjectWithOtherDetails(@Param("projectCodes") List<Long> projectCodes);

    @Query("SELECT p.projectCode from Project p " +
            "WHERE p.projectOpenStatus = true " +
            "AND p.account.accountCode = :accountCode " +
            "ORDER BY p.projectPriority ASC")
    Page<Long> findMyProjectCodes(@Param("accountCode") Long accountCode, Pageable pageable);


    @Query("SELECT p.projectCode from Project p " +
            "where p.projectOpenStatus = true " +
            "and p.projectName like %:keyword% " +
            "order by p.projectPriority ASC ")
    Page<Long> searchProjectsByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT p.projectCode from Project p " +
            "where p.projectOpenStatus = true " +
            "and p.projectName like %:keyword% " +
            "and p.account.accountCode = :accountCode " +
            "ORDER BY p.projectPriority asc")
    Page<Long> searchMyProjectsByKeyword(@Param("keyword") String keyword,
                                         @Param("accountCode") Long accountCode,
                                         Pageable pageable);


    Project findByProjectNameAndProjectOpenStatusIsTrue(String projectName);

    @Query("select new com.bu200.project.dto.ProjectDetailDTO" +
            "(p.projectCode, p.projectName, p.projectStart, p.projectEnd, p.projectStatus, p.projectDescription," +
            "a.accountName, a.accountEmail, d.departmentName, t.teamName) From Project p "+
            "join p.account a " +
            "join a.team t "+
            "join t.department d " +
            "where p.projectCode = :projectCode")
    ProjectDetailDTO findProjectDetail(@Param("projectCode") Long projectCode);

    @Query("select new com.bu200.project.dto.AccountDTO" +
            "(a.accountName, a.accountPosition, t.teamName, d.departmentName) from Project p " +
            "join p.accountProjects ap " +
            "join ap.account a " +
            "left join a.team t " +
            "left join t.department d " +
            "where p.projectCode = :projectCode"
            )
    List<AccountDTO> findProjectDeTailAccount(@Param("projectCode") Long projectCode);
}
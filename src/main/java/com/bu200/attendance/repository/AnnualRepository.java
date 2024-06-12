package com.bu200.attendance.repository;

import com.bu200.attendance.entity.Annual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnnualRepository extends JpaRepository<Annual, Long> {
    @Query("SELECT al FROM Annual al WHERE al.accountCode = :userCode AND YEAR(al.annualStart) = YEAR(CURRENT_DATE)")
    List<Annual> findAllByYear(@Param("userCode") Long userCode);
}

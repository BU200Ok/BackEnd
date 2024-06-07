package com.bu200.project.repository;

import com.bu200.project.dto.ReferenceRoomFileDTO;
import com.bu200.project.entity.TaskFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskFileRepository extends JpaRepository<TaskFile, Long> {
    //task -> taskPost -> taskFile
    @Query("select tf.taskFileCode from TaskFile tf " +
            "join tf.taskPost tp " +
            "join tp.task t " +
            "where t.taskCode = :taskCode")
    Page<Long> getTaskFilesCode(@Param("taskCode") Long taskCode, Pageable pageable);

    @Query("select new com.bu200.project.dto.ReferenceRoomFileDTO" +
            "(tp.taskPostCode, tp.taskPostDetail, tp.taskPostTime, a.accountName, tf.taskFileName, tf.taskFileRename) from TaskFile tf " +
            "join tf.taskPost tp " +
            "join tp.account a " +
            "where tf in :taskFileCodes")
    List<ReferenceRoomFileDTO> getTaskFiles(@Param("taskFileCodes")List<Long> taskFileCodes);

}

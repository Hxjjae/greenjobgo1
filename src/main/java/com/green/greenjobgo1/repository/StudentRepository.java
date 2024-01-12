package com.green.greenjobgo1.repository;

import com.green.greenjobgo1.common.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {
    StudentEntity findById(String Id);

    List<StudentEntity> findByIdAndRoleAndStartedAtAndEndedAtAfter(String id, String role, LocalDate startedAt, LocalDate EndedAt);

    List<StudentEntity> findByStartedAtBeforeAndEndedAtAfterAndEditableYn(LocalDate currentDate, LocalDate currentDate1, int i);

    List<StudentEntity> findByEndedAtBefore(LocalDate now);
}

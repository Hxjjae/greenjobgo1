package com.green.greenjobgo1.repository;

import com.green.greenjobgo1.common.entity.CourseSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminSubjectRepository extends JpaRepository<CourseSubjectEntity, Long> {
    CourseSubjectEntity findBySubjectNameAndRoundAndDelYn(String subjectName,int round,int delYn);

    CourseSubjectEntity findBySubjectNameAndRound(String subjectName, int round);
}

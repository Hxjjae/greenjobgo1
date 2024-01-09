package com.green.greenjobgo1.repository;

import com.green.greenjobgo1.common.entity.CourseSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminSubjectRepository extends JpaRepository<CourseSubjectEntity, Long> {
    CourseSubjectEntity findBySubjectName(String subjectName);
}

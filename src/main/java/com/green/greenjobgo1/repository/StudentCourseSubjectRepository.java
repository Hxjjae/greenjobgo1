package com.green.greenjobgo1.repository;

import com.green.greenjobgo1.config.entity.StudentCourseSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseSubjectRepository extends JpaRepository<StudentCourseSubjectEntity,Long> {
}

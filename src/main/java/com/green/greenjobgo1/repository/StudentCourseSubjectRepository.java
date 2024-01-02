package com.green.greenjobgo1.repository;

import com.green.greenjobgo1.config.entity.StudentCourseSubjectEntity;
import com.green.greenjobgo1.config.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentCourseSubjectRepository extends JpaRepository<StudentCourseSubjectEntity,Long> {
    Optional<StudentCourseSubjectEntity> findByStudentEntity(StudentEntity entity);
}

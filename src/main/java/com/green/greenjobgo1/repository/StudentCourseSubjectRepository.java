package com.green.greenjobgo1.repository;

import com.green.greenjobgo1.common.entity.CourseSubjectEntity;
import com.green.greenjobgo1.common.entity.StudentCourseSubjectEntity;
import com.green.greenjobgo1.common.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentCourseSubjectRepository extends JpaRepository<StudentCourseSubjectEntity,Long> {
    //Optional<StudentCourseSubjectEntity> findByStudentEntity(StudentEntity entity);
    List<StudentCourseSubjectEntity>findByStudentEntity(StudentEntity entity);

    StudentCourseSubjectEntity findByCourseSubjectEntityAndStudentEntity(CourseSubjectEntity courseSubjectEntity, StudentEntity entity);
}

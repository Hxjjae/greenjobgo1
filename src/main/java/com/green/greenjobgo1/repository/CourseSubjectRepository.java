package com.green.greenjobgo1.repository;

import com.green.greenjobgo1.common.entity.CategorySubjectEntity;
import com.green.greenjobgo1.common.entity.CourseSubjectEntity;
import com.green.greenjobgo1.common.entity.StudentCourseSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseSubjectRepository extends JpaRepository<CourseSubjectEntity, Long> {
    CourseSubjectEntity findByScsListIn(List<StudentCourseSubjectEntity> scsList);
}

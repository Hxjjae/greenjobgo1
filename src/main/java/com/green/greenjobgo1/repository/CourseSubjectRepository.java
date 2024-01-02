package com.green.greenjobgo1.repository;

import com.green.greenjobgo1.config.entity.CourseSubjectEntity;
import com.green.greenjobgo1.config.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseSubjectRepository extends JpaRepository<CourseSubjectEntity, Long> {
}

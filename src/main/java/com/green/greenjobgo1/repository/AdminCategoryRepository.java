package com.green.greenjobgo1.repository;

import com.green.greenjobgo1.admin.category.model.AdminCategoryVo;
import com.green.greenjobgo1.admin.category.model.CategoryMapping;
import com.green.greenjobgo1.admin.subject.model.AdminSubjectInsRes;
import com.green.greenjobgo1.config.entity.CategorySubjectEntity;
import com.green.greenjobgo1.config.entity.CourseSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminCategoryRepository extends JpaRepository<CategorySubjectEntity, Long> {
}

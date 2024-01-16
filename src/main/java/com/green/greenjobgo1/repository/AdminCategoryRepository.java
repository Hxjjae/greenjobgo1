package com.green.greenjobgo1.repository;

import com.green.greenjobgo1.common.entity.CategorySubjectEntity;
import com.green.greenjobgo1.common.entity.CourseSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AdminCategoryRepository extends JpaRepository<CategorySubjectEntity, Long> {

    List<CategorySubjectEntity> findByDelYn(Integer delYn);
}

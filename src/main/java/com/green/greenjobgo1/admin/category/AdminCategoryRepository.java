package com.green.greenjobgo1.admin.category;

import com.green.greenjobgo1.config.entity.CategorySubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminCategoryRepository extends JpaRepository<CategorySubjectEntity, Long> {
}

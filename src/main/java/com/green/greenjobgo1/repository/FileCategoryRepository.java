package com.green.greenjobgo1.repository;

import com.green.greenjobgo1.common.entity.FileCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileCategoryRepository extends JpaRepository<FileCategoryEntity, Long> {
}

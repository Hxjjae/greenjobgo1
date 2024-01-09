package com.green.greenjobgo1.repository;

import com.green.greenjobgo1.common.entity.FileCategoryEntity;
import com.green.greenjobgo1.common.entity.FileEntity;
import com.green.greenjobgo1.common.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findAllByStudentEntity(StudentEntity entity);

    List<FileEntity> findByFileCategoryEntityAndStudentEntity(FileCategoryEntity fileCategoryEntity, StudentEntity studentEntity);
}

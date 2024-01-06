package com.green.greenjobgo1.repository;

import com.green.greenjobgo1.config.entity.FileEntity;
import com.green.greenjobgo1.config.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findByStudentEntity(StudentEntity entity);
}

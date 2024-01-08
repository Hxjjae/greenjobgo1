package com.green.greenjobgo1.repository;

import com.green.greenjobgo1.config.entity.FileEntity;
import com.green.greenjobgo1.config.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findAllByStudentEntity(StudentEntity entity);
}

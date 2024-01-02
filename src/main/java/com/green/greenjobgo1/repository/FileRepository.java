package com.green.greenjobgo1.repository;

import com.green.greenjobgo1.config.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}

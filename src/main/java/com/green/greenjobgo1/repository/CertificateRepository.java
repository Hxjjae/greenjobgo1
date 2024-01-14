package com.green.greenjobgo1.repository;

import com.green.greenjobgo1.common.entity.CertificateEntity;
import com.green.greenjobgo1.common.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificateRepository extends JpaRepository<CertificateEntity, Long> {
    List<CertificateEntity> findByStudentEntity(StudentEntity studentEntity);
}

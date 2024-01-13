package com.green.greenjobgo1.repository;

import com.green.greenjobgo1.common.entity.CertificateEntity;
import com.green.greenjobgo1.common.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<CertificateEntity, Long> {
    CertificateEntity findByStudentEntity(StudentEntity studentEntity);
}

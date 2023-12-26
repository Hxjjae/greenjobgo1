package com.green.greenjobgo1.repository;

import com.green.greenjobgo1.config.entity.CompanyEntity;
import com.green.greenjobgo1.config.entity.CompanyListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity,Long> {
}

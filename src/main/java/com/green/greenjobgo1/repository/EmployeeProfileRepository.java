package com.green.greenjobgo1.repository;


import com.green.greenjobgo1.config.entity.EmployeeProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeProfileRepository extends JpaRepository<EmployeeProfileEntity,Long> {
    EmployeeProfileEntity findByName(String name);
}

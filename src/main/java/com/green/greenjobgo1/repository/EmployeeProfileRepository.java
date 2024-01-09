package com.green.greenjobgo1.repository;


import com.green.greenjobgo1.common.entity.EmployeeProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeProfileRepository extends JpaRepository<EmployeeProfileEntity,Long> {
    EmployeeProfileEntity findByName(String name);
    List<EmployeeProfileEntity> findByDelYn(int delyn);
}

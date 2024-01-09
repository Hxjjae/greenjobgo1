package com.green.greenjobgo1.admin;

import com.green.greenjobgo1.common.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity,Long>{
    AdminEntity findById(String id);
}

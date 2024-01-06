package com.green.greenjobgo1.admin.std_management.model;

import com.green.greenjobgo1.config.entity.CertificateEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminStudentUpdDto {
    private Long istudent;
    private String studentName;
    private String address;
    private String email;
    private String education;
    private String certificate;
}
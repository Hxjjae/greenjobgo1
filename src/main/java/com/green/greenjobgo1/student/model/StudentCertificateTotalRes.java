package com.green.greenjobgo1.student.model;

import com.green.greenjobgo1.admin.std_management.model.AdminStudentCertificateRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentCertificateTotalRes {
    private List<CertificateRes> res;
    private Long istudent;
}

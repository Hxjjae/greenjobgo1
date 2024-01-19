package com.green.greenjobgo1.admin.std_management.model;

import com.green.greenjobgo1.common.entity.CertificateEntity;
import com.green.greenjobgo1.student.model.StudentCertificateRes;
import com.green.greenjobgo1.student.model.StudentCertificateSelRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminStudentDetailRes {
    private int age;
    private String gender;
    private String name;
    private LocalDate birthday;
    private String address;
    private String email;
    private LocalDate startedAt;
    private LocalDate endedAt;
    private String mobileNumber;
    private String education;
    private String introducedLine;
    private int huntJobYn;
    private List<AdminStudentCertificateRes> Certificates;
    private AdminStudentDetailSubjectRes subject;
}

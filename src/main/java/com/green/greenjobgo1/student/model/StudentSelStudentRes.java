package com.green.greenjobgo1.student.model;

import com.green.greenjobgo1.admin.std_management.model.AdminStudentCertificateRes;
import com.green.greenjobgo1.admin.std_management.model.AdminStudentDetailSubjectRes;
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
public class StudentSelStudentRes {
    private Long istudent;
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
    private List<StudentCertificateSelRes> Certificates;
    private StudentDetailSubjectRes subject;
}

package com.green.greenjobgo1.student.model;

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
    private String name;
    private LocalDate startedAt;
    private LocalDate endedAt;
    private String address;
    private String mobileNumber;
    private String id;
    private String education;
    private StudentSelSubjectRes subject;
    private List<StudentCertificateSelRes> certificates;
}

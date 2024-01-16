package com.green.greenjobgo1.admin.std_management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminStudentRes {
    private Long istudent;
    private String classification;
    private String subjectName;
    private LocalDate startedAt;
    private LocalDate endedAt;
    private String name;
    private String gender;
    private String address;
    private String mobileNumber;
    private String education;
    private Long certificate;
    private Long file;
    private Integer huntJobYn;
}

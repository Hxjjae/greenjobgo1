package com.green.greenjobgo1.admin.student.model;

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
    private int gender;
    private String address;
    private String mobileNumber;
    private String education;
    private String certificate;
    private String file;
}

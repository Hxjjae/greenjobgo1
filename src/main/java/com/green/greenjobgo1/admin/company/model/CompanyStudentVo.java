package com.green.greenjobgo1.admin.company.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class CompanyStudentVo {
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

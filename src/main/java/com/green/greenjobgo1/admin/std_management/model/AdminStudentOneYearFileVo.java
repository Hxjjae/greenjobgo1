package com.green.greenjobgo1.admin.std_management.model;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class AdminStudentOneYearFileVo {
    private String classification;
    private String subjectName;
    private int round;
    private LocalDate startedAt;
    private LocalDate endedAt;
    private Long istudent;
    private String studentName;
    private String gender;
    private Long portfolio;
    private int storageYn;
}

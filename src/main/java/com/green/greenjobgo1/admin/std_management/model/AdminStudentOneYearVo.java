package com.green.greenjobgo1.admin.std_management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminStudentOneYearVo {
    private String subjectName;
    private int round;
    private String studentName;
    private Long istudent;
    private Long iclassification;
}

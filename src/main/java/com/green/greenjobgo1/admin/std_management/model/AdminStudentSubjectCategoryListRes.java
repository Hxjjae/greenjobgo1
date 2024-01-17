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
public class AdminStudentSubjectCategoryListRes {
    private Long iclassification;
    private String classification;
    private Long icourseSubject;
    private String subjectName;
    private Integer round;
    private LocalDate startedAt;
    private LocalDate endedAt;
}

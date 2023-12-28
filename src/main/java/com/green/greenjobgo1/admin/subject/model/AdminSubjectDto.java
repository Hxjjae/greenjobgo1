package com.green.greenjobgo1.admin.subject.model;

import com.green.greenjobgo1.config.entity.CategorySubjectEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminSubjectDto {
    private Long icourseSubject;
    private CategorySubjectEntity categorySubjectEntity;
    private String subjectName;
    private LocalDate startedAt;
    private LocalDate endedAt;
    private int subjectCondition;
    private int delYn;
    private int page;
    private int staIdx;
    private int size;


}

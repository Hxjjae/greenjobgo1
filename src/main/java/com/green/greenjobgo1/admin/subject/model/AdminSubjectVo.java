package com.green.greenjobgo1.admin.subject.model;

import com.green.greenjobgo1.common.entity.CategorySubjectEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminSubjectVo {
    private Long icourseSubject;
    private CategorySubjectEntity categorySubject;
    private String subjectName;
    private LocalDate startedAt;
    private LocalDate endedAt;
    private int subjectCondition;
}

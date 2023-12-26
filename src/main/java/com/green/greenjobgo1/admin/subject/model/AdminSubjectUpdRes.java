package com.green.greenjobgo1.admin.subject.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
@Getter
@Builder
public class AdminSubjectUpdRes {
    private Long icourseSubject;
    private String classification;
    private String courseSubjectName;
    private LocalDate startedAt;
    private LocalDate endedAt;
}

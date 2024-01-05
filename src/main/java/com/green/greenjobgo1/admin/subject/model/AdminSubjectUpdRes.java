package com.green.greenjobgo1.admin.subject.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Data
@Builder
public class AdminSubjectUpdRes {
    private Long icourseSubject;
    private Long iclassification;
    private String courseSubjectName;
    private LocalDate startedAt;
    private LocalDate endedAt;
    private int subjectCondition;
    private String instructor;
    private String lectureRoom;
}

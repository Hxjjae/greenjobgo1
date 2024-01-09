package com.green.greenjobgo1.admin.subject.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AdminSubjectUpdDto {
    private Long icourseSubject;
    private String courseSubjectName;
    private Long iclassification;
    private LocalDate startedAt;
    private LocalDate endedAt;
    private String instructor;
    private String lectureRoom;
    private Integer round;
    private Integer classTime;
}

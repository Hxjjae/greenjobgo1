package com.green.greenjobgo1.admin.subject.model;

import com.green.greenjobgo1.config.entity.CategorySubjectEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class AdminSubjectInsRes {
    private Long icourseSubject;
    private String classification;
    private String courseSubjectName;
    private LocalDate startedAt;
    private LocalDate endedAt;
    private String instructor;
    private String lectureRoom;
    private int round;
    private int classTime;
}

package com.green.greenjobgo1.admin.subject.model;

import com.green.greenjobgo1.config.entity.CategorySubjectEntity;
import com.green.greenjobgo1.config.entity.CourseSubjectEntity;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AdminSubjectInsDto {
    private String courseSubjectName;
    private String classification;
    private LocalDate startedAt;
    private LocalDate endedAt;
    private String instructor;
    private String lectureRoom;
    private int round;
    private int classTime;
}

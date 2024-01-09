package com.green.greenjobgo1.admin.subject.model;

import com.green.greenjobgo1.common.entity.CourseSubjectEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AdminSubjectDelRes {
    private Long icourseSubject;
    private String courseSubjectName;
    private int delYn;
}

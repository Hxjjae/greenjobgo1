package com.green.greenjobgo1.admin.subject.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminSubjectDelRes {
    private Long icourseSubject;
    private String courseSubjectName;
    private int delYn;
}

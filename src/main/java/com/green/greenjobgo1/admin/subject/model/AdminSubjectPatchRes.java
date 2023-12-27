package com.green.greenjobgo1.admin.subject.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminSubjectPatchRes {
    private Long icourseSubject;
    private int subjectCondition;
}

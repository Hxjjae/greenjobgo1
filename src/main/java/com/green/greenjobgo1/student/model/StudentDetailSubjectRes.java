package com.green.greenjobgo1.student.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetailSubjectRes {
    private Long icourseSubject;
    private String subjectName;
}

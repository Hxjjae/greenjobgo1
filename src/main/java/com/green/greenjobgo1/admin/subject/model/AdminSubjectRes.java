package com.green.greenjobgo1.admin.subject.model;

import com.green.greenjobgo1.common.utils.PagingUtils;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminSubjectRes {
    private Long icourseSubject;
    private String classification;
    private String courseSubjectName;
    private LocalDate startedAt;
    private LocalDate endedAt;
    private int delYn;
}

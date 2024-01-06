package com.green.greenjobgo1.admin.std_management.model;

import com.green.greenjobgo1.config.entity.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminStudentRoleRes {
    private Long icourseSubject;
    private LocalDate startedAt;
    private LocalDate endedAt;
    private int editableYn;

}

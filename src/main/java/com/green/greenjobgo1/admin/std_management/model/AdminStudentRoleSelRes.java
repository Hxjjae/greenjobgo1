package com.green.greenjobgo1.admin.std_management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminStudentRoleSelRes {
    private String subjectName;
    private int round;
    private LocalDate startedAt;
    private LocalDate endedAt;

}

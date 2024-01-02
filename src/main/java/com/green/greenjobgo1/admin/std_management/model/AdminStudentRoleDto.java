package com.green.greenjobgo1.admin.std_management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminStudentRoleDto {
    private Long istudent;
    private LocalDate startedAt;
    private LocalDate endedAt;
}

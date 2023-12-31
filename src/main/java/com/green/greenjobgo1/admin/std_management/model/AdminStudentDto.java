package com.green.greenjobgo1.admin.std_management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminStudentDto {
    private Long icategory;
    private String subjectName;
    private int page;
    private int staIdx;
    private int size;
}

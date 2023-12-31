package com.green.greenjobgo1.admin.student.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminStorageStudentDto {
    private int page;
    private int staIdx;
    private int size;
}

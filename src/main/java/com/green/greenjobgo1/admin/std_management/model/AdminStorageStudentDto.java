package com.green.greenjobgo1.admin.std_management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminStorageStudentDto {
    private Long iclassfication;
    private String subjectName;
    private String studentName;
    private int storageYn;
    private int page;
    private int staIdx;
    private int size;
}

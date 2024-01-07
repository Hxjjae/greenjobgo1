package com.green.greenjobgo1.admin.std_management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminStorageStudentRes {
    private String img;
    private Long istudent;
    private String studentName;
    private String subjectName;
    private int storageYn;

}

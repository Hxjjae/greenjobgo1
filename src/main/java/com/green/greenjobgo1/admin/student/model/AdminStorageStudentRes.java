package com.green.greenjobgo1.admin.student.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminStorageStudentRes {
    private Long ifile;
    private String file;
    private Long istudent;
    private String studentName;
    private String subjectName;

}

package com.green.greenjobgo1.admin.std_management.model;

import lombok.Data;

@Data
public class AdminStudentFileUpdDto {
    private Long ifile;
    private Long istudent;
    private Long iFileCategory;
    private String file;
    private String fileLink;
    private String introducedLine;
}

package com.green.greenjobgo1.student.model;

import lombok.Data;

@Data
public class StudentPatchDto {
    private Long ifile;
    private Long istudent;
    private Long iFileCategory;
    private String file;
    private String fileLink;
    private String introducedLine;
}

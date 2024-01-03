package com.green.greenjobgo1.student.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data

public class StudentInsDto {
    private Long ifile;
    private Long istudent;
    private Long iFileCategory;
    private String file;
}

package com.green.greenjobgo1.student.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data

public class StudentInsDto {
    private Long ifile;
    private Long istudent;
    private Long iFileCategory;
    private String file;
    private String fileLink;
    private String introducedLine;
    private String oneWord;
    private List<String> certificates;
}

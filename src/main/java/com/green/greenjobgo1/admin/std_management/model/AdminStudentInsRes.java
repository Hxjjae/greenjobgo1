package com.green.greenjobgo1.admin.std_management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminStudentInsRes {
    private Long ifile;
    private Long istudent;
    private String file;
    private LocalDate createdAt;
    private String oneWord;
}

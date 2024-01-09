package com.green.greenjobgo1.student.model;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentInsRes {
    private Long ifile;
    private Long istudent;
    private String file;
    private LocalDate createdAt;

    public StudentInsRes(StudentInsRes res) {
        this.ifile = res.getIfile();
        this.istudent = res.getIstudent();
        this.file = res.getFile();
        this.createdAt = res.createdAt;
    }
}

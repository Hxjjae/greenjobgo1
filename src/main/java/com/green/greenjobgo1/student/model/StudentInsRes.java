package com.green.greenjobgo1.student.model;

import com.green.greenjobgo1.config.entity.FileEntity;
import com.green.greenjobgo1.config.entity.StudentEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

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

package com.green.greenjobgo1.student.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentFileRes {
    private Long ifile;
    private String file;
    private String oneWord;
    private Integer mainYn;
    private String originFileName;
}

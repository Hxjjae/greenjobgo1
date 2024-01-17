package com.green.greenjobgo1.student.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentPortfolioMainRes {
    private Long ifile;
    private Long istudent;
    private Integer mainYn;
}

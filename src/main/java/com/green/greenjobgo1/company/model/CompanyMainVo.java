package com.green.greenjobgo1.company.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyMainVo {
    private String img;
    private Long  istudent;
    private String name;
    private String subjectName;
}

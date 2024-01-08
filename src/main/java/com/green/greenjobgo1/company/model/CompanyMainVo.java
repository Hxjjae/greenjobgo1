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
    private String file;
    private Long  istudent;
    private String name;
    private String subjectName;
    private String classification;
}

package com.green.greenjobgo1.company.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyStdVo {
    private String file;
    private Long  istudent;
    private String name;
    private String subjectName;
    private String classification;

}

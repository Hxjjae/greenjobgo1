package com.green.greenjobgo1.company.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyStdVo {
    private String subjectName;
    private String studentName;
    private Long istudent;
    private String img;

}

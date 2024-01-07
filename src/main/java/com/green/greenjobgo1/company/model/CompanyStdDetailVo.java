package com.green.greenjobgo1.company.model;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyStdDetailVo {
    private String file;
    private Long  istudent;
    private String name;
    private LocalDate birthday;
    private String subjectName;
    private String id;
    private LocalDate startedAt;
    private LocalDate endedAt;
    private String education;
    private String classification;
}

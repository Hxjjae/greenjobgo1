package com.green.greenjobgo1.company.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyStdVo {
    private Long  istudent;
    private String stdname;
    private String subejctName;
}

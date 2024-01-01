package com.green.greenjobgo1.admin.company.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class CompanyVo {
    private Long icompany;
    private String id;
    private LocalDate startedAt;
    private LocalDate endedAt;
}

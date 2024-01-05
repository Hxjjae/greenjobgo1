package com.green.greenjobgo1.admin.companylist.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Builder
public class CompanylistRes {
    private Long companyCode;
    private String area;
    private String companyName;
    private String leaderName;
    private String jobField;
    private String manager;
    private String phoneNumber;
    private LocalDate dateConslusion;
}

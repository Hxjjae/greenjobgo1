package com.green.greenjobgo1.admin.companylist.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class CompanyInsVo {
    private String status;
    private String message;
    private Long companyCode;
    private String area;
    private String companyName;
    private String leaderName;
    private String homepage;
    private String manager;
    private String phoneNumber;
    private LocalDate dateConslusion;

}

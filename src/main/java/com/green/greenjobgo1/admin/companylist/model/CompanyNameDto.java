package com.green.greenjobgo1.admin.companylist.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CompanyNameDto {
    private String area;
    private String companyName;
    private String leaderName;
    private String jobField;
    private String manger;
    private String phoneNumber;
    private LocalDate dateConslusion;
    private String sector;
}


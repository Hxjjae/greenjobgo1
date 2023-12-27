package com.green.greenjobgo1.admin.companylist.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanylistRes {
    private Long companyCode;
    private String companyName;
    private String sector;
    private String manger;
    private String phoneNumber;
    private String dateConslusion;
}

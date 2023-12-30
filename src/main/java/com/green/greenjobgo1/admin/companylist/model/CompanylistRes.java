package com.green.greenjobgo1.admin.companylist.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CompanylistRes {
    private Long companyCode;
    private Long companyNumber;
    private String companyName;
    private String sector;
    private String manger;
    private String phoneNumber;
    private String dateConslusion;
}

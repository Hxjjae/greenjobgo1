package com.green.greenjobgo1.admin.companylist.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class CompanylistRes {
    private Long companyCode;
    private String area;
    private String companyName;
    private String sector;
    private String leaderName;
    private String manger;
    private String phoneNumber;
    private String dateConslusion;
}

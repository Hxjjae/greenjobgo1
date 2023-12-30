package com.green.greenjobgo1.admin.companylist.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CompanylistVo {
    private int maxpage;
    private int totalcount;
    private List<CompanylistRes> list;
}

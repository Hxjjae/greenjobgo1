package com.green.greenjobgo1.company.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CompanyStdRes {
    private int maxpage;
    private int totalcount;
    private List<CompanyStdVo> list;
}

package com.green.greenjobgo1.company.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CompanystdDetailRes {
    private CompanyStdDetailVo vo;
    private List<CompanyStdfileVo> file;
}

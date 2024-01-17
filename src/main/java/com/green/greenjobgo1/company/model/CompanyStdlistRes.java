package com.green.greenjobgo1.company.model;

import com.green.greenjobgo1.common.utils.PagingUtils;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CompanyStdlistRes {
    private PagingUtils page;
    private int maxpage;
    private int totalcount;
    private List<CompanyStdlistVo> vo;
}

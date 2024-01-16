package com.green.greenjobgo1.company.model;

import com.green.greenjobgo1.common.utils.PagingUtils;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CompanyStdGalleryRes {
    private PagingUtils page;
    private int maxpage;
    private int totalcount;
    private List<CompanyStdGalleryVo> vo;
}

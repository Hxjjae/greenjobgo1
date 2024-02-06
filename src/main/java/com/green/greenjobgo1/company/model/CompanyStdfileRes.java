package com.green.greenjobgo1.company.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyStdfileRes {
    private CompanythumbnailVo thumbnail;
    private CompanyAboutMeVo aboutMe;
    private List<CompanystdportfolioVo> portfolio;
    private List<CompanyStdfileImgVo> fileLink;

}

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
    private String img;
    private List<CompanyStdfileImgVo> portfolio;
    private List<CompanyStdfileImgVo> fileLink;

}

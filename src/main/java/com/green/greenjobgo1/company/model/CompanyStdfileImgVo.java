package com.green.greenjobgo1.company.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyStdfileImgVo {
    private String file;
    private String oneWord;
    private int mainYn;
}

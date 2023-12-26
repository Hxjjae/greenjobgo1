package com.green.greenjobgo1.admin.company.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyVo {
    private Long icompany;
    private String id;
    private String password;
    private String role;
    private int viewableYn;

}

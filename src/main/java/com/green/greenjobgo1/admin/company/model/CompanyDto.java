package com.green.greenjobgo1.admin.company.model;

import lombok.Data;

@Data
public class CompanyDto {
    private String id;
    private String password;
    private String role;
    private String viewableYn;
}

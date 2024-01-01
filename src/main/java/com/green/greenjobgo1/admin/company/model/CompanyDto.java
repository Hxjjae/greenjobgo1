package com.green.greenjobgo1.admin.company.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CompanyDto {
    private String id;
    private String pw;
    private String role;
    private LocalDate startedAt;
    private LocalDate endedAt;
}

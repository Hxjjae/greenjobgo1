package com.green.greenjobgo1.admin.std_management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminMainPortfolioPatchDto {
    private List<Long> istudent;
    private int companyMainYn;
}

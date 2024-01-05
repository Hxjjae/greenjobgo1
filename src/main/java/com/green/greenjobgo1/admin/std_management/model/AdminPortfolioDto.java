package com.green.greenjobgo1.admin.std_management.model;

import lombok.Data;

@Data
public class AdminPortfolioDto {
    private Long iclassfication;
    private String subjectName;
    private String studentName;
    private int page;
    private int staIdx;
    private int size;
}

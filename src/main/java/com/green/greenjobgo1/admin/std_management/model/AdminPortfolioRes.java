package com.green.greenjobgo1.admin.std_management.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminPortfolioRes {
    private String subjectName;
    private String studentName;
    private String introducedLine;
    private String img;

}

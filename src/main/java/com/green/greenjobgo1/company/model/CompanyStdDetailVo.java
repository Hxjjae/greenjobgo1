package com.green.greenjobgo1.company.model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyStdDetailVo {
    private String name;
    private LocalDate birthday;
    private String address;
    private String addressDetail;
    private String email;
    private LocalDate startedAt;
    private LocalDate endedAt;
    private String mobileNumber;
    private String education;
    private List<CompanyStudentCertificateRes> certificates;
}

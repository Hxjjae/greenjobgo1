package com.green.greenjobgo1.company.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyStdDetail {
    private String name;
    private String gender;
    private int age;
    private LocalDate birthday;
    private String address;
    private String email;
    private LocalDate startedAt;
    private LocalDate endedAt;
    private String subjectName;
    private String mobileNumber;
    private String education;
}

package com.green.greenjobgo1.admin.std_management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminStudentDetailRes {
    private String name;
    private LocalDate birthday;
    private String address;
    private String addressDetail;
    private String email;
    private LocalDate startedAt;
    private LocalDate endedAt;
    private String mobileNumber;
    private String education;
}

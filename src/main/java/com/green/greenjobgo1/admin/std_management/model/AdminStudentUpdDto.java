package com.green.greenjobgo1.admin.std_management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminStudentUpdDto {
    private Long istudent;
    private String studentName;
    private String address;
    private String email;
    private String education;
    private String certificate;
    private String mobileNumber;
    private Integer huntJobYn;
    private LocalDate birthday;
    private String gender;
}

package com.green.greenjobgo1.admin.std_management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}

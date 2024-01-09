package com.green.greenjobgo1.admin.std_management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminStudentUpdRes {
    private Long istudent;
    private String studentName;
    private String address;
    private String email;
    private String education;
    private List<AdminStudentCertificateRes> certificate;

}

package com.green.greenjobgo1.admin.std_management.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
public class AdminStudentCertificateListDto {
    private Long istudent;
    private List<String> certificate;
}

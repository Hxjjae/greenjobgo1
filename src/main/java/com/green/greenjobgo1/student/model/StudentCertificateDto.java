package com.green.greenjobgo1.student.model;

import lombok.Data;

import java.util.List;

@Data
public class StudentCertificateDto {
    private Long istudent;
    private List<String> certificates;
}

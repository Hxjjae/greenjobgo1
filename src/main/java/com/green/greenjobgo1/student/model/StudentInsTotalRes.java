package com.green.greenjobgo1.student.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentInsTotalRes {
    private StudentInsRes res;
    private StudentIntroducedLineRes std;
    private List<CertificateRes> certRes;
}

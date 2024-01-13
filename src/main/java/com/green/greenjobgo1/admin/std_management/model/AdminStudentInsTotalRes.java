package com.green.greenjobgo1.admin.std_management.model;

import com.green.greenjobgo1.student.model.CertificateRes;
import com.green.greenjobgo1.student.model.StudentInsRes;
import com.green.greenjobgo1.student.model.StudentIntroducedLineRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminStudentInsTotalRes {
    private AdminStudentInsRes res;
    private AdminStudentIntroducedLineRes std;
    private List<CertificateRes> certRes;
}

package com.green.greenjobgo1.admin.std_management.model;

import com.green.greenjobgo1.student.model.CertificateRes;
import com.green.greenjobgo1.student.model.StudentIntroducedLineRes;
import com.green.greenjobgo1.student.model.StudentPatchRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminStudentFileUpdTotalRes {
    private AdminStudentFileUpdRes res;
    private AdminStudentIntroducedLineRes std;
    private List<CertificateRes> certRes;
}

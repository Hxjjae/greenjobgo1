package com.green.greenjobgo1.student.model;

import com.green.greenjobgo1.admin.std_management.model.AdminStudentFileLink;
import com.green.greenjobgo1.admin.std_management.model.AdminStudentFileRes;
import com.green.greenjobgo1.admin.std_management.model.AdminStudentImg;
import com.green.greenjobgo1.admin.std_management.model.AdminStudentResume;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentSelFileRes {
    private StudentImg img;
    private StudentResume resume;
    private List<StudentFileRes> portfolio;
    private List<StudentFileLink> fileLinks;
}

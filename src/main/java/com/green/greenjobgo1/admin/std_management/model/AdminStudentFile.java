package com.green.greenjobgo1.admin.std_management.model;

import com.querydsl.core.Tuple;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminStudentFile {
    private AdminStudentImg img;
    private AdminStudentResume resume;
    private List<AdminStudentFileRes> portfolio;
    private List<AdminStudentFileLink> fileLinks;
}

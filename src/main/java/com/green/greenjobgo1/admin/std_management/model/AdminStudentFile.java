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
public class AdminStudentFile {
    private String img;
    private String resume;
    private List<AdminStudentFileRes> portfolio;
    private List<AdminStudentFileLink> fileLinks;
}

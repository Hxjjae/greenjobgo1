package com.green.greenjobgo1.admin.std_management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminStudentResume {
    private Long ifile;
    private String resume;
    private String oneWord;
    private String originFileName;
}

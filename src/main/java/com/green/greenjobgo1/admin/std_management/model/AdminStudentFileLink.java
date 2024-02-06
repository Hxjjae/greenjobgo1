package com.green.greenjobgo1.admin.std_management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminStudentFileLink {
    private Long ifile;
    private String fileLink;
    private String oneWord;
    private Integer mainYn;
    private String originFileName;
}

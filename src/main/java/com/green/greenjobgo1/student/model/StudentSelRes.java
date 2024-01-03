package com.green.greenjobgo1.student.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentSelRes {
    private Long istudent;
    private String name;
    private String subjectName;
    private LocalDate startedAt;
    private LocalDate endedAt;
    private String address;
    private String mobileNumber;
    private String id;
    private String education;
}

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
public class StudentSelTotalRes {
    private StudentSelStudentRes std;
    private StudentSelFileRes file;
    private int portfolioYn;
    private int aboutMeYn;
}

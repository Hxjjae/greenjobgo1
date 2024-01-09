package com.green.greenjobgo1.admin.sign.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Builder
public class StudentExcel {
    private String subjectName;
    private String round;
    private String startedAt;
    private String endedAt;
    private String trainingTime;
    private String studentName;
    private String birthday;
    private String phone;
    private String email;
    private String address;
    private String employee;
    private String gender;
    private String age;
    private String education;
    private int resume;
    private int portfolio;

}

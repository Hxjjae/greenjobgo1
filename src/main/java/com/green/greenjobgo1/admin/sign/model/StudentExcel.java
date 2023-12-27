package com.green.greenjobgo1.admin.sign.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentExcel {
    private String subjectName;
    private String number;
    private String startedAt;
    private String endedAt;
    private String trainingTime;
    private String studentName;
    private String birthday;
    private String phone;
    private String email;
    private String address;
    private String teacherName;
    private String gender;
    private String age;
    private String education;
    private int resume;
    private int portfolio;

}

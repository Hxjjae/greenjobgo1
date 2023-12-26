package com.green.greenjobgo1.admin.EmployeeProfile.model;

import lombok.Data;

@Data
public class EmployeePutProfileDto {
    private Long iemply;
    private String name;
    private String phone;
    private String email;
    private String profilePic;
    private String kakaoId;
}

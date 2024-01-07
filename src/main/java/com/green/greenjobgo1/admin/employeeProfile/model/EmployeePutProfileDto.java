package com.green.greenjobgo1.admin.employeeProfile.model;

import lombok.Data;

@Data
public class EmployeePutProfileDto {
    private Long iemply;
    private String name;
    private String phone;
    private String email;
    private String profilePic;
}

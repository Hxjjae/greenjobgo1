package com.green.greenjobgo1.admin.EmployeeProfile.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmployeeProfileVo {
    private Long iemply;
    private String oneWord;
    private String name;
    private String conuselingNumber;
    private String phoneNumber;
    private String email;
    private String profilePic;
    private String kakaoId;
}

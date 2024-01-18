package com.green.greenjobgo1.admin.sign.model;

import com.green.greenjobgo1.common.security.sign.model.SignUpResultDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class AdminSignInResultDto extends SignUpResultDto {
    private String accessToken;
    private Long accessTokenTime;
    private String refreshToken;
    private String role;
    private String id;
}

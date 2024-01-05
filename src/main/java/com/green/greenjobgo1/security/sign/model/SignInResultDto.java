package com.green.greenjobgo1.security.sign.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class SignInResultDto extends SignUpResultDto {
    private String accessToken;
    private Long accessTokenTime;
    private String refreshToken;
    private String role;
    private String id;
}

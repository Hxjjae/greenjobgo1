package com.green.greenjobgo1.security.sign.model;

import lombok.Data;

@Data
public class UserUpdDto {
    private Long iuser;
    private String secretKey;
}

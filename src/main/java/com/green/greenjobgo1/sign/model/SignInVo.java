package com.green.greenjobgo1.sign.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInVo {
    private int editableYn;
    private int portfolioYn;
    private int aboutMeYn;
}

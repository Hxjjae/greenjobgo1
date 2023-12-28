package com.green.greenjobgo1.common.utils;

import com.green.greenjobgo1.security.CommonRes;
import com.green.greenjobgo1.security.sign.model.SignUpResultDto;

public class ResultUtils {
    public static void setSuccessResult(SignUpResultDto result) {
        result.setSuccess(true);
        result.setCode(CommonRes.SUCCESS.getCode());
        result.setMsg(CommonRes.SUCCESS.getMsg());
    }

    public static void setFailResult(SignUpResultDto result) {
        result.setSuccess(false);
        result.setCode(CommonRes.FAIL.getCode());
        result.setMsg(CommonRes.FAIL.getMsg());
    }
}

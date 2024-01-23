package com.green.greenjobgo1.common.security.config.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String name();
    int getHttpStatus();
    String getMessage();
}

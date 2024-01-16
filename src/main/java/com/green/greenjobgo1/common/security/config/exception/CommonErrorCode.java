package com.green.greenjobgo1.common.security.config.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not exists"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
    SUBJECT_NULL(HttpStatus.UNPROCESSABLE_ENTITY,"없는 과목입니다."),
    EMPLOYEE_NULL(HttpStatus.UNPROCESSABLE_ENTITY,"존재하지 않는 직원입니다."),
    UPLOAD_FAILED(HttpStatus.NOT_ACCEPTABLE, "파일을 업로드 할 수 없습니다."),
    DELETE_FAILED(HttpStatus.NOT_ACCEPTABLE, "파일을 삭제할 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}

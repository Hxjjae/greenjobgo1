package com.green.greenjobgo1.common.security.config.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {
    INVALID_PARAMETER(500, "Invalid parameter included"),
    RESOURCE_NOT_FOUND(500, "Resource not exists"),
    INTERNAL_SERVER_ERROR(500, "Internal server error"),
    SUBJECT_NULL(500,"없는 과목입니다."),
    EMPLOYEE_NULL(500,"존재하지 않는 직원입니다."),
    //로그인 에러
    EMAIL_NULL(402,"존재하지 않는 이메일22222"),
    ID_NULL(500,"존재하지 않는 아이디"),
    PASSWORD_FAILED(500,"비밀번호 불일치."),
    EXPIRE_LOGIN(500,"조회만료"),
    DELETE_SUCCESS(500,"성공적으로 삭제되었습니다."),

    NOT_AUTHORIZED(452, "editableYn이 활성화 되지 않았습니다."),
    UPLOAD_FAILED(453, "파일을 업로드 할 수 없습니다."),
    DELETE_FAILED(454, "파일을 삭제할 수 없습니다."),
    MAIN_YN_FAILED(455, "특정 분야 카테고리의 대표포트폴리오가 10개가 넘습니다.");


    private final int httpStatus;
    private final String message;
}

package com.green.greenjobgo1.common.security.config.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SignErrorCode implements ErrorCode{
    EMAIL_NULL(432,"존재하지 않는 이메일"),
    ID_NULL(433,"존재하지 않는 아이디"),
    PASSWORD_FAILED(434,"비밀번호 불일치"),
    EXPIRE_LOGIN(435,"조회만료"),
    DELETE_SUCCESS(436,"성공적으로 삭제되었습니다."),


    SUBJECT_NULL(437,"없는 과목입니다."),
    EMPLOYEE_NULL(438,"존재하지 않는 직원입니다."),
    ID_DUPLICATE(439,"존재하는 아이디 입니다."),
    FILE_ERROR(445,"파일형식이 맞지 않습니다.");
    private final int httpStatus;
    private final String message;
}

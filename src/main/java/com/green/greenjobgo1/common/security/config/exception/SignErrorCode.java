package com.green.greenjobgo1.common.security.config.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SignErrorCode implements ErrorCode{
    EMAIL_NULL(432,"존재하지 않는 이메일입니다."),
    ID_NULL(433,"존재하지 않는 아이디입니다."),
    PASSWORD_FAILED(434,"비밀번호 불일치"),
    EXPIRE_LOGIN(435,"조회 할 수 없는 기간입니다."),
    DELETE_SUCCESS(436,"성공적으로 삭제 되었습니다."),


    SUBJECT_NULL(437,"과정 등록관리에 없는 과목이 있습니다."),
    EMPLOYEE_NULL(438,"존재하지 않는 직원이 있습니다."),
    ID_DUPLICATE(439,"존재하는 아이디 입니다."),
    FILE_ERROR(445,"파일형식이 맞지 않습니다.");
    private final int httpStatus;
    private final String message;
}

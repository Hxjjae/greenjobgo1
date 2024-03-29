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

    COMPANYLIST_DUPLICATE(440,"이미 존재하는 기업리스트 입니다."),
    LEADERNAME_NULL(441,"대표이름을 입력 해주세요"),
    AREA_NULL(442,"지역을 입력 해주세요"),
    MANAGER_NULL(443,"담당자를 입력 해주세요"),
    PHONE_NULL(444,"전화번호를 입력 해주세요"),
    EMPLOYEE_DUPLICATE(446, "이미 존재하는 직원입니다."),

    NOT_AUTHORIZED(452, "editableYn이 활성화 되지 않았습니다."),
    UPLOAD_FAILED(453, "파일을 업로드 할 수 없습니다."),
    DELETE_FAILED(454, "파일을 삭제할 수 없습니다."),
    COMPANY_MAIN_YN_FAILED(455, "특정 분야 카테고리의 대표포트폴리오가 10개가 넘습니다."),
    MAIN_YN_FAILED(456, "이미 등록된 대표 포트폴리오가 있습니다."),
    ONE_WORD_EMPTY(457, "업로드하신 파일 또는 링크의 한줄소개가 비어있습니다."),
    INTRODUCED_LINE_EMPTY(458, "업로드하신 이력서의 한줄소개가 비어있습니다."),
    SUBJECT_DUPLICATE(459, "이미 존재하는 과정명(회차)입니다."),
    CATEGORY_NOT_EQUALS(460, "해당하는 대분류가 없습니다."),
    MAIN_FAIL(461,"취업한 학생이 있습니다. 메인 등록 실패!");



    private final int httpStatus;
    private final String message;
}

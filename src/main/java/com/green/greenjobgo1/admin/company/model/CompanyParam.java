package com.green.greenjobgo1.admin.company.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CompanyParam {
    /** 아이디 **/
    private String id;
    /** 비밀번호 **/
    private String pw;
    /** 권한부여(열람일) 시작일 **/
    private LocalDate startedAt;
    /** 권한부여(열람일) 종료일" **/
    private LocalDate endedAt;
}

package com.green.greenjobgo1.admin.sign.model;

import lombok.Data;

@Data
public class AdminParam {
    /** "아이디" **/
    private String id;
    /** "비밀번호" **/
    private String pw;
    /** 부서명을 적어 주세요" **/
    private String name;
}

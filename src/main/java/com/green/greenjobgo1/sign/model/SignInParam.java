package com.green.greenjobgo1.sign.model;

//import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SignInParam {
//    @Schema(example = "test00@naver.com", description = "이메일")
    private String email;
//    @Schema(example = "9812120000", description = "비밀번호")
    private String pw;
}

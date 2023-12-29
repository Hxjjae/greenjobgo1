package com.green.greenjobgo1.admin.sign.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AdminParam {
    @Schema(example = "greendg100", description = "아이디")
    private String id;
    @Schema(example = "green1234", description = "비밀번호")
    private String pw;
}

package com.green.greenjobgo1.admin.sign.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AdminParam {
    @Schema(example = "greendg01", description = "아이디")
    private String id;
    @Schema(example = "green1234", description = "비밀번호")
    private String pw;
    @Schema(example = "취업관리실", description = "부서명을 적어 주세요")
    private String name;
}

package com.green.greenjobgo1.admin.company.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CompanyParam {
    @Schema(example = "greendg100", description = "아이디")
    private String id;
    @Schema(example = "green1234", description = "비밀번호")
    private String pw;
    @Schema(example = "2024-01-01", description = "권한부여(열람일) 시작일")
    private LocalDate startedAt;
    @Schema(example = "2024-09-09", description = "권한부여(열람일) 종료일")
    private LocalDate endedAt;
}

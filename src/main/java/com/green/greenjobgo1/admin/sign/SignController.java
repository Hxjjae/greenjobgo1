package com.green.greenjobgo1.admin.sign;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Tag(name = "sign")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/sign")
public class SignController {
    private final SignService service;

    @PostMapping(value = "/excel",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "엑셀로 회원가입",description = "리턴값이 1이면 회원가입완료")
    public ResponseEntity<Integer> addExcel(@RequestPart MultipartFile file){
        return ResponseEntity.ok(service.addExcel(file));
    }
}

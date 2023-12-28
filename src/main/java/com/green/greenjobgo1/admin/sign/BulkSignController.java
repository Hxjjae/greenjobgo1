package com.green.greenjobgo1.admin.sign;

import com.green.greenjobgo1.admin.sign.model.SignInParam;
import com.green.greenjobgo1.security.CommonRes;
import com.green.greenjobgo1.security.sign.model.SignInResultDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Tag(name = "sign")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/sign")
public class BulkSignController {
    private final BulkSignService service;

    @PostMapping(value = "/excel",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "엑셀로 회원가입",description = "리턴값이 1이면 회원가입완료")
    public ResponseEntity<Integer> addExcel(@RequestPart MultipartFile file){
        return ResponseEntity.ok(service.addExcel(file));
    }

    @PostMapping("/sign-in")
    @Operation(summary = "로그인", description = """
            "email": 이메일<br>
            "pw": 비밀번호
            """)
    public SignInResultDto signIn(HttpServletRequest req, @RequestBody SignInParam p) {
        String ip = req.getRemoteAddr();
        log.info("[signIn] 로그인을 시도하고 있습니다. email: {}, pw: {}, ip: {}", p.getEmail(), p.getPw(), ip);

        SignInResultDto dto = service.signIn(p, ip);
        if(dto.getCode() == CommonRes.SUCCESS.getCode()) {
            log.info("[signIn] 정상적으로 로그인 되었습니다. email: {}, token: {}", p.getEmail(), dto.getAccessToken());
        }

        return dto;
    }


}

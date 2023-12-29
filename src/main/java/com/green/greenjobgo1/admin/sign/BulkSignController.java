package com.green.greenjobgo1.admin.sign;

import com.green.greenjobgo1.admin.sign.model.AdminParam;
import com.green.greenjobgo1.admin.sign.model.AdminSigInParam;
import com.green.greenjobgo1.config.entity.AdminEntity;
import com.green.greenjobgo1.security.CommonRes;
import com.green.greenjobgo1.security.sign.model.SignInResultDto;
import com.green.greenjobgo1.student.sign.model.TokenDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
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
    @Operation(summary = "엑셀로 수강생을 회원가입합니다.",description = "리턴값이 1이면 회원가입완료")
    public ResponseEntity<Integer> addExcel(@RequestPart MultipartFile file){
        return ResponseEntity.ok(service.addExcel(file));
    }

    @PostMapping("/sign-in")
    @Operation(summary = "로그인", description = """
            "id": 아이디<br>
            "pw": 비밀번호
            """)
    public SignInResultDto signIn(HttpServletRequest req, @RequestBody AdminSigInParam p) {
        String ip = req.getRemoteAddr();
        log.info("[signIn] 로그인을 시도하고 있습니다. email: {}, pw: {}, ip: {}", p.getId(), p.getPw(), ip);

        SignInResultDto dto = service.signIn(p, ip);
        if (dto.getCode() == CommonRes.SUCCESS.getCode()) {
            log.info("[signIn] 정상적으로 로그인 되었습니다. email: {}, token: {}", p.getId(), dto.getAccessToken());
        }

        return dto;
    }

    @PostMapping("/sign-up")
    @Operation(summary = "회원가입", description = """
            스웨거에서 관리자를 임의로 생성하기 위한 메소드입니다.
            """)
    public AdminEntity signUp(@RequestBody AdminParam p) {
        return service.signUp(p);
    }

    @PostMapping("/refresh-token")
    @Operation(summary = "accessToken 재발행")
    public String refreshToken(HttpServletRequest req, @RequestBody TokenDto token) {
        return service.refreshToken(req, token.getRefreshToken());
    }
    @PostMapping("/logout")
    @Operation(summary = "로그아웃")
    public ResponseEntity<?> logout(HttpServletRequest req) {
        service.logout(req);
        ResponseCookie responseCookie = ResponseCookie.from("refresh-token", "")
                .maxAge(0)
                .path("/")
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .build();
    }
}

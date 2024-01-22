package com.green.greenjobgo1.sign;

import com.green.greenjobgo1.sign.model.SignInParam;
import com.green.greenjobgo1.common.security.CommonRes;
import com.green.greenjobgo1.common.security.sign.model.SignInResultDto;
import com.green.greenjobgo1.sign.model.TokenDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "수강생,기업 로그인,로그아웃")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sign")
public class SignController {
    private final SignService service;
    @PostMapping("/sign-in")
    @Operation(summary = "로그인", description = """
            "email(id)": 이메일(기업id: greendg100)<br>
            "pw": 비밀번호(기업pw green1234)<br>
            "editableYn: 편집가능유무 1이면 편집가능" <br>
            "portfolioYn: 포트폴리오가 있으면 1" <br>
            "aboutMeYn: 이력서가 있으면 1" <br>
            <예외처리> <br>
            이메일 오류: code:422, "code": "EMAIL_NULL","message": "존재하지 않는 이메일입니다.<br>"
            비밀번호 오류 :code:422, "code": "PASSWORD_FAILED","message": "비밀번호 불일치<br>"
            조회기간 만료 :code:422, "code": "PASSWORD_FAILED","message": "조회 만료<br>"
            """)
    public SignInResultDto signIn(HttpServletRequest req, @RequestBody SignInParam p) {
        String ip = req.getRemoteAddr();
        log.info("[signIn] 로그인을 시도하고 있습니다. email(id): {}, pw: {}, ip: {}", p.getEmail(), p.getPw(), ip);

        SignInResultDto dto = service.signIn(p, ip);
        if(dto.getCode() == CommonRes.SUCCESS.getCode()) {
            log.info("[signIn] 정상적으로 로그인 되었습니다. email(id): {}, token: {}", p.getEmail(), dto.getAccessToken());
        }

        return dto;
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

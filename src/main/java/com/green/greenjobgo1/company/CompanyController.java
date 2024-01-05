package com.green.greenjobgo1.company;

import com.green.greenjobgo1.company.model.CompanySignInParam;
import com.green.greenjobgo1.security.CommonRes;
import com.green.greenjobgo1.security.sign.model.SignInResultDto;
import com.green.greenjobgo1.student.sign.model.TokenDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "기업 로그인,로그아웃,조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService service;

    @PostMapping("/sign-in")
    @Operation(summary = "로그인", description = """
            "id": 아이디<br>
            "pw": 비밀번호
            """)
    public SignInResultDto signIn(HttpServletRequest req, @RequestBody CompanySignInParam p) {
        String ip = req.getRemoteAddr();
        log.info("[signIn] 로그인을 시도하고 있습니다. id: {}, pw: {}, ip: {}", p.getId(), p.getPw(), ip);

        SignInResultDto dto = service.signIn(p, ip);
        if(dto.getCode() == CommonRes.SUCCESS.getCode()) {
            log.info("[signIn] 정상적으로 로그인 되었습니다. id: {}, token: {}", p.getId(), dto.getAccessToken());
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
//    @GetMapping("/student")
//    @Operation(summary = "수강생을 검색합니다.")
//    public CompanyStudentVo getstudent(){
//        return null;
//    }
}

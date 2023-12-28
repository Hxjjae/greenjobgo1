package com.green.greenjobgo1.security.sign;

import com.green.greenjobgo1.security.CommonRes;
import com.green.greenjobgo1.security.config.exception.CommonErrorCode;
import com.green.greenjobgo1.security.sign.model.SignInResultDto;
import com.green.greenjobgo1.security.sign.model.SignUpResultDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/sign-api")
public class SignController {

    private final SignService SERVICE;

    //ApiParam은 문서 자동화를 위한 Swagger에서 쓰이는 어노테이션이고
    //RequestParam은 http 로부터 요청 온 정보를 받아오기 위한 스프링 어노테이션이다.

    @GetMapping("/test")
    public String test(@RequestParam int test) {
        if(test == 1) {
            throw new IllegalArgumentException(CommonErrorCode.INVALID_PARAMETER.getMessage());
        }
        log.info("date : {}", new Date());
        return "ddd";
    }


    @PostMapping("/sign-in")
    public SignInResultDto signIn(HttpServletRequest req, @RequestParam String id, @RequestParam String password)
            throws Exception {

        String ip = req.getRemoteAddr();
        log.info("[signIn] 로그인을 시도하고 있습니다. id: {}, pw: {}, ip: {}", id, password, ip);

        return SERVICE.signIn(id, password, ip);
    }

    @PostMapping("/sign-up")
    public SignUpResultDto signUp(@RequestParam String id
                                , @RequestParam String pw
                                , @RequestParam String nm
                                , @RequestParam String role) {
        log.info("[signUp] 회원가입을 수행합니다. id: {}, pw: {}, nm: {}, role: {}", id, pw, nm, role);
        SignUpResultDto dto = SERVICE.signUp(id, pw, nm, role);
        log.info("[signUp] 회원가입 완료 id: {}", id);
        return dto;
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<SignUpResultDto> refreshToken(HttpServletRequest req
            , @RequestParam String refreshToken) {

        SignUpResultDto dto = SERVICE.refreshToken(req, refreshToken);
        return dto == null ? ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null) : ResponseEntity.ok(dto);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest req) {
        SERVICE.logout(req);
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

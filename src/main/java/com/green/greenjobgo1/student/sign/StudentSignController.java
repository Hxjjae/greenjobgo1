package com.green.greenjobgo1.student.sign;

import com.green.greenjobgo1.student.sign.model.SignInParam;
import com.green.greenjobgo1.security.CommonRes;
import com.green.greenjobgo1.security.sign.model.SignInResultDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "수강생 로그인,로그아웃")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student/sign")
public class StudentSignController {
    private final StudentSignService service;
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

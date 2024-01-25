package com.green.greenjobgo1.admin.sign;

import com.green.greenjobgo1.admin.sign.model.AdminParam;
import com.green.greenjobgo1.admin.sign.model.AdminSigInParam;
import com.green.greenjobgo1.admin.sign.model.AdminSignInResultDto;
import com.green.greenjobgo1.common.entity.AdminEntity;
import com.green.greenjobgo1.common.security.CommonRes;
import com.green.greenjobgo1.common.security.sign.model.SignInResultDto;
import com.green.greenjobgo1.sign.model.TokenDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Tag(name = "관리자 로그인,회원가입 및 수강생 엑셀 회원가입")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/sign")
public class AdminSignController {
    private final AdminSignService service;

    @PostMapping(value = "/excel",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "엑셀로 수강생을 회원가입합니다."
            ,description = "리턴값이 1이면 회원가입완료<br>"+
            "과목테이블에 없는 과목이 엑셀파일에 있으면 에러코드: 437.<br>"+
            "취업관리실 직원이 Employee테이블에 없다면 에러코드: 438"
    )
    public ResponseEntity<Integer> addExcel(@RequestPart MultipartFile studentfile){
        return ResponseEntity.ok(service.addExcel(studentfile));
    }
    @GetMapping("/student-download")
    @Operation(summary = "수강생 엑셀파일 다운로드")
    public void downloadMock(HttpServletResponse res) throws IOException {
        service.downloadstd(res);
    }

    @PostMapping("/sign-in")
    @Operation(summary = "로그인", description = """
            "id": 아이디<br>
            "pw": 비밀번호<br>
            <예외처리> <br>
            아이디 오류: code:433, <br>
            비밀번호 오류 :code:434<br>
            """)
    public AdminSignInResultDto signIn(HttpServletRequest req, @RequestBody AdminSigInParam p) {
        String ip = req.getRemoteAddr();
        log.info("[signIn] 로그인을 시도하고 있습니다. email: {}, pw: {}, ip: {}", p.getId(), p.getPw(), ip);

        AdminSignInResultDto dto = service.signIn(p, ip);
        if (dto.getCode() == CommonRes.SUCCESS.getCode()) {
            log.info("[signIn] 정상적으로 로그인 되었습니다. email: {}, token: {}", p.getId(), dto.getAccessToken());
        }

        return dto;
    }

    @PostMapping("/sign-up")
    @Operation(summary = "회원가입", description = """
            스웨거에서 관리자를 임의로 생성하기 위한 메소드입니다.<br>
            <예외처리><br>
            아이디 중복 오류: code 439
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

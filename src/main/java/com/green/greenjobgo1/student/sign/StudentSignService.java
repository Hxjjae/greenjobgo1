package com.green.greenjobgo1.student.sign;

import com.green.greenjobgo1.security.config.security.AuthenticationFacade;
import com.green.greenjobgo1.security.config.security.model.MyUserDetails;
import com.green.greenjobgo1.student.sign.model.SignInParam;
import com.green.greenjobgo1.common.utils.ResultUtils;
import com.green.greenjobgo1.config.entity.StudentEntity;
import com.green.greenjobgo1.repository.StudentRepository;
import com.green.greenjobgo1.security.config.RedisService;
import com.green.greenjobgo1.security.config.security.JwtTokenProvider;
import com.green.greenjobgo1.security.sign.model.SignInResultDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentSignService {
    private final PasswordEncoder PW_ENCODER;
    private final JwtTokenProvider JWT_PROVIDER;
    private final StudentRepository studentRepository;
    private final AuthenticationFacade facade;
    private final RedisService redisService;
    private final JPAQueryFactory jpaQueryFactory;
    public SignInResultDto signIn(SignInParam p, String ip) {
        log.info("[getSignInResult] signDataHandler로 회원 정보 요청");
        StudentEntity user = studentRepository.findById(p.getEmail());

        if (user == null) {
            throw new RuntimeException("존재하지 않는 이메일");
        }
        if(!PW_ENCODER.matches(p.getPw(), user.getPw())) {
            throw new RuntimeException("비밀번호 불일치");
        }

        String redisKey = String.format("c:RT(%s):%s:%s", "Server", user.getIstudent(), ip);

        if (redisService.getValues(redisKey)!=null){
            redisService.deleteValues(redisKey);
        }

        log.info("[getSignInResult] access_token 객체 생성");
        String accessToken = JWT_PROVIDER.generateJwtToken(String.valueOf(user.getIstudent()),
                Collections.singletonList(user.getRole()),
                JWT_PROVIDER.ACCESS_TOKEN_VALID_MS, JWT_PROVIDER.ACCESS_KEY);
        String refreshToken = JWT_PROVIDER.generateJwtToken(String.valueOf(user.getIstudent()),
                Collections.singletonList(user.getRole()),
                JWT_PROVIDER.REFRESH_TOKEN_VALID_MS, JWT_PROVIDER.REFRESH_KEY);
        Long accessTokenTime = JWT_PROVIDER.ACCESS_TOKEN_VALID_MS;

        redisService.setValues(redisKey, refreshToken);

        log.info("[getSignInResult] SignInResultDto 객체 생성");
        SignInResultDto dto = SignInResultDto.builder()
                .accessToken(accessToken)
                .accessTokenTime(accessTokenTime)
                .refreshToken(refreshToken)
                .role(user.getRole())
                .build();

        log.info("[getSignInResult] SignInResultDto 객체 값 주입");
        ResultUtils.setSuccessResult(dto);
        return dto;
    }

    public String refreshToken(HttpServletRequest req, String refreshToken) throws RuntimeException {
        String error = "유효하지 않은 토큰";
        if(!(JWT_PROVIDER.isValidateToken(refreshToken, JWT_PROVIDER.REFRESH_KEY))) { return error; }

        Claims claims = null;
        try {
            claims = JWT_PROVIDER.getClaims(refreshToken, JWT_PROVIDER.REFRESH_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (claims == null) {
            return error;
        }

        String strIuser = claims.getSubject();
        Long iuser = Long.valueOf(strIuser);
        String ip = req.getRemoteAddr();
        List<String> roles = (List<String>)claims.get("roles");

        String redisKey = "ROLE_ADMIN".equals(roles.get(0)) ?
                String.format("c:RT(%s):ADMIN:%s:%s", "Server", iuser, ip) :
                String.format("c:RT(%s):%s:%s", "Server", iuser, ip);

        String redisRt = redisService.getValues(redisKey);
        if (redisRt == null) {
            return error;
        }

        try {
            if (!redisRt.equals(refreshToken)) {
                return error;
            }

            return JWT_PROVIDER.generateJwtToken(strIuser, roles,
                    JWT_PROVIDER.ACCESS_TOKEN_VALID_MS, JWT_PROVIDER.ACCESS_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return error;
    }

    public void logout(HttpServletRequest req) {
        String accessToken = JWT_PROVIDER.resolveToken(req, JWT_PROVIDER.TOKEN_TYPE);
        Long iuser = facade.getLoginUserPk();
        String ip = req.getRemoteAddr();
        MyUserDetails userDetails = facade.getLoginUser();

        String redisKey = "ROLE_ADMIN".equals(userDetails.getRoles().get(0)) ?
                String.format("c:RT(%s):ADMIN:%s:%s", "Server", iuser, ip) :
                String.format("c:RT(%s):%s:%s", "Server", iuser, ip);

        String refreshTokenInRedis = redisService.getValues(redisKey);
        if (refreshTokenInRedis != null) {
            redisService.deleteValues(redisKey);
        }

        long expiration = JWT_PROVIDER.getTokenExpirationTime(accessToken, JWT_PROVIDER.ACCESS_KEY) -
                LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        log.info("expiration: {}", expiration);
        log.info("localDateTime-getTime(): {}", LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

        redisService.setValuesWithTimeout(accessToken, "logout", expiration);
    }
}

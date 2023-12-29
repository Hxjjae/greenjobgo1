package com.green.greenjobgo1.student.sign;

import com.green.greenjobgo1.student.sign.model.SignInParam;
import com.green.greenjobgo1.common.utils.ResultUtils;
import com.green.greenjobgo1.config.entity.StudentEntity;
import com.green.greenjobgo1.repository.StudentRepository;
import com.green.greenjobgo1.security.config.RedisService;
import com.green.greenjobgo1.security.config.security.JwtTokenProvider;
import com.green.greenjobgo1.security.sign.model.SignInResultDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
@Slf4j
@Service
@RequiredArgsConstructor
public class StudentSignService {
    private final PasswordEncoder PW_ENCODER;
    private final JwtTokenProvider JWT_PROVIDER;
    private final StudentRepository studentRepository;
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

        redisService.setValues(redisKey, refreshToken);

        log.info("[getSignInResult] SignInResultDto 객체 생성");
        SignInResultDto dto = SignInResultDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .role(user.getRole())
                .build();

        log.info("[getSignInResult] SignInResultDto 객체 값 주입");
        ResultUtils.setSuccessResult(dto);
        return dto;
    }
}

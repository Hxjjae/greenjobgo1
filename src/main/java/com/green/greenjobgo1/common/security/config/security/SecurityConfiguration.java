package com.green.greenjobgo1.common.security.config.security;

import com.green.greenjobgo1.common.security.config.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


//spring security 5.7.0부터 WebSecurityConfigurerAdapter deprecated 됨
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;
    private final MyUserDetailsServiceImpl SERVICE_IMPL;

    //webSecurityCustomizer를 제외한 모든 것, 시큐리티를 거친다. 보안과 연관
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authz ->
                            authz.requestMatchers(
                                             "/swagger-ui.html"
                                            , "/swagger-ui/**"
                                            , "/v3/api-docs/**"
                                            , "webjars/**"
                                            , "swagger-resources/**"
                                            , "/"
                                            , "/index.html"
                                            , "/static/**"
                                            , "/api/admin/sign/sign-in", "/api/admin/sign/refresh-token", "/api/admin/sign/sign-up"
                                            , "/api/admin-student/**"
                                            , "/pic/**", "/img/**"
                                            ,"/api/sign/**"
                                            ,"/sign-api/sign-in"
                                            , "/sign-api/sign-up"
                                            , "/sign-api/exception"
                                            , "/sign-api/otp"
                                            , "/sign-api/test"
                                            , "/sign-api/otp-valid"
                                            , "/view/**"
                                            , "/error"
                                            , "/err"
                                    ).permitAll()
                                    .requestMatchers(HttpMethod.GET, "/sign-api/refresh-token").permitAll()
                                    .requestMatchers(HttpMethod.GET, "/api/admin/**").hasAnyRole( "ADMIN")
                                    .requestMatchers(HttpMethod.PUT, "/api/admin/**").hasAnyRole("ADMIN")
                                    .requestMatchers(HttpMethod.PATCH, "/api/admin/**").hasAnyRole("ADMIN")
                                    .requestMatchers("/api/student/file").hasAnyRole("USER")
                                    .requestMatchers("/api/student/certificate").hasAnyRole("USER")
                                    .requestMatchers("/api/company/**").hasAnyRole("COMPANY")
                                    .requestMatchers("/api/student/**").hasAnyRole("USER")
                                    .requestMatchers("**exception**").permitAll()
                                    .requestMatchers("/todo-api").hasAnyRole("USER", "COMPANY", "ADMIN")
                                    .anyRequest().hasRole("ADMIN")

                ) //사용 권한 체크
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //세션 사용 X
        .httpBasic(http -> http.disable()) //UI 있는 시큐리티 설정을 비활성화
                .csrf(csrf -> csrf.disable()) //CSRF 보안이 필요 X, 쿠키와 세션을 이용해서 인증을 하고 있기 때문에 발생하는 일, https://kchanguk.tistory.com/197
                .exceptionHandling(except -> {
                    except.accessDeniedHandler(new CustomAccessDeniedHandler());
                    except.authenticationEntryPoint(new CustomAuthenticationEntryPoint());
                })
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, redisService), UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(SERVICE_IMPL);


        return httpSecurity.build();
    }

    //시큐리티를 거치지 않는다. 보안과 전혀 상관없는 페이지 및 리소스

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        //함수형 인터페이스 람다
//        WebSecurityCustomizer lamda = (web) -> web.ignoring()
//                    .requestMatchers(HttpMethod.GET, "/sign-api/refresh-token");
//        return lamda;
//    }
}

package com.green.greenjobgo1.common.security.config.security;

import com.green.greenjobgo1.repository.AdminRepository;
import com.green.greenjobgo1.common.security.config.security.model.MyUserDetails;
import com.green.greenjobgo1.common.entity.AdminEntity;
import com.green.greenjobgo1.common.entity.CompanyEntity;
import com.green.greenjobgo1.common.entity.StudentEntity;
import com.green.greenjobgo1.repository.CompanyRepository;
import com.green.greenjobgo1.repository.StudentRepository;
import com.green.greenjobgo1.common.security.config.RedisService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class JwtTokenProvider {
    public final Key ACCESS_KEY;
    public final Key REFRESH_KEY;
    public final String TOKEN_TYPE;

    public final long ADMIN_ACCESS_TOKEN_VALID_MS = 10_800_000L; // 1000L * 60 * 60 -> 1시간 3_600_000 // 10_800_000L
    public final long ACCESS_TOKEN_VALID_MS = 3_600_000L; // 1000L * 60 * 3 // 180초 180_000L
    public final long REFRESH_TOKEN_VALID_MS = 1_296_000_000L; // 1000L * 60 * 60 * 24 * 15 -> 15일

    private final AdminRepository adminRep;
    private final StudentRepository studentRep;
    private final CompanyRepository companyRep;
    private final RedisService redisService;

    @Autowired
    public JwtTokenProvider(@Value("${springboot.jwt.access-secret}") String accessSecretKey
                            , @Value("${springboot.jwt.refresh-secret}") String refreshSecretKey
                            , @Value("${springboot.jwt.token-type}") String tokenType
                            , AdminRepository adminRep, StudentRepository studentRep, CompanyRepository companyRep, RedisService redisService) {
        this.adminRep = adminRep;
        this.studentRep = studentRep;
        this.companyRep = companyRep;
        byte[] accessKeyBytes = Decoders.BASE64.decode(accessSecretKey);
        this.ACCESS_KEY = Keys.hmacShaKeyFor(accessKeyBytes);

        byte[] refreshKeyBytes = Decoders.BASE64.decode(refreshSecretKey);
        this.REFRESH_KEY = Keys.hmacShaKeyFor(refreshKeyBytes);
        this.TOKEN_TYPE = tokenType;
        this.redisService = redisService;
    }



    public String generateJwtToken(String strIuser, List<String> roles, long token_valid_ms, Key key) {
        log.info("JwtTokenProvider - generateJwtToken: 토큰 생성 시작");
        Date now = new Date();

        String token = Jwts.builder()
                .setClaims(createClaims(strIuser, roles))
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + token_valid_ms))
                .signWith(key)
                .compact();
        log.info("JwtTokenProvider - generateJwtToken: 토큰 생성 완료");
        return token;
    }

    private Claims createClaims(String strIuser, List<String> roles) {
        Claims claims = Jwts.claims();
        claims.setSubject(strIuser);
        claims.put("roles", roles);
        return claims;

        //return (Claims)Jwts.claims().setSubject(strIuser).put("roles", roles);
    }

    public Authentication getAuthentication(String token) {
        log.info("JwtTokenProvider - getAuthentication: 토큰 인증 정보 조회 시작");
        //UserDetails userDetails = SERVICE.loadUserByUsername(getUsername(token));
        UserDetails userDetails = getUserDetailsFromToken(token, ACCESS_KEY);
        log.info("JwtTokenProvider - getAuthentication: 토큰 인증 정보 조회 완료, UserDetails UserName : {}"
                , userDetails.getUsername());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private UserDetails getUserDetailsFromToken(String token, Key key) {
        Claims claims = getClaims(token, key);
        String strIuser = claims.getSubject();
        Long id = Long.valueOf(strIuser);
        List<String> roles = (List<String>)claims.get("roles");

        log.info(roles.get(0));
        if ("ROLE_ADMIN".equals(roles.get(0))) {
            AdminEntity admin = adminRep.findById(id).get();
            return MyUserDetails.builder()
                    .iuser(admin.getIadmin())
                    .uid(admin.getId())
                    .upw(admin.getPw())
                    .name(admin.getId())
                    .roles(roles)
                    .build();
        }else if (("ROLE_USER".equals(roles.get(0)))){
            StudentEntity student = studentRep.findById(id).get();
            return MyUserDetails.builder()
                    .iuser(student.getIstudent())
                    .uid(student.getId())
                    .upw(student.getPw())
                    .name(student.getName())
                    .roles(roles)
                    .build();
        }


            CompanyEntity company = companyRep.findById(id).get();
            return MyUserDetails.builder()
                    .iuser(company.getIcompany())
                    .uid(company.getId())
                    .upw(company.getPassword())
                    .name(company.getId())
                    .roles(roles)
                    .build();


    }

    public String resolveToken(HttpServletRequest req, String type) {
        log.info("JwtTokenProvider - resolveToken: HTTP 헤더에서 Token 값 추출");
//        for(Enumeration names = req.getHeaderNames(); names.hasMoreElements();) {
//            String name = (String)names.nextElement();
//            for(Enumeration values = req.getHeaders(name); values.hasMoreElements();) {
//                String value = (String)values.nextElement();
//                log.info("{}: {}", name, value);
//            }
//        }
        String headerAuth = req.getHeader("authorization");
        //return headerAuth == null  ? null : headerAuth.substring(type.length()).trim();
        return headerAuth != null && headerAuth.startsWith(String.format("%s ", type)) ? headerAuth.substring(type.length()).trim() : null;
    }

    public Claims getClaims(String token, Key key) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /*
    public boolean validateRefreshToken(String refreshToken){
        try {
            if (redisService.getValues(refreshToken).equals("delete")) { // 회원 탈퇴했을 경우
                return false;
            }
            getClaims(refreshToken, REFRESH_KEY);
            return true;
        }  catch (MalformedJwtException e) {
            log.error("Invalid JWT token.");
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token.");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty.");
        } catch (NullPointerException e){
            log.error("JWT Token is empty.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }


    public boolean validateAccessToken(String accessToken) {
        try {
            String r = redisService.getValues(accessToken);
            if (r != null && r.equals("logout")) { // 로그아웃 했을 경우
                return false;
            }
            getClaims(accessToken, ACCESS_KEY);
            return true;
        } catch(ExpiredJwtException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }
*/
    public long getTokenExpirationTime(String token, Key key) {
        try {
            return getClaims(token, key).getExpiration().getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }


    // Filter에서 사용
    public boolean isValidateToken(String token, Key key) {
        log.info("JwtTokenProvider - isValidateToken: 토큰 유효 체크 시작");
        try {
            return !getClaims(token, key).getExpiration().before(new Date());
        } catch (Exception e) {
            log.info("JwtTokenProvider - isValidateToken: 토큰 유효 체크 예외 발생");
            return false;
        }
        // 지났으면 true > false;
        // 안 지났으면 false > true;
    }
}

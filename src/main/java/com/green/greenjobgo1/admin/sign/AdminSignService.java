package com.green.greenjobgo1.admin.sign;

import com.green.greenjobgo1.admin.AdminRepository;
import com.green.greenjobgo1.admin.sign.model.AdminParam;
import com.green.greenjobgo1.admin.sign.model.AdminSigInParam;
import com.green.greenjobgo1.admin.sign.model.StudentExcel;
import com.green.greenjobgo1.common.utils.ExcelUtil;
import com.green.greenjobgo1.common.utils.ResultUtils;
import com.green.greenjobgo1.config.entity.AdminEntity;
import com.green.greenjobgo1.config.entity.StudentEntity;
import com.green.greenjobgo1.repository.StudentRepository;
import com.green.greenjobgo1.security.config.RedisService;
import com.green.greenjobgo1.security.config.security.AuthenticationFacade;
import com.green.greenjobgo1.security.config.security.JwtTokenProvider;
import com.green.greenjobgo1.security.config.security.model.MyUserDetails;
import com.green.greenjobgo1.security.sign.model.SignInResultDto;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminSignService {
    private final StudentRepository stdRep;
    private final PasswordEncoder PW_ENCODER;
    private final ExcelUtil excelUtil;
    private final AdminRepository AdminRep;
    private final RedisService redisService;
    private final JwtTokenProvider JWT_PROVIDER;
    private final AuthenticationFacade facade;

    @Transactional
    public int addExcel(MultipartFile file) {


        List<StudentExcel> studentlist = new ArrayList<>();

        // 엑셀의 셀데이터를 객체에 담기 4번째 행부터 5번째 열까지
        List<Map<String, Object>> listMap = excelUtil.getListData(file, 3, 15);

        for (Map<String, Object> map : listMap) {
            StudentExcel student = new StudentExcel();

            // 각 셀의 데이터를 VO에 set한다.
            student.setSubjectName(map.get("0").toString());
            student.setNumber(map.get("1").toString());
            student.setStartedAt(map.get("2").toString());
            student.setEndedAt(map.get("3").toString());
            student.setTrainingTime(map.get("4").toString());
            student.setStudentName(map.get("5").toString());
            student.setBirthday(map.get("6").toString());
            student.setPhone(map.get("7").toString());
            student.setEmail(map.get("8").toString());
            student.setAddress(map.get("9").toString());
            student.setGender(map.get("11").toString());
            student.setAge(map.get("12").toString());
            student.setEducation(map.get("13").toString());

            studentlist.add(student);
        }

        //DB에 저장
        for (StudentExcel user : studentlist) {
            StudentEntity student = new StudentEntity();
            String birthday = user.getBirthday();
            String birthdayfirst = "19"+birthday.substring(0,2);
            String birthdaysecond = "-"+birthday.substring(2,4);
            String birthdaythird = "-"+birthday.substring(4,6);
            LocalDate birth= LocalDate.parse((birthdayfirst + birthdaysecond + birthdaythird));

            String pwfirst = birthday.substring(0, 6);
            String phone = user.getPhone();
            String pwsecond = phone.substring(9, 13);

            log.info("비밀번호:{}",pwfirst+pwsecond);
            if (user.getGender().equals("남")){
                student.setGender(1);
            }else {
                student.setGender(0);
            }
            student.setId(user.getEmail());
            student.setPw(PW_ENCODER.encode(pwfirst+pwsecond));
            student.setMobileNumber(user.getPhone());
            student.setName(user.getStudentName());
            student.setBirthday(birth);
            student.setAddress(user.getAddress());
            student.setEducation(user.getEducation());
            student.setAge(Integer.parseInt(user.getAge()));
            //student.setRole("ROLE_ADMIN");
            student.setRole("ROLE_USER");
            student.setHuntJobYn(0);
            student.setStorageYn(0);
            student.setEditableYn(0);
            StudentEntity studententity = stdRep.findById(user.getEmail());
            if (studententity == null){
                StudentEntity save = stdRep.save(student);

                if (save.getId() == null){
                    return 0;
                }
            }

        }
        return 1;
    }

    public AdminEntity signUp(AdminParam p) {
        final String ADMIN = "ROLE_ADMIN";
        AdminEntity adminEntity = AdminRep.findById(p.getId());

        if (adminEntity == null) {
            return AdminRep.save(AdminEntity.builder()
                    .id(p.getId())
                    .pw(PW_ENCODER.encode(p.getPw()))
                    .role(ADMIN).build());
        }
        return null;
    }
    public SignInResultDto signIn(AdminSigInParam p, String ip) {
        final String ADMIN = "ROLE_ADMIN";
        AdminEntity adminEntity = AdminRep.findById(p.getId());

        if (adminEntity == null) {
            throw new RuntimeException("존재하지 않는 이메일");
        }
        if (!PW_ENCODER.matches(p.getPw(), adminEntity.getPw())) {
            throw new RuntimeException("비밀번호 불일치");
        }

        String redisKey = String.format("c:RT(%s):ADMIN:%s:%s", "Server", adminEntity.getIadmin(), ip);
        if (redisService.getValues(redisKey) != null) {
            redisService.deleteValues(redisKey);
        }

        String accessToken = JWT_PROVIDER.generateJwtToken(String.valueOf(adminEntity.getIadmin()),
                Collections.singletonList(ADMIN), JWT_PROVIDER.ACCESS_TOKEN_VALID_MS, JWT_PROVIDER.ACCESS_KEY);
        String refreshToken = JWT_PROVIDER.generateJwtToken(String.valueOf(adminEntity.getIadmin()),
                Collections.singletonList(ADMIN), JWT_PROVIDER.REFRESH_TOKEN_VALID_MS, JWT_PROVIDER.REFRESH_KEY);
        Long accessTokenTime = JWT_PROVIDER.ACCESS_TOKEN_VALID_MS;
        redisService.setValues(redisKey, refreshToken);

        SignInResultDto dto = SignInResultDto.builder()
                .accessToken(accessToken)
                .accessTokenTime(accessTokenTime)
                .refreshToken(refreshToken)
                .role(ADMIN)
                .build();

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

        String redisKey;
        if ("ROLE_ADMIN".equals(roles.get(0))){
            redisKey = String.format("c:RT(%s):ADMIN:%s:%s", "Server", iuser, ip);
        }
        if ("ROLE_USER".equals(roles.get(0))){
            redisKey = String.format("c:RT(%s):USER:%s:%s", "Server", iuser, ip);
        }else {
            redisKey = String.format("c:RT(%s):COMPANY:%s:%s", "Server", iuser, ip);
        }

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

        String redisKey;
        if ("ROLE_ADMIN".equals(userDetails.getRoles().get(0))){
            redisKey = String.format("c:RT(%s):ADMIN:%s:%s", "Server", iuser, ip);
        }else if ("ROLE_USER".equals(userDetails.getRoles().get(0))){
            redisKey = String.format("c:RT(%s):USER:%s:%s", "Server", iuser, ip);
        }else {
            redisKey = String.format("c:RT(%s):COMPANY:%s:%s", "Server", iuser, ip);
        }

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

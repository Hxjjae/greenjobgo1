package com.green.greenjobgo1.admin.sign;

import com.green.greenjobgo1.admin.sign.model.SignInParam;
import com.green.greenjobgo1.admin.sign.model.StudentExcel;
import com.green.greenjobgo1.common.utils.ExcelUtil;
import com.green.greenjobgo1.common.utils.ResultUtils;
import com.green.greenjobgo1.config.entity.StudentEntity;
import com.green.greenjobgo1.repository.StudentRepository;
import com.green.greenjobgo1.security.config.RedisService;
import com.green.greenjobgo1.security.config.security.JwtTokenProvider;
import com.green.greenjobgo1.security.config.security.model.UserEntity;
import com.green.greenjobgo1.security.sign.model.SignInResultDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class BulkSignService {
    private final StudentRepository stdRep;
    private final PasswordEncoder PW_ENCODER;
    private final JwtTokenProvider JWT_PROVIDER;
    private final StudentRepository studentRepository;
    private final RedisService redisService;
    private final JPAQueryFactory jpaQueryFactory;
    private final ExcelUtil excelUtil;

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
            student.setRole("ADMIN");
            student.setHuntJobYn(0);
            student.setStorageYn(0);
            student.setEditableYn(0);

            StudentEntity save = stdRep.save(student);

            if (save.getId() == null){
                return 0;
            }
        }
        return 1;
    }

    public SignInResultDto signIn(SignInParam p, String ip) {
        log.info("[getSignInResult] signDataHandler로 회원 정보 요청");
        StudentEntity user = studentRepository.findById(p.getEmail());

        if (user == null) {
            throw new RuntimeException("존재하지 않는 이메일");
        }
        if(!PW_ENCODER.matches(p.getPw(), user.getPw())) {
            throw new RuntimeException("비밀번호 불일치");
        }

        String redisKey = String.format("c:RT(%s):%s:%s", "Server", user.getId(), ip);

        if (redisService.getValues(redisKey)!=null){
            redisService.deleteValues(redisKey);
        }

        log.info("[getSignInResult] access_token 객체 생성");
        String accessToken = JWT_PROVIDER.generateJwtToken(String.valueOf(user.getId()),
                Collections.singletonList(user.getRole()),
                JWT_PROVIDER.ACCESS_TOKEN_VALID_MS, JWT_PROVIDER.ACCESS_KEY);
        String refreshToken = JWT_PROVIDER.generateJwtToken(String.valueOf(user.getId()),
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

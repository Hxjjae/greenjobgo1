package com.green.greenjobgo1.admin.sign;

import com.green.greenjobgo1.admin.AdminRepository;
import com.green.greenjobgo1.admin.sign.model.AdminParam;
import com.green.greenjobgo1.admin.sign.model.AdminSigInParam;
import com.green.greenjobgo1.admin.sign.model.StudentExcel;
import com.green.greenjobgo1.common.utils.ExcelUtil;
import com.green.greenjobgo1.common.utils.ResultUtils;
import com.green.greenjobgo1.config.entity.*;
import com.green.greenjobgo1.repository.AdminSubjectRepository;
import com.green.greenjobgo1.repository.StudentCourseSubjectRepository;
import com.green.greenjobgo1.repository.StudentRepository;
import com.green.greenjobgo1.security.config.RedisService;
import com.green.greenjobgo1.security.config.security.AuthenticationFacade;
import com.green.greenjobgo1.security.config.security.JwtTokenProvider;
import com.green.greenjobgo1.security.config.security.model.MyUserDetails;
import com.green.greenjobgo1.security.sign.model.SignInResultDto;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminSignService {
    private final StudentRepository stdRep;
    private final AdminSubjectRepository subjectRep;
    private final StudentCourseSubjectRepository studentSubjectRep;
    private final PasswordEncoder PW_ENCODER;
    private final ExcelUtil excelUtil;
    private final AdminRepository AdminRep;
    private final RedisService redisService;
    private final JwtTokenProvider JWT_PROVIDER;
    private final AuthenticationFacade facade;

    @Transactional
    public int addExcel(MultipartFile studentfile) {
        List<StudentExcel> studentlist = new ArrayList<>();

        // 엑셀의 셀데이터를 객체에 담기 4번째 행부터 5번째 열까지
        List<Map<String, Object>> listMap = excelUtil.getListData(studentfile, 3, 15);

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

            int start = Integer.parseInt(user.getStartedAt());
            int end = Integer.parseInt(user.getEndedAt());

            LocalDate startedAt = LocalDate.of(1900, 1, 1).plusDays(start - 2);
            LocalDate endedAt = LocalDate.of(1900, 1, 1).plusDays(end - 2);

            log.info("startedAt: {}",startedAt);
            log.info("endedAt: {}",endedAt);


            student.setGender(user.getGender().equals("남") ? 1 : 0);
            student.setId(user.getEmail());
            student.setPw(PW_ENCODER.encode(pwfirst+pwsecond));
            student.setMobileNumber(user.getPhone());
            student.setName(user.getStudentName());
            student.setBirthday(birth);
            student.setAddress(user.getAddress());
            student.setEducation(user.getEducation());
            student.setAge(Integer.parseInt(user.getAge()));
            student.setRole("ROLE_USER");
//            student.setStartedAt(startedAt);
//            student.setEndedAt(endedAt);
            student.setHuntJobYn(0);
            student.setStorageYn(0);
            student.setEditableYn(0);


            StudentEntity studententity = stdRep.findById(user.getEmail());
            if (studententity == null){
                StudentEntity save = stdRep.save(student);
                //StudentEntity studentEntity = stdRep.findById(save.getId());

                log.info("ID:{}",save.getIstudent());
                //학생이 소속된 과목table 정보 가져오기
                CourseSubjectEntity subjectentity = subjectRep.findBySubjectName(user.getSubjectName());

                if (subjectentity == null) {
                    throw new RuntimeException("존재하지 않는 과목입니다");
                }
                StudentCourseSubjectEntity entity = StudentCourseSubjectEntity.builder()
                        .studentEntity(save)
                        .courseSubjectEntity(subjectentity).build();

                studentSubjectRep.save(entity);

                if (save.getId() == null){
                    return 0;
                }
            }

        }
        return 1;
    }

    public void downloadstd(HttpServletResponse response) throws IOException {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("수강생다운로드");
        //Row row = null;
        Cell cell = null;

        // Header 넣기
        int cellNum = 0;
        Row header1 = sheet.createRow(0); // 첫번째줄 헤더
        Row header2 = sheet.createRow(1);
        Row header3 = sheet.createRow(2);
        String[] headerName = new String [] {"과정명","회차","훈련 시작일","훈련 종료일","훈련시간","이름","생년월일(7자리)","핸드폰","이메일","거주지(구까지)","캠퍼스별 담당 실장","성별","나이","학력","최종이력서","포트폴리오"};

        //첫 번째 행에서 첫 번째부터 세 번째 열까지 병합
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
        IntStream.rangeClosed(0, 4).forEach(i -> header1.createCell(i).setCellValue("과정 현황"));
        sheet.addMergedRegion(new CellRangeAddress(1,1,0,4));
        IntStream.rangeClosed(1, 4).forEach(i -> header2.createCell(i).setCellValue(""));

        sheet.addMergedRegion(new CellRangeAddress(0,0,5,9));
        IntStream.rangeClosed(5, 9).forEach(i -> header1.createCell(i).setCellValue("훈련생 현황"));
        sheet.addMergedRegion(new CellRangeAddress(1,1,5,9));
        IntStream.rangeClosed(5, 9).forEach(i -> header2.createCell(i).setCellValue("훈련생 정보"));

        sheet.addMergedRegion(new CellRangeAddress(0,0,10,15));
        IntStream.rangeClosed(10, 15).forEach(i -> header1.createCell(i).setCellValue("훈련생 희망 직무 현황"));
        sheet.addMergedRegion(new CellRangeAddress(1,1,11,15));
        IntStream.rangeClosed(11, 15).forEach(i -> header2.createCell(i).setCellValue("내담자특성상황"));

        for (int i = 0; i <headerName.length; i++) {
            header3.createCell(i).setCellValue(headerName[i]);
        }

        //헤더 색넣기
        CellStyle oliveGreen = wb.createCellStyle();
        oliveGreen.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
        oliveGreen.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle lightGreen = wb.createCellStyle();
        lightGreen.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        lightGreen.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle paleBlue = wb.createCellStyle();
        paleBlue.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
        paleBlue.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // 병합된 영역의 각 셀에 스타일 적용
        IntStream.rangeClosed(0, 4).mapToObj(col -> header1.getCell(col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK))
                .forEach(mergedCell -> mergedCell.setCellStyle(oliveGreen));

        IntStream.rangeClosed(5, 9).mapToObj(col -> header1.getCell(col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK))
                .forEach(mergedCell -> mergedCell.setCellStyle(lightGreen));

        IntStream.rangeClosed(10, 15).mapToObj(col -> header1.getCell(col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK))
                .forEach(mergedCell -> mergedCell.setCellStyle(paleBlue));

        List<StudentEntity> studentlist = stdRep.findAll();

         //Body
//        for (int i = 0; i <studentlist.size(); i++) {
//            //과정명 찾기
//            List<StudentCourseSubjectEntity> scsList = studentlist.get(0).getScsList();
//
//            Long isubject = null;
//            for (int z = 0; z < scsList.size(); z++) {
//                isubject = scsList.get(z).getCourseSubjectEntity().getIcourseSubject();
//                log.info("subjectName: {}",isubject);
//            }
//            CourseSubjectEntity subjectEntity = subjectRep.findById(isubject).get();
//            int rowNum = 3;
//            cellNum = 0;
//            row = sheet.createRow(rowNum++);
//            row.createCell(0).setCellValue(subjectEntity.getSubjectName());
//            row.createCell(1).setCellValue("2");// 과정 회차
//            row.createCell(2).setCellValue(subjectEntity.getStartedAt().toString());
//            row.createCell(3).setCellValue(subjectEntity.getEndedAt().toString());
//            row.createCell(4).setCellValue(subjectEntity.getEndedAt().toString());
//            row.createCell(5).setCellValue(subjectEntity.getEndedAt().toString());
//            row.createCell(6).setCellValue(subjectEntity.getEndedAt().toString());
//            row.createCell(7).setCellValue(subjectEntity.getEndedAt().toString());
//
//        }
        int rowNum = 4;
        for (StudentEntity student : studentlist) {
            List<StudentCourseSubjectEntity> scsList = student.getScsList();

            if (!scsList.isEmpty()) {
                CourseSubjectEntity subjectEntity = subjectRep.findById(scsList.get(0).getCourseSubjectEntity().getIcourseSubject()).orElse(null);
                if (subjectEntity != null) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(subjectEntity.getSubjectName());
                    row.createCell(1).setCellValue("2"); // 과정 회차
                    row.createCell(2).setCellValue(subjectEntity.getStartedAt().toString());
                    row.createCell(3).setCellValue(subjectEntity.getEndedAt().toString());

                }

            }
        }

        // Download
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=student.xlsx");
        try {
            wb.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            wb.close();
        }
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
        }else if ("ROLE_USER".equals(roles.get(0))){
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

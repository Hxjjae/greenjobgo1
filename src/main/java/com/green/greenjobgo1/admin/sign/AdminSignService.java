package com.green.greenjobgo1.admin.sign;

import ch.qos.logback.core.spi.ErrorCodes;
import com.green.greenjobgo1.admin.AdminRepository;
import com.green.greenjobgo1.admin.sign.model.AdminParam;
import com.green.greenjobgo1.admin.sign.model.AdminSigInParam;
import com.green.greenjobgo1.admin.sign.model.AdminSignInResultDto;
import com.green.greenjobgo1.admin.sign.model.StudentExcel;
import com.green.greenjobgo1.common.entity.*;
import com.green.greenjobgo1.common.security.config.exception.*;
import com.green.greenjobgo1.common.utils.ExcelUtil;
import com.green.greenjobgo1.common.utils.MyFileUtils;
import com.green.greenjobgo1.common.utils.ResultUtils;
import com.green.greenjobgo1.repository.*;
import com.green.greenjobgo1.common.security.config.RedisService;
import com.green.greenjobgo1.common.security.config.security.AuthenticationFacade;
import com.green.greenjobgo1.common.security.config.security.JwtTokenProvider;
import com.green.greenjobgo1.common.security.config.security.model.MyUserDetails;
import com.green.greenjobgo1.common.security.sign.model.SignInResultDto;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminSignService {
    private final StudentRepository stdRep;
    private final AdminSubjectRepository subjectRep;
    private final EmployeeProfileRepository employeeprofileRep;
    private final StudentCourseSubjectRepository studentCourseSubjectRep;
    private final PasswordEncoder PW_ENCODER;
    private final ExcelUtil excelUtil;
    private final AdminRepository AdminRep;
    private final RedisService redisService;
    private final JwtTokenProvider JWT_PROVIDER;
    private final AuthenticationFacade facade;
    private final AdminCategoryRepository adminCategoryRep;
    private final JPAQueryFactory jpaQueryFactory;
    private final LoginPicRepository loginPicRep;

    QFileEntity file = QFileEntity.fileEntity;
    QStudentEntity stu = QStudentEntity.studentEntity;

    @Value("${file.dir}")
    private String FILE_DIR;

    @Transactional
    public int addExcel(MultipartFile studentfile) {
        List<StudentExcel> studentlist = new ArrayList<>();

        // 엑셀의 셀데이터를 객체에 담기 4번째 행부터 15번째 열까지
        List<Map<String, Object>> listMap = excelUtil.getListData(studentfile, 1, 15);

        log.info("listsize:{}",listMap.size());
        if (listMap.size()<1){
            throw new RestApiException(SignErrorCode.FILE_ERROR);
        }
        //   각 셀의 데이터를 StudentExcel에 넣는다 한다.
        for (Map<String, Object> map : listMap) {
            StudentExcel student = StudentExcel.builder()
                    .subjectName(map.get("0").toString())
                    .round(map.get("1").toString())
                    .startedAt(map.get("2").toString())
                    .endedAt(map.get("3").toString())
                    .trainingTime(map.get("4").toString())
                    .studentName(map.get("5").toString())
                    .birthday(map.get("6").toString())
                    .phone(map.get("7").toString())
                    .email(map.get("8").toString())
                    .address(map.get("9").toString())
                    .employee(map.get("10").toString())
                    .gender(map.get("11").toString())
                    .age(map.get("12").toString())
                    .education(map.get("13").toString()).build();

            studentlist.add(student);
        }


        //DB에 저장
        for (StudentExcel user : studentlist) {

            // 주민번호 뒷자리가 1 또는 2 이면 19를 반환하고 아니면 20을 반환한다. 19**년생 20**년생
            int year = (user.getBirthday().substring(7, 8).equals("1") || user.getBirthday().substring(7, 8).equals("2")) ? 19 : 20;


            String birthday = year + user.getBirthday().substring(0, 2) + "-" + user.getBirthday().substring(2, 4) + "-" + user.getBirthday().substring(4, 6);
            log.info("birthday:{}", birthday);
            LocalDate birth = LocalDate.parse(birthday);
            log.info("birthday:{}", birthday);
            //생년월일 생성

            // 생년월일 앞자리와 전화번호 뒷자리를 조합하여 비밀번호 생성
            String pwfirst = user.getBirthday().substring(0, 6);
            String phone = user.getPhone();
            String pwsecond = phone.substring(9, 13);
            log.info("패스워드확인:{}",pwfirst);
            log.info("패스워드확인:{}",pwsecond);
            log.info("패스워드확인:{}",pwfirst+pwsecond);
            CourseSubjectEntity subjectentity = subjectRep.findBySubjectNameAndRoundAndDelYn(user.getSubjectName(), Integer.parseInt(user.getRound()),0);


            if (subjectentity == null) {
                //log.info("에러코드 확인:{}: 존재하지 않는 과목입니다",subjectentity.getSubjectName());
                log.info("과목코드 확인:{}",user.getSubjectName());
                throw new RestApiException(SignErrorCode.SUBJECT_NULL);
            }

            EmployeeProfileEntity employeeEntity = employeeprofileRep.findByName(user.getEmployee());
            if (employeeEntity == null){
                log.info("에러코드 확인: 존재하지 않는 직원입니다.");
                throw new RestApiException(SignErrorCode.EMPLOYEE_NULL);
            }

            StudentEntity student = StudentEntity.builder()
                    .gender(user.getGender())
                    .id(user.getEmail())
                    .pw(PW_ENCODER.encode(pwfirst + pwsecond))
                    .mobileNumber(user.getPhone())
                    .name(user.getStudentName())
                    .birthday(birth)
                    .address(user.getAddress())
                    .education(user.getEducation())
                    .age(Integer.parseInt(user.getAge()))
                    .role("ROLE_USER")
                    .employeeProfile(employeeEntity)
                    .categorySubjectEntity(subjectentity.getCategorySubjectEntity())
                    .build();

            StudentEntity studententity = stdRep.findById(user.getEmail());

            // 중복된 이메일은 회원가입 X
            if (studententity == null){
                StudentEntity save = stdRep.save(student);

                log.info("ID:{}",save.getIstudent());
                //학생이 소속된 과목table 정보 가져오기
                log.info("과정명:{}",user.getSubjectName());
                CourseSubjectEntity courseSubjectEntity = subjectRep.findBySubjectNameAndRoundAndDelYn(user.getSubjectName(), Integer.parseInt(user.getRound()),0);

                //subject의 대분류 조회
                CategorySubjectEntity categorySubjectEntity = adminCategoryRep.findById(courseSubjectEntity.getCategorySubjectEntity().getIclassification()).get();

                Long iclassification = courseSubjectEntity.getCategorySubjectEntity().getIclassification();


                StudentCourseSubjectEntity entity = StudentCourseSubjectEntity.builder()
                        .studentEntity(save)
                        .courseSubjectEntity(subjectentity)
                        .iclassification(iclassification)
                        .iclassification(categorySubjectEntity.getIclassification())
                        .build();

                studentCourseSubjectRep.save(entity);
                if (save.getId() == null){
                    throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
                }

            }
        }
        return 1;
    }

    public void downloadstd(HttpServletResponse response) throws IOException {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("수강생다운로드");

        // Header 넣기
        int cellNum = 0;
//        Row header1 = sheet.createRow(0); // 첫번째줄 헤더
//        Row header2 = sheet.createRow(1);
        Row header3 = sheet.createRow(0);
        String[] headerName = new String [] {"과정명","회차","훈련 시작일","훈련 종료일","훈련시간","이름","생년월일(7자리)","핸드폰","이메일","거주지(구까지)","캠퍼스별 담당 실장","성별","나이","학력","최종이력서","포트폴리오"};

        //첫 번째 행에서 첫 번째부터 네 번째 까지 병합
//        sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
//        IntStream.rangeClosed(0, 4).forEach(i -> header1.createCell(i).setCellValue("과정 현황"));
//        sheet.addMergedRegion(new CellRangeAddress(1,1,0,4));
//        IntStream.rangeClosed(1, 4).forEach(i -> header2.createCell(i).setCellValue(""));
//
//        sheet.addMergedRegion(new CellRangeAddress(0,0,5,9));
//        IntStream.rangeClosed(5, 9).forEach(i -> header1.createCell(i).setCellValue("훈련생 현황"));
//        sheet.addMergedRegion(new CellRangeAddress(1,1,5,9));
//        IntStream.rangeClosed(5, 9).forEach(i -> header2.createCell(i).setCellValue("훈련생 정보"));
//
//        sheet.addMergedRegion(new CellRangeAddress(0,0,10,15));
//        IntStream.rangeClosed(10, 15).forEach(i -> header1.createCell(i).setCellValue("훈련생 희망 직무 현황"));
//        sheet.addMergedRegion(new CellRangeAddress(1,1,11,15));
//        IntStream.rangeClosed(11, 15).forEach(i -> header2.createCell(i).setCellValue("내담자특성상황"));

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
        IntStream.rangeClosed(0, 15).forEach(col -> {
            CellStyle style;


            if (col <= 4) {
                style = paleBlue;
            } else if (col <= 9) {
                style = lightGreen;
            } else {
                style = paleBlue;
            }

//            header1.getCell(col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(style);
//            header2.getCell(col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(style);
            header3.getCell(col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(style);

            style.setBorderTop(BorderStyle.THIN);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
        });

        List<StudentEntity> studentlist = stdRep.findAll();

         //Body
        int rowNum = 1;
        for (StudentEntity student : studentlist) {
            List<StudentCourseSubjectEntity> scsList = student.getScsList();
            log.info("scslistsize:{}",scsList.size());

            if (!scsList.isEmpty()) {
                for (int i = 0; i < scsList.size(); i++) {
                CourseSubjectEntity subjectEntity = subjectRep.findById(scsList.get(i).getCourseSubjectEntity().getIcourseSubject()).orElse(null);
                if (subjectEntity != null) {

                    Long aboutMeCount = jpaQueryFactory
                            .select(file.file.count())
                            .from(file)
                            .join(file.studentEntity, stu)
                            .where(stu.istudent.eq(student.getIstudent()), file.fileCategoryEntity.iFileCategory.eq(1L)).fetchOne();

                    Long portfolioCount = jpaQueryFactory
                            .select(file.file.count())
                            .from(file)
                            .join(file.studentEntity, stu)
                            .where(stu.istudent.eq(student.getIstudent()), file.fileCategoryEntity.iFileCategory.in(2, 3)).fetchOne();


                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(subjectEntity.getSubjectName()); //과목명
                    row.createCell(1).setCellValue(subjectEntity.getRound()); // 과정 회차
                    row.createCell(2).setCellValue(subjectEntity.getStartedAt().toString());
                    row.createCell(3).setCellValue(subjectEntity.getEndedAt().toString());
                    row.createCell(4).setCellValue(subjectEntity.getClassTime()); //수강시간
                    row.createCell(5).setCellValue(student.getName());
                    //생년월일 넣기 ex) 19990101-1******
                    row.createCell(6).setCellValue(dateFormatter(student.getBirthday())+"-"+generateGenderCode(student.getBirthday(),student.getGender())+"******");
                    row.createCell(7).setCellValue(student.getMobileNumber());
                    row.createCell(8).setCellValue(student.getId());
                    row.createCell(9).setCellValue(student.getAddress());
                    row.createCell(10).setCellValue(student.getEmployeeProfile().getName());
                    row.createCell(11).setCellValue(student.getGender());
                    row.createCell(12).setCellValue(student.getAge());
                    row.createCell(13).setCellValue(student.getEducation()); // 학력
                    row.createCell(14).setCellValue(aboutMeCount >0 ? "O": "-" );
                    row.createCell(15).setCellValue(portfolioCount > 0 ? "O": "-" );
                }
                }
            }
        }

        // Download
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment;filename=student.xlsx");

        try {
            wb.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            wb.close();
        }
    }
    public String dateFormatter(LocalDate birthday){
        LocalDate originalDate = LocalDate.parse(birthday.toString());
        // 원하는 형식으로 날짜를 포맷
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        String formattedDate = originalDate.format(formatter);

        return formattedDate;
    }


    public  int generateGenderCode(LocalDate birth, String gender) {
        int genderCode;

        // 2000년 이전 여자: 2, 남자: 1
        // 2000년 이후 여자: 3, 남자: 4
        LocalDate comparisonDate = LocalDate.of(2000, 1, 1);
        if (birth.isBefore(comparisonDate)) {
            genderCode = gender.equals("남") ? 1 : 2;
        } else {
            genderCode = gender.equals("여")? 4 : 3;
        }
        return genderCode;
    }

    public AdminEntity signUp(AdminParam p) {
        final String ADMIN = "ROLE_ADMIN";
        AdminEntity adminEntity = AdminRep.findById(p.getId());


        if (adminEntity == null) {
            return AdminRep.save(AdminEntity.builder()
                    .id(p.getId())
                    .pw(PW_ENCODER.encode(p.getPw()))
                    .name(p.getName())
                    .role(ADMIN).build());
        }
        //아이디가 있으면 에러
        throw new RestApiException(SignErrorCode.ID_DUPLICATE);
    }
    public AdminSignInResultDto signIn(AdminSigInParam p, String ip) {
        final String ADMIN = "ROLE_ADMIN";
        AdminEntity adminEntity = AdminRep.findById(p.getId());

        if (adminEntity == null) {
            throw new RestApiException(SignErrorCode.ID_NULL);
        }
        if (!PW_ENCODER.matches(p.getPw(), adminEntity.getPw())) {
            throw new RestApiException(SignErrorCode.PASSWORD_FAILED);
        }

        String redisKey = String.format("c:RT(%s):ADMIN:%s:%s", "Server", adminEntity.getIadmin(), ip);
        if (redisService.getValues(redisKey) != null) {
            redisService.deleteValues(redisKey);
        }

        String accessToken = JWT_PROVIDER.generateJwtToken(String.valueOf(adminEntity.getIadmin()),
                Collections.singletonList(ADMIN), JWT_PROVIDER.ADMIN_ACCESS_TOKEN_VALID_MS, JWT_PROVIDER.ACCESS_KEY);
        String refreshToken = JWT_PROVIDER.generateJwtToken(String.valueOf(adminEntity.getIadmin()),
                Collections.singletonList(ADMIN), JWT_PROVIDER.REFRESH_TOKEN_VALID_MS, JWT_PROVIDER.REFRESH_KEY);
        Long accessTokenTime = JWT_PROVIDER.ADMIN_ACCESS_TOKEN_VALID_MS;
        redisService.setValues(redisKey, refreshToken);

        AdminSignInResultDto dto = AdminSignInResultDto.builder()
                .accessToken(accessToken)
                .accessTokenTime(accessTokenTime)
                .refreshToken(refreshToken)
                .role(ADMIN)
                .id(p.getId())
                .name(adminEntity.getName())
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

        log.info("roles:{}",roles.get(0));
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

    public LoginPicEntity loginPic(MultipartFile pic){
        String fileDir = MyFileUtils.getAbsolutePath(FILE_DIR);
        String centerPath = String.format("%s/loginpic", MyFileUtils.getAbsolutePath(fileDir));

        File dic = new File(centerPath);
        if(!dic.exists()){
            dic.mkdirs();
        }

        String originFileName = pic.getOriginalFilename();
        String savedFileName = MyFileUtils.makeRandomFileNm(originFileName);
        String savedFilePath = String.format("%s/%s",centerPath, savedFileName);

        File target = new File(savedFilePath);
        try {
            pic.transferTo(target);
        }catch (Exception e) {
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }

        String img = savedFileName;

        LoginPicEntity loginPicEntity = LoginPicEntity.builder().pic(img).build();

        loginPicRep.save(loginPicEntity);

        return loginPicEntity;

    }

    public String getloginPic(){
        List<LoginPicEntity> all = loginPicRep.findAll();

        Collections.shuffle(all);

        LoginPicEntity loginPicEntity = all.get(0);
        String pic = "/img/loginpic/"+loginPicEntity.getPic();
        return pic;
    }


}

package com.green.greenjobgo1.company;


import com.green.greenjobgo1.admin.employeeProfile.model.EmployeeProfileVo;
import com.green.greenjobgo1.common.entity.EmployeeProfileEntity;
import com.green.greenjobgo1.common.utils.PagingUtils;
import com.green.greenjobgo1.company.model.*;
import com.green.greenjobgo1.config.entity.*;
import com.green.greenjobgo1.repository.CompanyRepository;
import com.green.greenjobgo1.repository.EmployeeProfileRepository;
import com.green.greenjobgo1.repository.StudentRepository;
import com.green.greenjobgo1.common.security.config.RedisService;
import com.green.greenjobgo1.common.security.config.security.AuthenticationFacade;
import com.green.greenjobgo1.common.security.config.security.JwtTokenProvider;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyService {
    private final PasswordEncoder PW_ENCODER;
    private final JwtTokenProvider JWT_PROVIDER;
    private final AuthenticationFacade facade;
    private final RedisService redisService;
    private final CompanyRepository companyRep;
    private final StudentRepository StudentRep;
    private final JPAQueryFactory jpaQueryFactory;
    private final EmployeeProfileRepository EmployeeProfileRep;

    QStudentEntity qstudent = QStudentEntity.studentEntity;

    QStudentCourseSubjectEntity qstudentCourseSubject = QStudentCourseSubjectEntity.studentCourseSubjectEntity;
    QCourseSubjectEntity qCourseSubject  = QCourseSubjectEntity.courseSubjectEntity;
    QFileEntity qfileEntity = QFileEntity.fileEntity;
    QCategorySubjectEntity qCategorySubjectEntity = QCategorySubjectEntity.categorySubjectEntity;

//    public String refreshToken(HttpServletRequest req, String refreshToken) throws RuntimeException {
//        String error = "유효하지 않은 토큰";
//        if(!(JWT_PROVIDER.isValidateToken(refreshToken, JWT_PROVIDER.REFRESH_KEY))) { return error; }
//
//        Claims claims = null;
//        try {
//            claims = JWT_PROVIDER.getClaims(refreshToken, JWT_PROVIDER.REFRESH_KEY);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (claims == null) {
//            return error;
//        }
//
//        String strIuser = claims.getSubject();
//        Long iuser = Long.valueOf(strIuser);
//        String ip = req.getRemoteAddr();
//        List<String> roles = (List<String>)claims.get("roles");
//
//        log.info("iuser:{}",iuser);
//        String redisKey ;
//
//        if ("ROLE_ADMIN".equals(roles.get(0))){
//            redisKey = String.format("c:RT(%s):ADMIN:%s:%s", "Server", iuser, ip);
//        } else if ("ROLE_USER".equals(roles.get(0))){
//            redisKey = String.format("c:RT(%s):USER:%s:%s", "Server", iuser, ip);
//        }else
//            redisKey = String.format("c:RT(%s):COMPANY:%s:%s", "Server", iuser, ip);
//
//        log.info("redisKey 키값 확인:{}",redisKey);
//
//        String redisRt = redisService.getValues(redisKey);
//        if (redisRt == null) {
//            return error;
//        }
//
//        try {
//            if (!redisRt.equals(refreshToken)) {
//                return error;
//            }
//
//            return JWT_PROVIDER.generateJwtToken(strIuser, roles,
//                    JWT_PROVIDER.ACCESS_TOKEN_VALID_MS, JWT_PROVIDER.ACCESS_KEY);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return error;
//    }
//
//    public void logout(HttpServletRequest req) {
//        String accessToken = JWT_PROVIDER.resolveToken(req, JWT_PROVIDER.TOKEN_TYPE);
//        Long iuser = facade.getLoginUserPk();
//        String ip = req.getRemoteAddr();
//        MyUserDetails userDetails = facade.getLoginUser();
//
//
//        String redisKey = null;
//        if ("ROLE_ADMIN".equals(userDetails.getRoles().get(0))){
//            redisKey = String.format("c:RT(%s):ADMIN:%s:%s", "Server", iuser, ip);
//        }else if ("ROLE_USER".equals(userDetails.getRoles().get(0))){
//            redisKey = String.format("c:RT(%s):USER:%s:%s", "Server", iuser, ip);
//        }else {
//            redisKey = String.format("c:RT(%s):COMPANY:%s:%s", "Server", iuser, ip);
//        }
//
//        log.info("rediskeys: {}",redisKey);
//
//        String refreshTokenInRedis = redisService.getValues(redisKey);
//        if (refreshTokenInRedis != null) {
//            redisService.deleteValues(redisKey);
//        }
//
//        long expiration = JWT_PROVIDER.getTokenExpirationTime(accessToken, JWT_PROVIDER.ACCESS_KEY) -
//                LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//        log.info("expiration: {}", expiration);
//        log.info("localDateTime-getTime(): {}", LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
//
//        redisService.setValuesWithTimeout(accessToken, "logout", expiration);
//    }


    public CompanyStdRes getstudent(int page,int size,Long icategory,String subjectName,String studentName){
        //page 값이 1이상인 경우 -1
        int page2 = (page > 0) ? (page - 1) : 0;
        Pageable pageable = PageRequest.of(page2, size);



        List<CompanyStdVo> StudentEntity  = jpaQueryFactory.select(
                Projections.bean(CompanyStdVo.class,
                        qstudent.istudent,
                        qfileEntity.file,
                        qstudent.name,
                        qCourseSubject.subjectName,
                        qCategorySubjectEntity.classification
                )).from(qstudent)
                .innerJoin(qstudentCourseSubject)
                .on(qstudentCourseSubject.studentEntity.istudent.eq(qstudent.istudent))
                .innerJoin(qCourseSubject)
                .on(qCourseSubject.icourseSubject.eq(qstudentCourseSubject.courseSubjectEntity.icourseSubject))
                .innerJoin(qfileEntity)
                .on(qfileEntity.studentEntity.istudent.eq(qstudent.istudent))
                .innerJoin(qCategorySubjectEntity)
                .on(qCategorySubjectEntity.iclassification.eq(qCourseSubject.categorySubjectEntity.iclassification))
                .where(qfileEntity.fileCategoryEntity.iFileCategory.eq(4L))
                .where(eqcategory(icategory))
                .where(eqsubjectName(subjectName))
                .where(eqstudentName(studentName))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = jpaQueryFactory.select(
                        qstudent.istudent.count()
                ).from(qstudent)
                .innerJoin(qstudentCourseSubject)
                .on(qstudentCourseSubject.studentEntity.istudent.eq(qstudent.istudent))
                .innerJoin(qCourseSubject)
                .on(qCourseSubject.icourseSubject.eq(qstudentCourseSubject.courseSubjectEntity.icourseSubject))
                .innerJoin(qfileEntity)
                .on(qfileEntity.studentEntity.istudent.eq(qstudent.istudent))
                .innerJoin(qCategorySubjectEntity)
                .on(qCategorySubjectEntity.iclassification.eq(qCourseSubject.categorySubjectEntity.iclassification))
                .where(qfileEntity.fileCategoryEntity.iFileCategory.eq(4L))
                .where(eqcategory(icategory))
                .where(eqsubjectName(subjectName))
                .where(eqstudentName(studentName)).fetchOne();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int totalcount = Math.toIntExact(count);
        int maxpage = (int) Math.ceil((double) count / pageable.getPageSize());

        PagingUtils utils = new PagingUtils(pageable.getPageNumber(), (int) maxpage, pageable);
        utils.setIdx((int) maxpage);

        log.info("maxpage:{}",maxpage);

        return CompanyStdRes.builder().page(utils).maxpage(maxpage).totalcount(totalcount).list(StudentEntity).build();

    }

    private BooleanExpression eqcategory(Long icategory) {
        if(icategory == null || icategory == 0) {
            return null;
        }
        return qCategorySubjectEntity.iclassification.eq(icategory);
    }

    private BooleanExpression eqsubjectName(String subjectName) {
        if(subjectName == null || subjectName.isEmpty()) {
            return null;
        }
        return qCourseSubject.subjectName.like("%"+subjectName+"%");
    }
    private BooleanExpression eqstudentName(String studentName) {
        if(studentName == null || studentName.isEmpty()) {
            return null;
        }
        return qstudent.name.like("%"+studentName+"%");
    }

    public CompanystdDetailRes detailStd(Long istudent){
        CompanyStdDetailVo Vo = jpaQueryFactory.select(Projections.bean(CompanyStdDetailVo.class,
                        qfileEntity.file,
                        qstudent.istudent,
                        qstudent.name,
                        qstudent.birthday,
                        qCourseSubject.subjectName,
                        qstudent.id,
                        qCourseSubject.startedAt,
                        qCourseSubject.endedAt,
                        qstudent.education,
                        qCategorySubjectEntity.classification
                )).from(qstudent)
                .innerJoin(qstudentCourseSubject)
                .on(qstudentCourseSubject.studentEntity.istudent.eq(qstudent.istudent))
                .innerJoin(qCourseSubject)
                .on(qCourseSubject.icourseSubject.eq(qstudentCourseSubject.courseSubjectEntity.icourseSubject))
                .innerJoin(qfileEntity)
                .on(qfileEntity.studentEntity.istudent.eq(qstudent.istudent))
                .innerJoin(qCategorySubjectEntity)
                .on(qCategorySubjectEntity.iclassification.eq(qCourseSubject.categorySubjectEntity.iclassification))
                .where(qstudent.istudent.eq(istudent))
                .where(qfileEntity.fileCategoryEntity.iFileCategory.eq(4L))
                .fetchOne();

        List<CompanyStdfileVo> file = jpaQueryFactory.select(Projections.constructor(CompanyStdfileVo.class,
                        qfileEntity.file
                )).from(qstudent)
                .innerJoin(qfileEntity)
                .on(qfileEntity.studentEntity.istudent.eq(qstudent.istudent))
                .where(qstudent.istudent.eq(istudent))
                .where(qfileEntity.fileCategoryEntity.iFileCategory.ne(4L))
                .fetch();
        return CompanystdDetailRes.builder().vo(Vo).file(file).build();
    }

    public List<CompanyMainVo> mainselstd(){
        List<CompanyMainVo> list = jpaQueryFactory.select(Projections.constructor(CompanyMainVo.class,
                        qfileEntity.file,
                        qstudent.istudent,
                        qstudent.name,
                        qCourseSubject.subjectName,
                        qCourseSubject.icourseSubject))
                .from(qstudent)
                .innerJoin(qfileEntity)
                .on(qfileEntity.studentEntity.istudent.eq(qstudent.istudent))
                .innerJoin(qCourseSubject)
                .on(qCourseSubject.icourseSubject.eq(qstudentCourseSubject.courseSubjectEntity.icourseSubject))
                .fetch();
        return list;
    }

    public List<EmployeeProfileVo> getProfile(){
        List<EmployeeProfileEntity> entityList = EmployeeProfileRep.findByDelYn(0);

        return entityList.stream().map(profile -> EmployeeProfileVo.builder()
                .oneWord(profile.getOneWord())
                .iemply(profile.getIemply())
                .counselingNumber (profile.getCounselingNumber())
                .name(profile.getName())
                .phoneNumber(profile.getPhoneNumber())
                .email(profile.getEmail())
                .profilePic("/home/download/employee/"+profile.getIemply()+"/"+profile.getProfilePic()).build()).toList();
    }

}

package com.green.greenjobgo1.company;


import com.green.greenjobgo1.admin.employeeProfile.model.EmployeeProfileVo;
import com.green.greenjobgo1.admin.std_management.model.AdminStudentCertificateRes;
import com.green.greenjobgo1.admin.std_management.model.AdminStudentDetailSubjectRes;
import com.green.greenjobgo1.common.entity.*;
import com.green.greenjobgo1.common.utils.PagingUtils;
import com.green.greenjobgo1.company.model.*;
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
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyService {
    private final JPAQueryFactory jpaQueryFactory;
    private final EmployeeProfileRepository EmployeeProfileRep;
    private final StudentRepository studentRep;

    QStudentEntity qstudent = QStudentEntity.studentEntity;
    QStudentCourseSubjectEntity qstudentCourseSubject = QStudentCourseSubjectEntity.studentCourseSubjectEntity;
    QCourseSubjectEntity qCourseSubject  = QCourseSubjectEntity.courseSubjectEntity;
    QFileEntity qfileEntity = QFileEntity.fileEntity;
    QCategorySubjectEntity qCategorySubjectEntity = QCategorySubjectEntity.categorySubjectEntity;
    QCertificateEntity certificate = QCertificateEntity.certificateEntity;

    public CompanyStdRes getstudent(Pageable pageable,Long icategory,String subjectName,String studentName){

        String sort = "istudent,DESC";
        //page 값이 1이상인 경우 -1
        //int page2 = (page > 0) ? (page - 1) : 0;
        //String[] parts = sort.split(",");
        //Pageable pageable = PageRequest.of(page2, size, Sort.Direction.fromString(parts[1]), parts[0]);

        //Pageable pageable = PageRequest.of(page2, size, Sort.Direction.valueOf(sort));


        List<CompanyStdVo> StudentEntity  = jpaQueryFactory.select(
                Projections.bean(CompanyStdVo.class,
                        qCourseSubject.subjectName,
                        qstudent.name.as("studentName"),
                        qstudent.istudent,
                        qfileEntity.file.as("img")
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
        long count = jpaQueryFactory.select(
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
                .where(eqstudentName(studentName))
                .fetchOne();


        int totalcount = Math.toIntExact(count);
        int maxpage = (int) Math.ceil((double) count / pageable.getPageSize());

        PagingUtils utils = new PagingUtils(pageable.getPageNumber() + 1,  (int)count, pageable);
        utils.setIdx( (int)count);

        log.info("maxpage:{}",maxpage);

        //url 위치 붙여주기
        List<CompanyStdVo> list = StudentEntity.stream().map(item -> CompanyStdVo.builder()
                .img("/img/student/"+item.getIstudent()+"/"+item.getImg())
                .istudent(item.getIstudent())
                .studentName(item.getStudentName())
                .subjectName(item.getSubjectName()).build()).toList();

        return CompanyStdRes.builder().page(utils).maxpage(maxpage).totalcount(totalcount).vo(list).build();

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
    private BooleanExpression eqistudent(Long istudent) {
        if(istudent == null || istudent == 0) {
            return null;
        }
        return qstudent.istudent.eq(istudent);
    }

    public CompanystdDetailRes detailStd(Long istudent){
        StudentEntity student = studentRep.findById(istudent).get();

//        CompanyStdDetail v2 = jpaQueryFactory.select(Projections.constructor(CompanyStdDetail.class,
//                        qstudent.name,
//                        qstudent.gender,
//                        qstudent.age,
//                        qstudent.birthday,
//                        qstudent.address,
//                        qstudent.id.as("email"),
//                        qCourseSubject.startedAt,
//                        qCourseSubject.endedAt,
//                        qstudent.mobileNumber,
//                        qstudent.education
//                )).from(qstudent)
//                .innerJoin(qstudentCourseSubject)
//                .on(qstudentCourseSubject.studentEntity.istudent.eq(qstudent.istudent))
//                .innerJoin(qCourseSubject)
//                .on(qCourseSubject.icourseSubject.eq(qstudentCourseSubject.courseSubjectEntity.icourseSubject))
//                .where(eqistudent(istudent))
//                .fetchOne();

        List<CompanyStudentCertificateRes> res = jpaQueryFactory.select(
                        Projections.bean(CompanyStudentCertificateRes.class, certificate.icertificate, certificate.certificate))
                .from(certificate)
                .join(certificate.studentEntity, qstudent)
                .where(qstudent.istudent.eq(istudent)).fetch();

        List<CompanyStdfileVo> file = jpaQueryFactory.select(Projections.bean(CompanyStdfileVo.class,
                        qfileEntity.file
                )).from(qstudent)
                .innerJoin(qfileEntity)
                .on(qfileEntity.studentEntity.istudent.eq(qstudent.istudent))
                .where(qstudent.istudent.eq(istudent))
                .fetch();

        List<CompanyDetailSubjectRes> studentsubject = jpaQueryFactory.select(Projections.bean(CompanyDetailSubjectRes.class,
                        qCourseSubject.icourseSubject,
                        qCourseSubject.subjectName,
                        qCourseSubject.startedAt,
                        qCourseSubject.endedAt
                ))
                .from(qCourseSubject)
                .join(qCourseSubject.scsList, qstudentCourseSubject)
                .join(qstudentCourseSubject.studentEntity, qstudent)
                .where(qstudent.istudent.eq(istudent)).fetch();

        CompanyStdDetailVo build = CompanyStdDetailVo.builder()
                .name(student.getName())
                .gender(student.getGender())
                .age(student.getAge())
                .birthday(student.getBirthday())
                .address(student.getAddress())
                .email(student.getId())
                .startedAt(student.getStartedAt())
                .endedAt(student.getEndedAt())
                .mobileNumber(student.getMobileNumber())
                .education(student.getEducation())
                .certificates(res)
                .subject(studentsubject).build();

        return CompanystdDetailRes.builder().vo(build).file(file).build();
    }

    public List<CompanyMainVo> mainselstd(Long icategory){
        List<CompanyMainVo> list = jpaQueryFactory.select(Projections.constructor(CompanyMainVo.class,
                        qfileEntity.file,
                        qstudent.istudent,
                        qstudent.name,
                        qCourseSubject.subjectName))
                .from(qstudent)
                .innerJoin(qfileEntity)
                .on(qfileEntity.studentEntity.istudent.eq(qstudent.istudent))
                .innerJoin(qstudentCourseSubject)
                .on(qstudentCourseSubject.studentEntity.istudent.eq(qstudent.istudent))
                .innerJoin(qCourseSubject)
                .on(qCourseSubject.icourseSubject.eq(qstudentCourseSubject.courseSubjectEntity.icourseSubject))
                .where(eqcategory(icategory))
                .where(qfileEntity.fileCategoryEntity.iFileCategory.eq(4L))
                //.where(qfileEntity.file.eq())
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
                .profilePic("/img/employee/"+profile.getIemply()+"/"+profile.getProfilePic()).build()).toList();
    }

}

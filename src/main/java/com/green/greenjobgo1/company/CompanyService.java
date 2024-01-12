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
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
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

        log.info("pageable:{}",pageable.getSort());
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
                .orderBy(getorder(pageable))
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

    private OrderSpecifier[] getorder(Pageable pageable) {
        List<OrderSpecifier> orders = new LinkedList();
        if(!pageable.getSort().isEmpty()) {
            for(Sort.Order order : pageable.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;

                switch(order.getProperty().toLowerCase()) {
                    case "istudent": orders.add(new OrderSpecifier(direction,qstudent.istudent)); break;
                    case "subjectName": orders.add(new OrderSpecifier(direction,qCourseSubject.subjectName)); break;
                    case "name": orders.add(new OrderSpecifier(direction,qstudent.name)); break;

                }
            }
        }
        return orders.stream().toArray(OrderSpecifier[]::new);
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

        CompanyStdDetail vo = jpaQueryFactory.select(Projections.constructor(CompanyStdDetail.class,
                        qstudent.name,
                        qstudent.gender,
                        qstudent.age,
                        qstudent.birthday,
                        qstudent.address,
                        qstudent.id.as("email"),
                        qCourseSubject.startedAt,
                        qCourseSubject.endedAt,
                        qCourseSubject.subjectName,
                        qstudent.mobileNumber,
                        qstudent.education
                )).from(qstudent)
                .innerJoin(qstudentCourseSubject)
                .on(qstudentCourseSubject.studentEntity.istudent.eq(qstudent.istudent))
                .innerJoin(qCourseSubject)
                .on(qCourseSubject.icourseSubject.eq(qstudentCourseSubject.courseSubjectEntity.icourseSubject))
                .where(eqistudent(istudent))
                .fetchOne();

        List<CompanyStudentCertificateRes> res = jpaQueryFactory.select(
                        Projections.bean(CompanyStudentCertificateRes.class, certificate.icertificate, certificate.certificate))
                .from(certificate)
                .join(certificate.studentEntity, qstudent)
                .where(qstudent.istudent.eq(istudent)).fetch();

        List<CompanyStdfileVo> file = jpaQueryFactory.select(Projections.bean(CompanyStdfileVo.class,
                        qfileEntity.file,
                        qfileEntity.fileCategoryEntity.iFileCategory,
                        qfileEntity.fileCategoryEntity.file.as("file_category")
                )).from(qfileEntity)
                .innerJoin(qfileEntity)
                .on(qfileEntity.studentEntity.istudent.eq(qstudent.istudent))
                .where(qstudent.istudent.eq(istudent))
                .fetch();
        log.info("file:{}",file.get(0).getIFileCategory());

//        List<CompanyDetailSubjectRes> studentsubject = jpaQueryFactory.select(Projections.bean(CompanyDetailSubjectRes.class,
//                        qCourseSubject.subjectName
//                ))
//                .from(qCourseSubject)
//                .join(qCourseSubject.scsList, qstudentCourseSubject)
//                .join(qstudentCourseSubject.studentEntity, qstudent)
//                .where(qstudent.istudent.eq(istudent)).fetch();

        CompanyStdDetailVo build = CompanyStdDetailVo.builder()
                .name(vo.getName())
                .gender(vo.getGender())
                .age(vo.getAge())
                .birthday(vo.getBirthday())
                .address(vo.getAddress())
                .email(vo.getEmail())
                .startedAt(vo.getStartedAt())
                .endedAt(vo.getEndedAt())
                .mobileNumber(vo.getMobileNumber())
                .education(vo.getEducation())
                .certificates(res)
                .subject(vo.getSubjectName()).build();

        //url 붙여주기
        List<CompanyStdfileVo> list = file.stream().map(item -> CompanyStdfileVo.builder()
                .iFileCategory(item.getIFileCategory())
                .file("/img/student/" + student.getIstudent() + "/" + item.getFile())
                .file_category(item.getFile_category()).build()).toList();

        return CompanystdDetailRes.builder().vo(build).file(list).build();
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
                .innerJoin(qCategorySubjectEntity)
                .on(qCategorySubjectEntity.iclassification.eq(qCourseSubject.categorySubjectEntity.iclassification))
                .where(eqcategory(icategory))
                .where(qfileEntity.fileCategoryEntity.iFileCategory.eq(4L))
                //.where(qfileEntity.file.eq())
                .fetch();

        return list.stream().map(item-> CompanyMainVo.builder()
                .file("/img/student/" + item.getIstudent()+"/"+item.getFile())
                .istudent(item.getIstudent())
                .name(item.getName())
                .subjectName(item.getSubjectName()).build()).toList();

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

package com.green.greenjobgo1.student;

import com.green.greenjobgo1.admin.std_management.model.*;
import com.green.greenjobgo1.common.entity.*;
import com.green.greenjobgo1.student.model.*;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentQdsl {

    private final JPAQueryFactory jpaQueryFactory;

    QStudentEntity stu = QStudentEntity.studentEntity;
    QCourseSubjectEntity cos = QCourseSubjectEntity.courseSubjectEntity;
    QStudentCourseSubjectEntity scs = QStudentCourseSubjectEntity.studentCourseSubjectEntity;
    QFileEntity file = QFileEntity.fileEntity;
    QCompanyListEntity qCompanyList = QCompanyListEntity.companyListEntity;
    QCertificateEntity certificate = QCertificateEntity.certificateEntity;

    public StudentSelStudentRes studentVo(Long istudent) {
        JPAQuery<StudentSelStudentRes> query = jpaQueryFactory
                .select(Projections.bean(StudentSelStudentRes.class, stu.istudent, stu.name, stu.startedAt, stu.endedAt,
                        stu.address, stu.mobileNumber, stu.id, stu.education, stu.certificates))
                .from(scs)
                .join(scs.studentEntity, stu)
                .where(stu.istudent.eq(istudent));
        return query.fetchOne();
    }

    public List<StudentCertificateSelRes> certificateRes(Long istudent) {
        JPAQuery<StudentCertificateSelRes> query = jpaQueryFactory.select(
                        Projections.bean(StudentCertificateSelRes.class, certificate.icertificate, certificate.certificate))
                .from(certificate)
                .join(certificate.studentEntity, stu)
                .where(stu.istudent.eq(istudent));
        return query.fetch();
    }

    public List<StudentFileRes> fileVos(StudentSelDto dto) {
        JPAQuery<StudentFileRes> query = jpaQueryFactory
                .select(Projections.bean(StudentFileRes.class, file.ifile, file.file, file.oneWord, file.mainYn))
                .from(file)
                .join(file.studentEntity, stu)
                .where(stu.istudent.eq(dto.getIstudent()),
                        file.fileCategoryEntity.iFileCategory.eq(2L))
                .orderBy(file.mainYn.desc(), file.ifile.asc());
        return query.fetch();
    }

    public List<StudentFileLink> fileLinks(StudentSelDto dto) {
        JPAQuery<StudentFileLink> query = jpaQueryFactory
                .select(Projections.bean(StudentFileLink.class, file.ifile, file.file.as("fileLink"), file.oneWord, file.mainYn))
                .from(file)
                .join(file.studentEntity, stu)
                .where(stu.istudent.eq(dto.getIstudent()),
                        file.fileCategoryEntity.iFileCategory.eq(3L))
                .orderBy(file.ifile.asc());
        return query.fetch();
    }

    public StudentImg img(StudentSelDto dto) {
        JPAQuery<StudentImg> query = jpaQueryFactory
                .select(Projections.bean(StudentImg.class, file.file.as("img"), file.ifile))
                .from(file)
                .join(file.studentEntity, stu)
                .where(stu.istudent.eq(dto.getIstudent()),
                        file.fileCategoryEntity.iFileCategory.eq(4L))
                .orderBy(file.ifile.asc());
        return query.fetchOne();
    }

    public StudentResume resume(StudentSelDto dto) {
        JPAQuery<StudentResume> query = jpaQueryFactory
                .select(Projections.bean(StudentResume.class, file.ifile, file.file.as("resume"), file.oneWord))
                .from(file)
                .join(file.studentEntity, stu)
                .where(stu.istudent.eq(dto.getIstudent()),
                        file.fileCategoryEntity.iFileCategory.eq(1L))
                .orderBy(file.ifile.asc());
        return query.fetchOne();
    }

    public StudentDetailSubjectRes subjectList(Long istudent) {
        JPAQuery<StudentDetailSubjectRes> query = jpaQueryFactory.select(
                        Projections.bean(StudentDetailSubjectRes.class, cos.icourseSubject, cos.subjectName, cos.startedAt, cos.endedAt))
                .from(cos)
                .join(cos.scsList, scs)
                .join(scs.studentEntity, stu)
                .where(stu.istudent.eq(istudent));
        return query.fetchOne();
    }

    public Long countByFile(Long studentId) {
        JPAQuery<Long> query = jpaQueryFactory
                .select(file.file.count())
                .from(file)
                .where(file.fileCategoryEntity.iFileCategory.eq(2L)
                        .and(file.studentEntity.istudent.eq(studentId)));
        return query.fetchOne();
    }

    public Long countByFileLink(Long studentId) {
        JPAQuery<Long> query = jpaQueryFactory
                .select(file.file.count())
                .from(file)
                .where(file.fileCategoryEntity.iFileCategory.eq(3L)
                        .and(file.studentEntity.istudent.eq(studentId)));
        return query.fetchOne();
    }

    public Long countByFileAndFileLink(Long studentId) {
        JPAQuery<Long> query = jpaQueryFactory
                .select(file.file.count())
                .from(file)
                .where(file.fileCategoryEntity.iFileCategory.in(2, 3L)
                        .and(file.studentEntity.istudent.eq(studentId)));
        return query.fetchOne();
    }

    public Long countByResume(Long istudent) {
        JPAQuery<Long> query = jpaQueryFactory
                .select(file.file.count())
                .from(file)
                .where(file.fileCategoryEntity.iFileCategory.eq(1L)
                        .and(file.studentEntity.istudent.eq(istudent)));
        return query.fetchOne();
    }

    public Long countByThumbnail(Long istudent) {
        JPAQuery<Long> query = jpaQueryFactory
                .select(file.file.count())
                .from(file)
                .where(file.fileCategoryEntity.iFileCategory.eq(4L),
                        file.studentEntity.istudent.eq(istudent));
        return query.fetchOne();
    }

    public Long countByPortfolioMain(Long istudent) {
        JPAQuery<Long> query = jpaQueryFactory
                .select(file.file.count())
                .from(file)
                .where(file.studentEntity.istudent.eq(istudent),
                        file.mainYn.eq(1));
        return query.fetchOne();
    }

    public List<CompanyListEntity> companyList(String companyName, Pageable pageable) {
        JPAQuery<CompanyListEntity> query =  jpaQueryFactory.select(Projections.constructor(CompanyListEntity.class,
                        qCompanyList.companyCode,
                        qCompanyList.area,
                        qCompanyList.companyName,
                        qCompanyList.leaderName,
                        qCompanyList.homepage,
                        qCompanyList.manager,
                        qCompanyList.phoneNumber,
                        qCompanyList.dateConslusion))
                .from(qCompanyList)
                .where(eqCompanyName(companyName))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qCompanyList.companyCode.desc());
        return query.fetch();
    }

    public CertificateRes certificate(StudentCertificateDto dto) {
        JPAQuery<CertificateRes> query = jpaQueryFactory
                .select(Projections.bean(CertificateRes.class, certificate.icertificate, certificate.certificate))
                .from(certificate)
                .join(certificate.studentEntity, stu)
                .where(stu.istudent.eq(dto.getIstudent()),
                        certificate.icertificate.eq(dto.getIcertificate()));
        return query.fetchOne();
    }
    public Long companyIdx(String companyName) {
        JPAQuery<Long> count = jpaQueryFactory
                .select(qCompanyList.companyCode.count())
                .from(qCompanyList)
                .where(eqCompanyName(companyName));

        return count.fetchOne();
    }

    private BooleanExpression eqCompanyName(String companyName) {
        if(companyName == null || companyName.isEmpty()) {
            return null;
        }
        return qCompanyList.companyName.like("%"+companyName+"%");

    }


}

package com.green.greenjobgo1.admin.std_management;

import com.green.greenjobgo1.admin.std_management.model.*;
import com.green.greenjobgo1.common.entity.*;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdminStudentQdsl {

    private final JPAQueryFactory jpaQueryFactory;
    @Autowired
    public AdminStudentQdsl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    QCategorySubjectEntity cas = QCategorySubjectEntity.categorySubjectEntity;
    QCourseSubjectEntity cos = QCourseSubjectEntity.courseSubjectEntity;
    QStudentCourseSubjectEntity scs = QStudentCourseSubjectEntity.studentCourseSubjectEntity;
    QStudentEntity stu = QStudentEntity.studentEntity;
    QFileEntity file = QFileEntity.fileEntity;
    QCertificateEntity certificate = QCertificateEntity.certificateEntity;
    QCategorySubjectEntity cate = QCategorySubjectEntity.categorySubjectEntity;

    public List<AdminStudentRes> studentVos(AdminStudentDto dto, Pageable pageable) {

        JPAQuery<AdminStudentRes> query = jpaQueryFactory
                .select(Projections.bean(AdminStudentRes.class, stu.istudent, cas.classification, cos.subjectName,
                        cos.startedAt, cos.endedAt, stu.name, stu.gender, stu.address, stu.mobileNumber
                        , stu.education))
                .from(scs)
                .join(scs.studentEntity, stu)
                .join(scs.courseSubjectEntity, cos)
                .join(cos.categorySubjectEntity, cas)
                .where(eqIclassification(dto.getIcategory()),
                        eqSubjectName(dto.getSubjectName()),
                        stu.delYn.eq(0)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(stu.istudent.asc());
        return query.fetch();
    }

    public List<AdminStudentFile> fileVos(AdminStudentDetailDto dto) {
        JPAQuery<AdminStudentFile> query = jpaQueryFactory
                .select(Projections.bean(AdminStudentFile.class, file.file))
                .from(file)
                .join(file.studentEntity, stu)
                .where(stu.istudent.eq(dto.getIstudent()))
                .orderBy(file.fileCategoryEntity.iFileCategory.desc());
        return query.fetch();
    }

    public Long fileCount(Long istudent) {
        JPAQuery<Long> query = jpaQueryFactory
                .select(file.file.count())
                .from(file)
                .join(file.studentEntity, stu)
                .where(stu.istudent.eq(istudent), file.fileCategoryEntity.iFileCategory.in(1,2,3));
        return query.fetchOne();
    }

    public Long certificateCount(Long istudent) {
        JPAQuery<Long> query = jpaQueryFactory
                .select(certificate.certificate.count())
                .from(certificate)
                .join(certificate.studentEntity, stu)
                .where(stu.istudent.eq(istudent));
        return query.fetchOne();
    }

    public List<AdminStudentCertificateRes> certificateRes(Long istudent) {
        JPAQuery<AdminStudentCertificateRes> query = jpaQueryFactory.select(
                        Projections.bean(AdminStudentCertificateRes.class, certificate.icertificate, certificate.certificate))
                .from(certificate)
                .join(certificate.studentEntity, stu)
                .where(stu.istudent.eq(istudent));
        return query.fetch();
    }

    public AdminStudentDetailSubjectRes subjectList(Long istudent) {
        JPAQuery<AdminStudentDetailSubjectRes> query = jpaQueryFactory.select(
                Projections.bean(AdminStudentDetailSubjectRes.class, cos.icourseSubject, cos.subjectName))
                .from(cos)
                .join(cos.scsList, scs)
                .join(scs.studentEntity, stu)
                .where(stu.istudent.eq(istudent));
        return query.fetchOne();
    }

    public List<AdminPortfolioRes> portfolioVos(AdminPortfolioDto dto, Pageable pageable) {
        JPAQuery<AdminPortfolioRes> query = jpaQueryFactory.select(
                        Projections.bean(AdminPortfolioRes.class, stu.name.as("studentName")
                                , cos.subjectName, file.file.as("img"), stu.istudent, stu.storageYn, stu.huntJobYn))
                .from(stu)
                .join(stu.scsList, scs)
                .join(scs.courseSubjectEntity, cos)
                .join(cos.categorySubjectEntity, cate)
                .join(stu.files, file)
                .where(eqIclassification(dto.getIclassfication()),
                        eqSubjectName(dto.getSubjectName()),
                        eqStudentName(dto.getStudentName()),
                        file.fileCategoryEntity.iFileCategory.eq(4L),
                        stu.delYn.eq(0))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(file.mainYn.desc(), stu.storageYn.desc(), stu.istudent.asc());
        return query.fetch();
    }

    public List<AdminStorageStudentRes> storageVos(AdminStorageStudentDto dto, Pageable pageable) {
        JPAQuery<AdminStorageStudentRes> query = jpaQueryFactory.select(
                        Projections.bean(AdminStorageStudentRes.class, stu.istudent ,stu.name.as("studentName")
                                , cos.subjectName, file.file.as("img"), stu.introducedLine, stu.storageYn, stu.companyMainYn, stu.huntJobYn))
                .from(stu)
                .join(stu.scsList, scs)
                .join(scs.courseSubjectEntity, cos)
                .join(cos.categorySubjectEntity, cate)
                .join(stu.files, file)
                .where(eqIclassification(dto.getIclassfication()),
                        eqSubjectName(dto.getSubjectName()),
                        eqStudentName(dto.getStudentName()),
                        file.fileCategoryEntity.iFileCategory.eq(4L),
                        stu.storageYn.eq(1),
                        stu.delYn.eq(0))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(stu.companyMainYn.desc(), cos.endedAt.desc());
        return query.fetch();
    }

    public List<AdminStudentRoleSelRes> roleList() {
        JPAQuery<AdminStudentRoleSelRes> query = jpaQueryFactory.selectDistinct
                        (Projections.bean(AdminStudentRoleSelRes.class, cos.subjectName, cos.round, stu.startedAt, stu.endedAt))
                .from(cos)
                .join(cos.scsList, scs)
                .join(scs.studentEntity, stu)
                .where(stu.editableYn.eq(1));
        return query.fetch();
    }

    public Long rowCount(Long iclassification) {
        JPAQuery<Long> query = jpaQueryFactory
                .select(stu.istudent.count())
                .from(scs)
                .join(scs.studentEntity, stu)
                .where(scs.iclassification.eq(iclassification),
                        stu.companyMainYn.eq(1));
        return query.fetchOne();
    }

    public Long countByFileCategoryEntityIFileCategoryInAndStudentEntityIstudent(List<Long> fileCategoryIds, Long studentId) {
        JPAQuery<Long> query = jpaQueryFactory
                .select(file.file.count())
                .from(file)
                .where(file.fileCategoryEntity.iFileCategory.in(fileCategoryIds)
                        .and(file.studentEntity.istudent.eq(studentId).or(file.studentEntity.isNull())));
        return query.fetchOne();
    }

    public Long stdIdx(AdminStudentDto dto) {
        JPAQuery<Long> query = jpaQueryFactory.select(stu.istudent.count())
                .from(scs)
                .join(scs.studentEntity, stu)
                .join(scs.courseSubjectEntity, cos)
                .join(cos.categorySubjectEntity, cas)
                .where(eqIclassification(dto.getIcategory())
                        , eqSubjectName(dto.getSubjectName()), stu.delYn.eq(0));
        return query.fetchOne();
    }

    public Long portfolioIdx(AdminPortfolioDto dto) {
        JPAQuery<Long> query = jpaQueryFactory.select(file.file.count())
                .from(stu)
                .join(stu.scsList, scs)
                .join(scs.courseSubjectEntity, cos)
                .join(cos.categorySubjectEntity, cate)
                .join(stu.files, file)
                .where(eqIclassification(dto.getIclassfication()),
                        eqSubjectName(dto.getSubjectName()),
                        eqStudentName(dto.getStudentName()),
                        file.fileCategoryEntity.iFileCategory.eq(4L),
                        stu.delYn.eq(0));
        return query.fetchOne();
    }


    public Long storageIdx(AdminStorageStudentDto dto) {
        JPAQuery<Long> query = jpaQueryFactory.selectDistinct(stu.istudent.count())
                .from(stu)
                .join(stu.scsList, scs)
                .join(scs.courseSubjectEntity, cos)
                .join(cos.categorySubjectEntity, cate)
                .join(stu.files, file)
                .where(eqIclassification(dto.getIclassfication()),
                        eqSubjectName(dto.getSubjectName()),
                        eqStudentName(dto.getStudentName()),
                        file.fileCategoryEntity.iFileCategory.eq(4L),
                        stu.storageYn.eq(1),
                        stu.delYn.eq(0));
        return query.fetchOne();
    }

    private BooleanExpression eqIclassification(Long iclassification) {
        return iclassification != null ? cas.iclassification.eq(iclassification) : null;
    }

    private BooleanExpression eqSubjectName(String subjectName) {
        return subjectName != null ? cos.subjectName.contains(subjectName) : null;
    }

    private BooleanExpression eqStudentName(String studentName) {
        return studentName != null ? stu.name.contains(studentName) : null;
    }

    private BooleanExpression eqStorageYn(Integer storageYn) {
        return storageYn != null ? stu.storageYn.eq(storageYn) : null;

    }

    private BooleanExpression eqDelYn(Integer delYn) {
        return delYn != null ? cos.delYn.eq(delYn) : null;
    }
}

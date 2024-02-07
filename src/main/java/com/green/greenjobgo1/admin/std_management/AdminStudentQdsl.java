package com.green.greenjobgo1.admin.std_management;

import com.green.greenjobgo1.admin.std_management.model.*;
import com.green.greenjobgo1.common.entity.*;
import com.green.greenjobgo1.company.model.CompanyStdVo;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.LinkedList;
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
                        , stu.education, stu.huntJobYn, cos.round))
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

    public List<AdminStudentFileRes> fileVos(AdminStudentDetailDto dto) {
        JPAQuery<AdminStudentFileRes> query = jpaQueryFactory
                .select(Projections.bean(AdminStudentFileRes.class, file.ifile, file.file, file.oneWord, file.mainYn, file.originFileName))
                .from(file)
                .join(file.studentEntity, stu)
                .where(stu.istudent.eq(dto.getIstudent()),
                        file.fileCategoryEntity.iFileCategory.eq(2L))
                .orderBy(file.mainYn.desc(), file.ifile.asc());
        return query.fetch();
    }

    public List<AdminStudentFileLink> fileLinks(AdminStudentDetailDto dto) {
        JPAQuery<AdminStudentFileLink> query = jpaQueryFactory
                .select(Projections.bean(AdminStudentFileLink.class, file.ifile, file.file.as("fileLink"), file.oneWord, file.mainYn))
                .from(file)
                .join(file.studentEntity, stu)
                .where(stu.istudent.eq(dto.getIstudent()),
                        file.fileCategoryEntity.iFileCategory.eq(3L))
                .orderBy(file.ifile.asc());
        return query.fetch();
    }

    public AdminStudentImg img(AdminStudentDetailDto dto) {
        JPAQuery<AdminStudentImg> query = jpaQueryFactory
                .select(Projections.bean(AdminStudentImg.class, file.file.as("img"), file.ifile, file.originFileName))
                .from(file)
                .join(file.studentEntity, stu)
                .where(stu.istudent.eq(dto.getIstudent()),
                        file.fileCategoryEntity.iFileCategory.eq(4L))
                .orderBy(file.ifile.asc());
        return query.fetchOne();
    }

    public AdminStudentResume resume(AdminStudentDetailDto dto) {
        JPAQuery<AdminStudentResume> query = jpaQueryFactory
                .select(Projections.bean(AdminStudentResume.class, file.ifile, file.file.as("resume"), file.oneWord, file.originFileName))
                .from(file)
                .join(file.studentEntity, stu)
                .where(stu.istudent.eq(dto.getIstudent()),
                        file.fileCategoryEntity.iFileCategory.eq(1L))
                .orderBy(file.ifile.asc());
        return query.fetchOne();
    }

    public AdminStudentCertificateRes certificate(AdminStudentCertificateDto dto) {
        JPAQuery<AdminStudentCertificateRes> query = jpaQueryFactory
                .select(Projections.bean(AdminStudentCertificateRes.class, certificate.icertificate, certificate.certificate))
                .from(certificate)
                .join(certificate.studentEntity, stu)
                .where(stu.istudent.eq(dto.getIstudent()),
                        certificate.icertificate.eq(dto.getIcertificate()));
        return query.fetchOne();
    }

    public Long fileCount(Long istudent) {
        JPAQuery<Long> query = jpaQueryFactory
                .select(file.file.count())
                .from(file)
                .join(file.studentEntity, stu)
                .where(stu.istudent.eq(istudent), file.fileCategoryEntity.iFileCategory.in(2, 3));
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
                        Projections.bean(AdminStudentDetailSubjectRes.class, cos.icourseSubject, cos.subjectName, cos.startedAt, cos.endedAt))
                .from(cos)
                .join(cos.scsList, scs)
                .join(scs.studentEntity, stu)
                .where(stu.istudent.eq(istudent));
        return query.fetchOne();
    }

    public List<AdminPortfolioRes> portfolioVos(AdminPortfolioDto dto, Pageable pageable) {
        JPAQuery<AdminPortfolioRes> query = jpaQueryFactory.selectDistinct(
                        Projections.bean(AdminPortfolioRes.class, stu.name.as("studentName")
                                , cos.subjectName
                                , stu.istudent, stu.storageYn, stu.huntJobYn
                                , ExpressionUtils.as(JPAExpressions.selectDistinct(file.file)
                                        .from(stu)
                                        .join(stu.scsList, scs)
                                        .join(scs.courseSubjectEntity, cos)
                                        .join(cos.categorySubjectEntity, cate)
                                        .join(stu.files, file)
                                        .where(eqIclassification(dto.getIclassfication()),
                                                eqSubjectName(dto.getSubjectName()),
                                                eqStudentName(dto.getStudentName()),
                                                file.fileCategoryEntity.iFileCategory.eq(4L),
                                                stu.delYn.eq(0)),"img")))
                .from(stu)
                .join(stu.scsList, scs)
                .join(scs.courseSubjectEntity, cos)
                .join(cos.categorySubjectEntity, cate)
                .join(stu.files, file)
                .where(eqIclassification(dto.getIclassfication()),
                        eqSubjectName(dto.getSubjectName()),
                        eqStudentName(dto.getStudentName()),
                        stu.delYn.eq(0))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(cos.endedAt.desc(),stu.istudent.desc());
        return query.fetch();
    }

    public List<AdminStorageStudentRes> storageVos(AdminStorageStudentDto dto, Pageable pageable) {
        JPAQuery<AdminStorageStudentRes> query = jpaQueryFactory.select(
                        Projections.bean(AdminStorageStudentRes.class, stu.istudent, stu.name.as("studentName")
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
                .where(stu.editableYn.eq(1)
                        ,stu.startedAt.isNotNull()
                        ,stu.endedAt.isNotNull())
                .orderBy(stu.endedAt.asc());
        return query.fetch();
    }

    public List<AdminStudentSubjectCategoryListRes> subjectCategoryList(AdminStudentCategoryDto dto, Pageable pageable) {
        JPAQuery<AdminStudentSubjectCategoryListRes> query = jpaQueryFactory.select(Projections.bean(
                        AdminStudentSubjectCategoryListRes.class, cas.iclassification, cas.classification, cos.icourseSubject, cos.subjectName, cos.round, cos.startedAt, cos.endedAt))
                .from(cos)
                .leftJoin(cos.categorySubjectEntity, cas)
                .leftJoin(cos.scsList, scs)
                .where(eqIclassification(dto.getIclassfication()),
                        eqIcourseSubject(dto.getIcourseSubject()),
                        scs.studentEntity.isNotNull())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(cos.icourseSubject.desc())
                .groupBy(cos.icourseSubject);
        return query.fetch();
    }

    public List<AdminStudentSubjectDropBox> selSubjectDropBox(AdminStudentSubjectDropBoxDto dto) {
        JPAQuery<AdminStudentSubjectDropBox> query = jpaQueryFactory
                .select(Projections.bean(AdminStudentSubjectDropBox.class, cos.icourseSubject, cos.subjectName, cos.round))
                .from(cos)
                .leftJoin(cos.categorySubjectEntity, cas)
                .where(cas.iclassification.eq(dto.getIclassification()));
        return query.fetch();
    }

    public Long subjectCategoryCount(AdminStudentCategoryDto dto) {
        JPAQuery<Long> query = jpaQueryFactory
                .select(cos.icourseSubject.count())
                .from(cos)
                .leftJoin(cos.categorySubjectEntity, cas)
                .where(eqIclassification(dto.getIclassfication()),
                        eqIcourseSubject(dto.getIcourseSubject()),
                        cos.scsList.isNotEmpty());
        return query.fetchOne();

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

    public Long countByResume(Long istudent) {
        JPAQuery<Long> query = jpaQueryFactory
                .select(file.file.count())
                .from(file)
                .where(file.fileCategoryEntity.iFileCategory.eq(1L)
                        .and(file.studentEntity.istudent.eq(istudent)));
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
    public AdminFileMainVo mainFile(StudentEntity student){
        return jpaQueryFactory.select(Projections.bean(AdminFileMainVo.class,file.ifile))
                .from(file)
                .where(file.mainYn.eq(1), file.studentEntity.eq(student)).fetchOne();
    }

    public Long countByThumbnail(Long istudent) {
        JPAQuery<Long> query = jpaQueryFactory
                .select(file.file.count())
                .from(file)
                .where(file.fileCategoryEntity.iFileCategory.eq(4L),
                        file.studentEntity.istudent.eq(istudent));
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

    public List<AdminStudentOneYearVo> oneYearStudent(Pageable pageable,Long iclassification,Long icourseSubject,String studentName){

        //1년전 날짜 계산
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);

        return jpaQueryFactory.select(Projections.bean(AdminStudentOneYearVo.class,
                        cas.classification,
                        cos.subjectName,
                        cos.round,
                        cos.startedAt,
                        cos.endedAt,
                        stu.istudent,
                        stu.name.as("studentName"),
                        stu.gender, stu.storageYn
                )).from(stu)
                .innerJoin(scs)
                .on(scs.studentEntity.istudent.eq(stu.istudent))
                .innerJoin(cos)
                .on(cos.icourseSubject.eq(scs.courseSubjectEntity.icourseSubject))
                .innerJoin(cas)
                .on(cas.iclassification.eq(cos.categorySubjectEntity.iclassification))
                .where(eqIclassification(iclassification))
                .where(eqIcourseSubject(icourseSubject))
                .where(eqStudentName(studentName))
                .where(cos.endedAt.before(oneYearAgo))
                .orderBy(getorder(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }


    public long oneYearStudentCount(Long iclassification,Long icourseSubject,String studentName){
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
       return jpaQueryFactory.select(stu.istudent.count()).from(stu)
                .innerJoin(scs)
                .on(scs.studentEntity.istudent.eq(stu.istudent))
                .innerJoin(cos)
                .on(cos.icourseSubject.eq(scs.courseSubjectEntity.icourseSubject))
                .innerJoin(cas)
                .on(cas.iclassification.eq(cos.categorySubjectEntity.iclassification))
                .where(eqIclassification(iclassification))
                .where(eqIcourseSubject(icourseSubject))
                .where(eqStudentName(studentName))
                .where(cos.endedAt.before(oneYearAgo))
                .fetchOne();
    }

    private BooleanExpression eqIcourseSubject(Long icourseSubject) {
        return icourseSubject != null ? cos.icourseSubject.eq(icourseSubject) : null;
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

    private OrderSpecifier[] getorder(Pageable pageable) {
        List<OrderSpecifier> orders = new LinkedList();
        if(!pageable.getSort().isEmpty()) {
            for(Sort.Order order : pageable.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;

                switch(order.getProperty().toLowerCase()) {
                    case "istudent": orders.add(new OrderSpecifier(direction,stu.istudent)); break;

                }
            }
        }
        return orders.stream().toArray(OrderSpecifier[]::new);
    }


}

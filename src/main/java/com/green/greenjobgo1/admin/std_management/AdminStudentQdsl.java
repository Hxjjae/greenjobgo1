package com.green.greenjobgo1.admin.std_management;

import com.green.greenjobgo1.admin.category.model.AdminCategoryDto;
import com.green.greenjobgo1.admin.std_management.model.AdminStorageStudentDto;
import com.green.greenjobgo1.admin.std_management.model.AdminStorageStudentRes;
import com.green.greenjobgo1.admin.std_management.model.AdminStudentDto;
import com.green.greenjobgo1.admin.std_management.model.AdminStudentRes;
import com.green.greenjobgo1.admin.subject.model.AdminSubjectDto;
import com.green.greenjobgo1.config.entity.*;
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
public class AdminStudentQdsl {

    private final JPAQueryFactory jpaQueryFactory;

    QCategorySubjectEntity cas = QCategorySubjectEntity.categorySubjectEntity;
    QCourseSubjectEntity cos = QCourseSubjectEntity.courseSubjectEntity;
    QStudentCourseSubjectEntity scs = QStudentCourseSubjectEntity.studentCourseSubjectEntity;
    QStudentEntity stu = QStudentEntity.studentEntity;
    QFileEntity file = QFileEntity.fileEntity;
    QCertificateEntity certificate = QCertificateEntity.certificateEntity;

    public List<AdminStudentRes> studentVos(AdminStudentDto dto, Pageable pageable) {

        JPAQuery<AdminStudentRes> query = jpaQueryFactory
                .select(Projections.bean(AdminStudentRes.class, stu.istudent, cas.classification, cos.subjectName,
                        cos.startedAt, cos.endedAt, stu.name, stu.gender, stu.address, stu.mobileNumber
                        , stu.education))
                .from(scs)
                .join(scs.studentEntity, stu)
                .join(scs.courseSubjectEntity, cos)
//                .join(stu.files, file)
//                .join(stu.certificates, certificate)
                .join(cos.categorySubjectEntity, cas)
                .where(eqIclassification(dto.getIcategory()),
                        eqSubjectName(dto.getSubjectName())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(stu.istudent.asc());
        return query.fetch();
    }

    public List<AdminStorageStudentRes> storageVos(Pageable pageable) {
        JPAQuery<AdminStorageStudentRes> query = jpaQueryFactory
                .select(Projections.bean(AdminStorageStudentRes.class
                        , stu.istudent, stu.name.as("studentName"), cos.subjectName, file.ifile, file.file))
                .from(stu)
                .join(stu.files, file)
                .join(stu.scsList, scs)
                .join(scs.courseSubjectEntity, cos)
                .where(stu.storageYn.eq(1))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(stu.istudent.asc());
        return query.fetch();
    }

    public Long stdIdx(AdminStudentDto dto) {
        JPAQuery<Long> query = jpaQueryFactory.select(stu.istudent.count())
                .from(scs)
                .join(scs.studentEntity, stu)
                .join(scs.courseSubjectEntity, cos)
                .join(cos.categorySubjectEntity, cas)
                .where(eqIclassification(dto.getIcategory())
                        ,eqSubjectName(dto.getSubjectName()));
        return query.fetchOne();
    }

    public Long storageIdx() {
        JPAQuery<Long> query = jpaQueryFactory.select(stu.istudent.count())
                .from(stu)
                .join(stu.files, file)
                .join(stu.scsList, scs)
                .join(scs.courseSubjectEntity, cos)
                .where(stu.storageYn.eq(1));
        return query.fetchOne();
    }

    private BooleanExpression eqIclassification(Long iclassification) {
        return iclassification != null ? cas.iclassification.eq(iclassification) : null;
    }

    private BooleanExpression eqSubjectName(String subjectName) {
        return subjectName != null ? cos.subjectName.contains(subjectName) : null;
    }
}

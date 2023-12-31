package com.green.greenjobgo1.admin.student;

import com.green.greenjobgo1.admin.student.model.AdminStorageStudentRes;
import com.green.greenjobgo1.admin.student.model.AdminStudentDto;
import com.green.greenjobgo1.admin.student.model.AdminStudentRes;
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
                        , stu.education, certificate.certificate, file.file))
                .from(scs)
                .join(scs.studentEntity, stu)
                .join(scs.courseSubjectEntity, cos)
                .join(stu.files, file)
                .join(stu.certificates, certificate)
                .join(cos.categorySubjectEntity, cas)
                .where(eqIclassification(dto.getIcategory()),
                        eqSubjectName(dto.getSubjectName())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(stu.istudent.desc());
        return query.fetch();
    }

    public List<AdminStorageStudentRes> storageVos(Pageable pageable) {
        JPAQuery<AdminStorageStudentRes> query = jpaQueryFactory
                .select(Projections.bean(AdminStorageStudentRes.class
                        , file.ifile, file.file, stu.istudent, stu.name.as("studentName"), cos.subjectName))
                .from(stu)
                .join(stu.files, file)
                .join(stu.scsList, scs)
                .join(scs.courseSubjectEntity, cos)
                .where(stu.storageYn.eq(1))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(stu.istudent.desc());
        return query.fetch();
    }

    private BooleanExpression eqIclassification(Long iclassification) {
        return iclassification != null ? cas.iclassification.eq(iclassification) : null;
    }

    private BooleanExpression eqSubjectName(String subjectName) {
        return subjectName != null ? cos.subjectName.contains(subjectName) : null;
    }
}

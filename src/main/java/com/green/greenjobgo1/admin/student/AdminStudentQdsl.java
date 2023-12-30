package com.green.greenjobgo1.admin.student;

import com.green.greenjobgo1.admin.student.model.AdminStudentRes;
import com.green.greenjobgo1.config.entity.*;
import com.querydsl.core.types.Projections;
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

    public List<AdminStudentRes> studentVos(Pageable pageable) {
        JPAQuery<AdminStudentRes> query = jpaQueryFactory
                .select(Projections.bean(AdminStudentRes.class, stu.istudent, cas.classification, cos.subjectName,
                        cos.startedAt, cos.endedAt, stu.name, stu.gender, stu.address, stu.mobileNumber, stu.education, stu.files))
                .from(stu)
                .join()
    }


}

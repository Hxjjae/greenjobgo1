package com.green.greenjobgo1.student;

import com.green.greenjobgo1.admin.std_management.model.AdminStudentDto;
import com.green.greenjobgo1.config.entity.QCourseSubjectEntity;
import com.green.greenjobgo1.config.entity.QStudentCourseSubjectEntity;
import com.green.greenjobgo1.config.entity.QStudentEntity;
import com.green.greenjobgo1.student.model.StudentSelRes;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentQdsl {

    private final JPAQueryFactory jpaQueryFactory;

    QStudentEntity stu = QStudentEntity.studentEntity;
    QCourseSubjectEntity cos = QCourseSubjectEntity.courseSubjectEntity;
    QStudentCourseSubjectEntity scs = QStudentCourseSubjectEntity.studentCourseSubjectEntity;

    public StudentSelRes studentVo(Long istudent) {
        JPAQuery<StudentSelRes> query = jpaQueryFactory
                .select(Projections.bean(StudentSelRes.class, stu.istudent, stu.name, cos.subjectName
                        , cos.startedAt, cos.endedAt, stu.address, stu.mobileNumber, stu.id, stu.education))
                .from(stu)
                .join(stu.scsList, scs)
                .join(scs.courseSubjectEntity, cos)
                .where(stu.istudent.eq(istudent));
        return query.fetchOne();
    }

}

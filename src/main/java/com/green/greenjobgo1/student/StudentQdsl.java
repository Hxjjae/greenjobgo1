package com.green.greenjobgo1.student;

import com.green.greenjobgo1.admin.std_management.model.AdminStudentDto;
import com.green.greenjobgo1.common.entity.*;
import com.green.greenjobgo1.student.model.StudentSelRes;
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

    public StudentSelRes studentVo(Long istudent) {
        JPAQuery<StudentSelRes> query = jpaQueryFactory
                .select(Projections.bean(StudentSelRes.class, stu.istudent, stu.name, cos.subjectName
                        , cos.startedAt, cos.endedAt, stu.address, stu.mobileNumber, stu.id, stu.education, stu.certificates))
                .from(stu)
                .join(stu.scsList, scs)
                .join(scs.courseSubjectEntity, cos)
                .where(stu.istudent.eq(istudent));
        return query.fetchOne();
    }

    public Long countByFileCategoryEntityIFileCategoryInAndStudentEntityIstudent(List<Long> fileCategoryIds, Long studentId) {
        JPAQuery<Long> query = jpaQueryFactory
                .select(file.file.count())
                .from(file)
                .where(file.fileCategoryEntity.iFileCategory.in(fileCategoryIds)
                        .and(file.studentEntity.istudent.eq(studentId)));
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

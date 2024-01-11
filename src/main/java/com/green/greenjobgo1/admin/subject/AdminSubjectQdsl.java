package com.green.greenjobgo1.admin.subject;

import com.green.greenjobgo1.admin.category.model.AdminCategoryDto;
import com.green.greenjobgo1.admin.subject.model.*;
import com.green.greenjobgo1.common.entity.QCategorySubjectEntity;
import com.green.greenjobgo1.common.entity.QCourseSubjectEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor

public class AdminSubjectQdsl {

    private final JPAQueryFactory jpaQueryFactory;

    QCourseSubjectEntity cos = QCourseSubjectEntity.courseSubjectEntity;
    QCategorySubjectEntity cas = QCategorySubjectEntity.categorySubjectEntity;

    public List<AdminSubjectRes> subjectVos(AdminSubjectDto dto, AdminCategoryDto categoryDto, Pageable pageable) {
        JPAQuery<AdminSubjectRes> query = jpaQueryFactory.select(Projections.bean(AdminSubjectRes.class
                        , cos.icourseSubject, cos.subjectName.as("courseSubjectName")
                        , cas.iclassification, cas.classification, cos.instructor, cos.lectureRoom
                        , cos.startedAt, cos.endedAt, cos.subjectCondition, cos.delYn,
                        cos.round, cos.classTime))
                .from(cos)
                .where(eqDelYn(dto.getDelYn())
                        , eqIclassification(categoryDto.getIclassification())
                        , eqCondition(dto.getSubjectCondition())
                        , eqSubjectName(dto.getSubjectName())
                )
                .join(cos.categorySubjectEntity, cas)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(cos.icourseSubject.desc());
        return query.fetch();
    }

    public Long updSubject(AdminSubjectUpdDto dto) {
        JPAUpdateClause query = jpaQueryFactory.update(cos)
                .set(cos.icourseSubject, dto.getIcourseSubject());
        if (dto.getCourseSubjectName() != null) {
            query.set(cos.subjectName, dto.getCourseSubjectName());
        }
        if (dto.getIclassification() != null) {
            query.set(cos.categorySubjectEntity.iclassification, dto.getIclassification());
        }
        if (dto.getInstructor() != null) {
            query.set(cos.instructor, dto.getInstructor());
        }
        if (dto.getLectureRoom() != null) {
            query.set(cos.lectureRoom, dto.getLectureRoom());
        }
        if (dto.getStartedAt() != null) {
            query.set(cos.startedAt, dto.getStartedAt());
        }
        if (dto.getEndedAt() != null) {
            query.set(cos.endedAt, dto.getEndedAt());
        }
        if (dto.getRound() != null) {
            query.set(cos.round, dto.getRound());
        }
        if (dto.getClassTime() != null) {
            query.set(cos.classTime, dto.getClassTime());
        }
        return query.execute();
    }


    public Long selIdx(AdminCategoryDto dto, AdminSubjectDto subjectDto) {
        JPAQuery<Long> query = jpaQueryFactory.select(cos.icourseSubject.count())
                .from(cos)
                .join(cos.categorySubjectEntity, cas)
                .where(eqIclassification(dto.getIclassification())
                        , eqSubjectName(subjectDto.getSubjectName()),
                        cos.delYn.eq(0));
        return query.fetchOne();
    }

    private BooleanExpression eqIclassification(Long iclassification) {
        return iclassification != null ? cas.iclassification.eq(iclassification) : null;
    }

    private BooleanExpression eqCondition(Integer condition) {
        return condition != null ? cos.subjectCondition.eq(condition) : null;
    }

    private BooleanExpression eqSubjectName(String subjectName) {
        return subjectName != null ? cos.subjectName.contains(subjectName) : null;
    }

    private BooleanExpression eqDelYn(Integer delYn) {
        return delYn != null ? cos.delYn.eq(delYn) : null;
    }
}

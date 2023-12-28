package com.green.greenjobgo1.admin.subject;

import com.green.greenjobgo1.admin.subject.model.*;
import com.green.greenjobgo1.config.entity.CategorySubjectEntity;
import com.green.greenjobgo1.config.entity.CourseSubjectEntity;
import com.green.greenjobgo1.config.entity.QCategorySubjectEntity;
import com.green.greenjobgo1.config.entity.QCourseSubjectEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<AdminSubjectRes> subjectVos(AdminSubjectDto dto, Pageable pageable) {
        JPAQuery<AdminSubjectRes> query = jpaQueryFactory.select(Projections.bean(AdminSubjectRes.class, cos.icourseSubject, cos.subjectName.as("courseSubjectName")
                , cos.categorySubjectEntity.classification, cos.startedAt, cos.endedAt, cos.delYn))
                .from(cos)
                .where(cos.delYn.eq(0))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(cos.icourseSubject.desc());
        return query.fetch();
    }

    private BooleanExpression eqclassification(String classification) {
        if (StringUtils.isNullOrEmpty(classification)) {
            return null;
        }
        return cas.classification.eq(classification);
    }

}

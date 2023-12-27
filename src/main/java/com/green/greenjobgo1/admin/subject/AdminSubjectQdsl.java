package com.green.greenjobgo1.admin.subject;

import com.green.greenjobgo1.admin.subject.model.AdminSubjectInsDto;
import com.green.greenjobgo1.admin.subject.model.AdminSubjectInsRes;
import com.green.greenjobgo1.admin.subject.model.AdminSubjectUpdRes;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdminSubjectQdsl {
    private final JPAQueryFactory jpaQueryFactory;

    QCourseSubjectEntity cos = QCourseSubjectEntity.courseSubjectEntity;
    QCategorySubjectEntity cas = QCategorySubjectEntity.categorySubjectEntity;

    public void updSubject(CourseSubjectEntity entity) {
        JPAUpdateClause query = jpaQueryFactory
                .update(cos).where(cos.icourseSubject.eq(entity.getIcourseSubject()));
        if (entity.getCategorySubjectEntity().getClassification() != null) {
            query.set(cos.categorySubjectEntity.classification, entity.getCategorySubjectEntity().getClassification());
        }
        if (entity.getSubjectName() != null) {
            query.set(cos.subjectName, entity.getSubjectName());
        }
        if (entity.getStartedAt() != null) {
            query.set(cos.startedAt, entity.getStartedAt());
        }
        if (entity.getEndedAt() != null) {
            query.set(cos.endedAt, entity.getEndedAt());
        }
        query.execute();

    }

    private BooleanExpression eqclassification(String classification) {
        if (StringUtils.isNullOrEmpty(classification)) {
            return null;
        }
        return cas.classification.eq(classification);
    }

}

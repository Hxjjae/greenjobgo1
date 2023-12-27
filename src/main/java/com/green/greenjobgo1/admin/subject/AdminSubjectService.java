package com.green.greenjobgo1.admin.subject;

import com.green.greenjobgo1.admin.subject.model.*;
import com.green.greenjobgo1.config.entity.CategorySubjectEntity;
import com.green.greenjobgo1.config.entity.CourseSubjectEntity;
import com.green.greenjobgo1.repository.AdminCategoryRepository;
import com.green.greenjobgo1.repository.AdminSubjectRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminSubjectService {
    private final AdminSubjectRepository AS_REP;
    private final AdminCategoryRepository AC_REP;
    private final AdminSubjectQdsl adminSubjectQdsl;

    public AdminSubjectInsRes insAdminSubject(AdminSubjectInsDto dto) {
        List<CategorySubjectEntity> categorySubjectEntities = AC_REP.findAll();
        CourseSubjectEntity courseSubjectEntity = new CourseSubjectEntity();
        for (CategorySubjectEntity categorySubjectEntity : categorySubjectEntities) {
            if (categorySubjectEntity.getClassification().equals(dto.getClassification())) {

                courseSubjectEntity.setSubjectName(dto.getCourseSubjectName());
                courseSubjectEntity.setCategorySubjectEntity(categorySubjectEntity);
                courseSubjectEntity.setStartedAt(dto.getStartedAt());
                courseSubjectEntity.setEndedAt(dto.getEndedAt());

                break;
            }
        }
        CourseSubjectEntity save = AS_REP.save(courseSubjectEntity);
        return AdminSubjectInsRes.builder()
                .icourseSubject(save.getIcourseSubject())
                .courseSubjectName(save.getSubjectName())
                .classification(save.getCategorySubjectEntity().getClassification())
                .startedAt(save.getStartedAt())
                .endedAt(save.getEndedAt())
                .build();

    }

    public List<AdminSubjectRes> selAdminSubject() {

    }

    @Transactional
    public AdminSubjectUpdRes updAdminSubject(AdminSubjectUpdDto dto) {
        Optional<CategorySubjectEntity> byCategorySubject = AC_REP.findById(dto.getIclassification());
        Optional<CourseSubjectEntity> byCourseSubject = AS_REP.findById(dto.getIcourseSubject());

        if (byCourseSubject.isPresent()) {
            if (dto.getIclassification() != null) {
                byCourseSubject.get().getCategorySubjectEntity().setIclassification(byCategorySubject.get().getIclassification());
            }
            if (dto.getCourseSubjectName() != null) {
                byCourseSubject.get().setSubjectName(dto.getCourseSubjectName());
            }
            if (dto.getStartedAt() != null) {
                byCourseSubject.get().setStartedAt(dto.getStartedAt());
            }
            if (dto.getEndedAt() != null) {
                byCourseSubject.get().setEndedAt(dto.getEndedAt());
            }

        } else {
            throw new RuntimeException("해당 아이디에 대한 데이터를 찾을 수 없습니다.");
        }
        CourseSubjectEntity save = AS_REP.save(byCourseSubject.get());
        return AdminSubjectUpdRes.builder()
                .icourseSubject(save.getIcourseSubject())
                .courseSubjectName(save.getSubjectName())
                .startedAt(save.getStartedAt())
                .endedAt(save.getEndedAt())
                .classification(save.getCategorySubjectEntity().getClassification())
                .build();

    }

    public AdminSubjectPatchRes patchAdminSubject(AdminSubjectPatchDto dto) {
        Optional<CourseSubjectEntity> byId = AS_REP.findById(dto.getIcourseSubject());

        if (byId.isPresent()) {
            CourseSubjectEntity courseSubjectEntity = byId.get();
            courseSubjectEntity.setSubjectCondition(dto.getSubjectCondition());

            CourseSubjectEntity save = AS_REP.save(courseSubjectEntity);
            return AdminSubjectPatchRes.builder()
                    .icourseSubject(save.getIcourseSubject())
                    .subjectCondition(save.getSubjectCondition())
                    .build();
        } else {
            throw new EntityNotFoundException("not found");
        }
    }

    public AdminSubjectDelRes delAdminSubject(AdminSubjectDelDto dto) {
        Optional<CourseSubjectEntity> byId = AS_REP.findById(dto.getIcourseSubject());
        if (byId.isPresent()) {
            CourseSubjectEntity courseSubjectEntity = byId.get();
            courseSubjectEntity.setDelYn(dto.getDelYn());

            CourseSubjectEntity save = AS_REP.save(courseSubjectEntity);
            return AdminSubjectDelRes.builder()
                    .icourseSubject(save.getIcourseSubject())
                    .delYn(save.getDelYn())
                    .courseSubjectName(courseSubjectEntity.getSubjectName())
                    .build();
        } else {
            throw new EntityNotFoundException("not found");
        }
    }

}
package com.green.greenjobgo1.admin.subject;

import com.green.greenjobgo1.admin.category.model.AdminCategoryVo;
import com.green.greenjobgo1.admin.subject.model.AdminSubjectInsDto;
import com.green.greenjobgo1.admin.subject.model.AdminSubjectInsRes;
import com.green.greenjobgo1.admin.subject.model.AdminSubjectUpdDto;
import com.green.greenjobgo1.admin.subject.model.AdminSubjectUpdRes;
import com.green.greenjobgo1.config.entity.CategorySubjectEntity;
import com.green.greenjobgo1.config.entity.CourseSubjectEntity;
import com.green.greenjobgo1.repository.AdminCategoryRepository;
import com.green.greenjobgo1.repository.AdminSubjectRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminSubjectService {
    private final AdminSubjectRepository AS_REP;
    private final AdminCategoryRepository AC_REP;
    private final AdminSubjectQdsl SUBJECT_QDSL;
    private final EntityManager EM;

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

    @Transactional
    public AdminSubjectUpdRes updAdminSubject(AdminSubjectUpdDto dto) {
        Optional<CategorySubjectEntity> byCategorySubject = AC_REP.findById(dto.getIclassification());
        Optional<CourseSubjectEntity> byCourseSubject = AS_REP.findById(dto.getIcourseSubject());

        if (byCourseSubject.isPresent()) {
                byCourseSubject.get().setSubjectName(dto.getCourseSubjectName());
                byCourseSubject.get().getCategorySubjectEntity().setClassification(byCategorySubject.get().getClassification());
                byCourseSubject.get().setStartedAt(dto.getStartedAt());
                byCourseSubject.get().setEndedAt(dto.getEndedAt());

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
}
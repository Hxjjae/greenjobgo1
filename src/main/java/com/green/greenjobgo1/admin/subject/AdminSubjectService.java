package com.green.greenjobgo1.admin.subject;

import com.green.greenjobgo1.admin.category.model.AdminCategoryVo;
import com.green.greenjobgo1.admin.subject.model.AdminSubjectInsDto;
import com.green.greenjobgo1.admin.subject.model.AdminSubjectInsRes;
import com.green.greenjobgo1.admin.subject.model.AdminSubjectUpdRes;
import com.green.greenjobgo1.config.entity.CategorySubjectEntity;
import com.green.greenjobgo1.config.entity.CourseSubjectEntity;
import com.green.greenjobgo1.repository.AdminCategoryRepository;
import com.green.greenjobgo1.repository.AdminSubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminSubjectService {
    private final AdminSubjectRepository AS_REP;
    private final AdminCategoryRepository AC_REP;
    private final AdminSubjectQdsl subjectQdsl;

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

    public AdminSubjectUpdRes updAdminSubject(CourseSubjectEntity entity) {
        List<CategorySubjectEntity> categorySubjectEntities = AC_REP.findAll();
        Optional<CourseSubjectEntity> byId = AS_REP.findById(entity.getIcourseSubject());

        if (byId.isPresent()) {
            for (CategorySubjectEntity categorySubjectEntity : categorySubjectEntities) {
                if (categorySubjectEntity.getClassification().equals(entity.getCategorySubjectEntity().getClassification())) {

                    byId.get().setSubjectName(entity.getSubjectName());
                    byId.get().setCategorySubjectEntity(categorySubjectEntity);
                    byId.get().setStartedAt(entity.getStartedAt());
                    byId.get().setEndedAt(entity.getEndedAt());

                    break;
                }
            }
        }
        CourseSubjectEntity save = AS_REP.save(entity);
        return AdminSubjectUpdRes.builder()
                .icourseSubject(save.getIcourseSubject())
                .courseSubjectName(save.getSubjectName())
                .startedAt(save.getStartedAt())
                .endedAt(save.getEndedAt())
                .build();
    }
}

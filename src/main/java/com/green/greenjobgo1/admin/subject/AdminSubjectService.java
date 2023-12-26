package com.green.greenjobgo1.admin.subject;

import com.green.greenjobgo1.admin.subject.model.AdminSubjectInsDto;
import com.green.greenjobgo1.admin.subject.model.AdminSubjectInsRes;
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

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminSubjectService {
    private final AdminSubjectRepository AS_REP;
    private final AdminCategoryRepository AC_REP;

    public AdminSubjectInsRes insAdminSubject(AdminSubjectInsDto dto) {
        CourseSubjectEntity entity = new CourseSubjectEntity();
        List<CategorySubjectEntity> categorySubjectEntities = AC_REP.findAll();

        for (int i = 0; i < categorySubjectEntities.size(); i++) {

            if (categorySubjectEntities.get(i).getClassification().equals(dto.getClassification())) {

                entity.setSubjectName(dto.getCourseSubjectName());
                entity.setStartedAt(dto.getStartedAt());
                entity.setEndedAt(dto.getEndedAt());

                if (entity.getCategorySubjectEntity() == null) {
                    entity.setCategorySubjectEntity(new CategorySubjectEntity());
                }

                entity.getCategorySubjectEntity().setClassification(dto.getClassification());

                break;
            }
        }
        CourseSubjectEntity save = AS_REP.save(entity);

        return AdminSubjectInsRes.builder()
                .icourseSubject(save.getIcourseSubject())
                .courseSubjectName(save.getSubjectName())
                .startedAt(save.getStartedAt())
                .endedAt(save.getEndedAt())
                .classification(save.getCategorySubjectEntity().getClassification())
                .build();
    }
}

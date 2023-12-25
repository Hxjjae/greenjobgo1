package com.green.greenjobgo1.admin.subject;

import com.green.greenjobgo1.admin.subject.model.AdminSubjectInsDto;
import com.green.greenjobgo1.admin.subject.model.AdminSubjectInsRes;
import com.green.greenjobgo1.config.entity.CategorySubjectEntity;
import com.green.greenjobgo1.config.entity.CourseSubjectEntity;
import com.green.greenjobgo1.repository.AdminCategoryRepository;
import com.green.greenjobgo1.repository.AdminSubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminSubjectService {
    private final AdminSubjectRepository AS_REP;
    private final AdminCategoryRepository AC_REP;

//    public AdminSubjectInsRes insAdminSubject(AdminSubjectInsDto dto) {
//        List<CategorySubjectEntity> csList = AC_REP.findAll();
//
//        for (CategorySubjectEntity categorySubjectEntity : csList) {
//
//            if (categorySubjectEntity.getClassification().equals(dto.getCategorySubject().getClassification())) {
//                CourseSubjectEntity entity = new CourseSubjectEntity();
//
//                entity.setSubjectName(dto.getCourseSubjectName());
//                entity.setStartedAt(dto.getStartedAt());
//                entity.setEndedAt(dto.getEndedAt());
//                entity.setCategorySubjectEntity(dto.getCategorySubject());
//
//                CourseSubjectEntity save = AS_REP.save(entity);
//
//                return AdminSubjectInsRes.builder()
//                        .icourseSubject(save.getIcourseSubject())
//                        .courseSubjectName(save.getSubjectName())
//                        .startedAt(save.getStartedAt())
//                        .endedAt(save.getEndedAt())
//                        .categorySubject(save.getCategorySubjectEntity())
//                        .build();
//
//            } else {
//                throw new RuntimeException("올바르지 않은 카테고리 입력입니다.");
//            }
//        }
//    }
}

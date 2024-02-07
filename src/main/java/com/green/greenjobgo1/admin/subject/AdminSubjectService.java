package com.green.greenjobgo1.admin.subject;

import com.green.greenjobgo1.admin.category.model.AdminCategoryDto;
import com.green.greenjobgo1.admin.subject.model.*;
import com.green.greenjobgo1.common.security.config.exception.CommonErrorCode;
import com.green.greenjobgo1.common.security.config.exception.RestApiException;
import com.green.greenjobgo1.common.utils.PagingUtils;
import com.green.greenjobgo1.common.entity.CategorySubjectEntity;
import com.green.greenjobgo1.common.entity.CourseSubjectEntity;
import com.green.greenjobgo1.repository.AdminCategoryRepository;
import com.green.greenjobgo1.repository.AdminSubjectRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collections;
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
        CourseSubjectEntity existSubject = AS_REP.findBySubjectNameAndRound(dto.getCourseSubjectName(), dto.getRound());

        if (existSubject != null) {
            throw new RestApiException(CommonErrorCode.SUBJECT_DUPLICATE);
        }

        List<CategorySubjectEntity> categorySubjectEntities = AC_REP.findAll();
        CourseSubjectEntity courseSubjectEntity = new CourseSubjectEntity();
        boolean categoryMatch = false;

        for (CategorySubjectEntity categorySubjectEntity : categorySubjectEntities) {
            if (categorySubjectEntity.getIclassification().equals(dto.getIclassification())) {
                courseSubjectEntity.setInstructor(dto.getInstructor());
                courseSubjectEntity.setLectureRoom(dto.getLectureRoom());
                courseSubjectEntity.setSubjectName(dto.getCourseSubjectName());
                courseSubjectEntity.setCategorySubjectEntity(categorySubjectEntity);
                courseSubjectEntity.setStartedAt(dto.getStartedAt());
                courseSubjectEntity.setEndedAt(dto.getEndedAt());
                courseSubjectEntity.setRound(dto.getRound());
                courseSubjectEntity.setClassTime(dto.getClassTime());

                categoryMatch = true;
                break;
            }
        }

        if (!categoryMatch) {
            throw new RestApiException(CommonErrorCode.CATEGORY_NOT_EQUALS);
        }

        CourseSubjectEntity save = AS_REP.save(courseSubjectEntity);
        return AdminSubjectInsRes.builder()
                .icourseSubject(save.getIcourseSubject())
                .courseSubjectName(save.getSubjectName())
                .iclassification(save.getCategorySubjectEntity().getIclassification())
                .startedAt(save.getStartedAt())
                .endedAt(save.getEndedAt())
                .instructor(save.getInstructor())
                .lectureRoom(save.getLectureRoom())
                .round(save.getRound())
                .classTime(save.getClassTime())
                .build();
    }

        public AdminSubjectFindRes selAdminSubject(AdminSubjectDto dto, AdminCategoryDto categoryDto, Pageable pageable) {
        long maxPage = adminSubjectQdsl.selIdx(categoryDto, dto);
        PagingUtils utils = new PagingUtils(pageable.getPageNumber() + 1, (int) maxPage, pageable);
        if (categoryDto.getIclassification() != null) {
            utils.setIdx((int) maxPage);
        } else {
            utils.setIdx((int) maxPage);
        }

        List<AdminSubjectRes> list = adminSubjectQdsl.subjectVos(dto, categoryDto, pageable);

        return AdminSubjectFindRes.builder()
                .page(utils)
                .res(list.stream().map(item -> AdminSubjectRes.builder()
                        .courseSubjectName(item.getCourseSubjectName())
                        .iclassification(item.getIclassification())
                        .classification(item.getClassification())
                        .icourseSubject(item.getIcourseSubject())
                        .startedAt(item.getStartedAt())
                        .endedAt(item.getEndedAt())
                        .lectureRoom(item.getLectureRoom())
                        .instructor(item.getInstructor())
                        .subjectCondition(item.getSubjectCondition())
                        .round(item.getRound())
                        .classTime(item.getClassTime())
                        .build()).toList())
                .build();
    }

    public AdminSubjectUpdRes updAdminSubject(AdminSubjectUpdDto dto) {
        Optional<CourseSubjectEntity> subjectId = AS_REP.findById(dto.getIcourseSubject());
        List<CategorySubjectEntity> categoryList = AC_REP.findAll();

        CourseSubjectEntity entity = new CourseSubjectEntity();
        if (subjectId.isPresent()) {
            for (CategorySubjectEntity categorySubjectEntity : categoryList) {
                if (categorySubjectEntity.getIclassification().equals(dto.getIclassification())) {
                    entity.setIcourseSubject(dto.getIcourseSubject());
                    entity.setCategorySubjectEntity(categorySubjectEntity);
                    entity.setSubjectName(dto.getCourseSubjectName());
                    entity.setStartedAt(dto.getStartedAt());
                    entity.setEndedAt(dto.getEndedAt());
                    entity.setInstructor(dto.getInstructor());
                    entity.setLectureRoom(dto.getLectureRoom());
                    entity.setRound(dto.getRound());
                    entity.setClassTime(dto.getClassTime());

                    break;
                }
            }
        } else {
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
        CourseSubjectEntity save = AS_REP.save(entity);
        return AdminSubjectUpdRes.builder()
                .icourseSubject(save.getIcourseSubject())
                .iclassification(save.getCategorySubjectEntity().getIclassification())
                .courseSubjectName(save.getSubjectName())
                .startedAt(save.getStartedAt())
                .endedAt(save.getEndedAt())
                .instructor(save.getInstructor())
                .lectureRoom(save.getLectureRoom())
                .round(save.getRound())
                .classTime(save.getClassTime())
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
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
    }

    public List<AdminSubjectDelRes> delAdminSubject(AdminSubjectDelDto dto) {
        if (dto.getIcourseSubject() != null) {
            List<CourseSubjectEntity> subjectList = AS_REP.findAllById(dto.getIcourseSubject());
            List<AdminSubjectDelRes> resultList = new ArrayList<>();

            for (CourseSubjectEntity courseSubjectEntity : subjectList) {
                courseSubjectEntity.setDelYn(1);
                CourseSubjectEntity save = AS_REP.save(courseSubjectEntity);

                AdminSubjectDelRes build = AdminSubjectDelRes.builder()
                        .icourseSubject(save.getIcourseSubject())
                        .courseSubjectName(save.getSubjectName())
                        .delYn(save.getDelYn())
                        .build();
                resultList.add(build);
            }
            return resultList;
        } else {
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
    }

}
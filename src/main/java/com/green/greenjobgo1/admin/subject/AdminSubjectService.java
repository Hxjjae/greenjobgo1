package com.green.greenjobgo1.admin.subject;

import com.green.greenjobgo1.admin.category.model.AdminCategoryDto;
import com.green.greenjobgo1.admin.subject.model.*;
import com.green.greenjobgo1.common.utils.PagingUtils;
import com.green.greenjobgo1.config.entity.CategorySubjectEntity;
import com.green.greenjobgo1.config.entity.CourseSubjectEntity;
import com.green.greenjobgo1.repository.AdminCategoryRepository;
import com.green.greenjobgo1.repository.AdminSubjectRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

                courseSubjectEntity.setInstructor(dto.getInstructor());
                courseSubjectEntity.setLectureRoom(dto.getLectureRoom());
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
                .instructor(save.getInstructor())
                .lectureRoom(save.getLectureRoom())
                .build();

    }

    public AdminSubjectFindRes selAdminSubject(AdminSubjectDto dto, AdminCategoryDto categoryDto, Pageable pageable) {
        long maxPage = adminSubjectQdsl.selIdx(categoryDto, dto);
        PagingUtils utils = new PagingUtils(pageable.getPageNumber()+1, (int)maxPage, pageable);
        if (categoryDto.getIclassification() != null) {
            utils.setIdx((int)maxPage);
        } else {
            utils.setIdx((int)maxPage);
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
                        .build()).toList())
                .build();
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
            if (dto.getInstructor() != null) {
                byCourseSubject.get().setInstructor(dto.getInstructor());
            }
            if (dto.getLectureRoom() != null) {
                byCourseSubject.get().setLectureRoom(dto.getLectureRoom());
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
                .instructor(save.getInstructor())
                .lectureRoom(save.getLectureRoom())
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
            courseSubjectEntity.setDelYn(1);

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
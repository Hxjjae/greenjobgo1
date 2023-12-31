package com.green.greenjobgo1.admin.subject;

import com.green.greenjobgo1.admin.category.model.AdminCategoryDto;
import com.green.greenjobgo1.admin.subject.model.*;
import com.green.greenjobgo1.config.entity.CategorySubjectEntity;
import com.green.greenjobgo1.config.entity.CourseSubjectEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/subject")
@Tag(name = "관리자 과목 CRUD")
public class AdminSubjectController {

    @Autowired
    private final AdminSubjectService SERVICE;

    @PostMapping
    @Operation(summary = "수강과목 추가", description = "")
    public AdminSubjectInsRes postAdminSubject(@RequestBody AdminSubjectInsDto dto) {
        return SERVICE.insAdminSubject(dto);
    }

    @GetMapping
    @Operation(summary = "수강과목 보기", description = "iclassficiation = 대분류 pk\n" +
                                        "subjectName = 과정 이름\n" +
                                        "condition = 과정 상태\n" +
                                        "delYn = 삭제 여부\n" +
                                        "\nResponses : " +
            "\npage = 현재 페이지\n" +
            "\nmaxPage = 최대 페이지\n" +
            "\nrow = 행 갯수(10고정)\n" +
            "\nicourseSubject = 과정 pk\n" +
            "\niclassfication = 대분류 pk\n" +
            "\ncourseSubjectName = 과정 이름\n" +
            "\nstartedAt = 시작일자\n" +
            "\nendedAt = 종료일자\n" +
            "\ninstructor = 강사명\n" +
            "\nlectureRoom = 강의실\n" +
            "\ndelYn = 삭제 여부\n")
    public ResponseEntity<AdminSubjectFindRes> getAdminSubject(@ParameterObject @PageableDefault(sort = "icourseSubject", direction = Sort.Direction.ASC) Pageable pageable,
                                                               @RequestParam(required = false) Long iclassification,
                                                               @RequestParam(required = false) String subjectName,
                                                               @RequestParam(required = false, defaultValue = "0") Integer condition,
                                                               @RequestParam(required = false, defaultValue = "0") Integer delYn) {
        AdminSubjectDto subjectDto = new AdminSubjectDto();
        AdminCategoryDto categoryDto = new AdminCategoryDto();
        categoryDto.setIclassification(iclassification);
        subjectDto.setSubjectName(subjectName);
        subjectDto.setDelYn(delYn);
        subjectDto.setSubjectCondition(condition);
        subjectDto.setPage(pageable.getPageNumber());
        subjectDto.setSize(pageable.getPageSize());
        return SERVICE.selAdminSubject(subjectDto, categoryDto,pageable);
    }

    @PutMapping
    @Operation(summary = "수강과목 수정", description = "\nicourseSubject = 과정 pk\n" +
                            "\niclassfication = 대분류 pk\n" +
                        "\ncourseSubjectName = 과정 이름\n" +
                        "\nstartedAt = 시작일자\n" +
                        "\nendedAt = 종료일자\n")

    public AdminSubjectUpdRes putAdminSubject(@RequestParam Long icourseSubject,
                                              @RequestParam(required = false) Long iclassification,
                                              @RequestParam(required = false) String courseSubjectName,
                                              @RequestParam(required = false) LocalDate startedAt,
                                              @RequestParam(required = false) LocalDate endedAt) {
        AdminSubjectUpdDto dto = new AdminSubjectUpdDto();
        dto.setIcourseSubject(icourseSubject);
        dto.setIclassification(iclassification);
        dto.setCourseSubjectName(courseSubjectName);
        dto.setEndedAt(endedAt);
        dto.setStartedAt(startedAt);
        return SERVICE.updAdminSubject(dto);
    }

    @PatchMapping
    @Operation(summary = "수강과목 상태 변경", description = "icourseSubject = 과정 pk\n" +
            "\ncondition = 과정 상태 (0 = 시작전 , 1 = 진행중, 2 = 종료)\n")
    public AdminSubjectPatchRes patchAdminSubject(@RequestParam Long icourseSubject,
                                                  @RequestParam int condition) {
        AdminSubjectPatchDto dto = new AdminSubjectPatchDto();
        dto.setIcourseSubject(icourseSubject);
        dto.setSubjectCondition(condition);
        return SERVICE.patchAdminSubject(dto);
    }

    @DeleteMapping
    @Operation(summary = "수강과목 삭제", description = "")
    public AdminSubjectDelRes delAdminSubject(@RequestParam Long icourseSubject) {
        AdminSubjectDelDto dto = new AdminSubjectDelDto();
        dto.setIcourseSubject(icourseSubject);
        return SERVICE.delAdminSubject(dto);
    }
}

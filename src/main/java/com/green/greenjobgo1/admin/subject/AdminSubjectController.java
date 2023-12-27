package com.green.greenjobgo1.admin.subject;

import com.green.greenjobgo1.admin.subject.model.*;
import com.green.greenjobgo1.config.entity.CategorySubjectEntity;
import com.green.greenjobgo1.config.entity.CourseSubjectEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Operation(summary = "수강과목 보기", description = "")
    public List<AdminSubjectRes> getAdminSubject() {
        return SERVICE.selAdminSubject();
    }

    @PutMapping
    @Operation(summary = "수강과목 수정", description = "")

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
    @Operation(summary = "수강과목 상태 변경", description = "")
    public AdminSubjectPatchRes patchAdminSubject(@RequestParam Long icourseSubject,
                                                @RequestParam int condition) {
        AdminSubjectPatchDto dto = new AdminSubjectPatchDto();
        dto.setIcourseSubject(icourseSubject);
        dto.setSubjectCondition(condition);
        return SERVICE.patchAdminSubject(dto);
    }
}

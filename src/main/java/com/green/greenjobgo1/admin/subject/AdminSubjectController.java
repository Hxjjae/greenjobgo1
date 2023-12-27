package com.green.greenjobgo1.admin.subject;

import com.green.greenjobgo1.admin.subject.model.AdminSubjectInsDto;
import com.green.greenjobgo1.admin.subject.model.AdminSubjectInsRes;
import com.green.greenjobgo1.admin.subject.model.AdminSubjectUpdDto;
import com.green.greenjobgo1.admin.subject.model.AdminSubjectUpdRes;
import com.green.greenjobgo1.config.entity.CategorySubjectEntity;
import com.green.greenjobgo1.config.entity.CourseSubjectEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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


}

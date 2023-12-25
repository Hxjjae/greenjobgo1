package com.green.greenjobgo1.admin.subject;

import com.green.greenjobgo1.admin.subject.model.AdminSubjectInsDto;
import com.green.greenjobgo1.admin.subject.model.AdminSubjectInsRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

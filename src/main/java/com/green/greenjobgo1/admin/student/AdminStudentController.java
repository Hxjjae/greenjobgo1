package com.green.greenjobgo1.admin.student;

import com.green.greenjobgo1.admin.student.model.AdminStudentDto;
import com.green.greenjobgo1.admin.student.model.AdminStudentFindRes;
import com.green.greenjobgo1.admin.student.model.AdminStudentRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin-student")
@Tag(name = "관리자 학생관리")
public class AdminStudentController {

    private final AdminStudentService SERVICE;

    @GetMapping
    @Operation(summary = "학생 조회")
    public ResponseEntity<AdminStudentFindRes> getStudentList(@ParameterObject @PageableDefault(sort = "icourseSubject", direction = Sort.Direction.ASC) Pageable pageable,
                                                              @RequestParam(required = false) Long icategory,
                                                              @RequestParam(required = false) String subjectName
    ) {
        AdminStudentDto dto = new AdminStudentDto();
            dto.setIcategory(icategory);
            dto.setSubjectName(subjectName);
        return SERVICE.selStudentList(dto, pageable);
    }
}

package com.green.greenjobgo1.admin.std_management;

import com.green.greenjobgo1.admin.std_management.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/detail")
    @Operation(summary = "학생 상세 조회")
    public AdminStudentDetailRes getStudentDetail(@RequestParam Long istudent) {
        AdminStudentDetailDto dto = new AdminStudentDetailDto();
        dto.setIstudent(istudent);
        return SERVICE.selStudentDetail(dto);
    }

    @GetMapping("/storage")
    @Operation(summary = "보관함 학생 조회")
    public ResponseEntity<AdminStorageStudentFindRes> getStorage(@ParameterObject @PageableDefault(sort = "istudent", direction = Sort.Direction.ASC, page = 1) Pageable pageable) {
        AdminStorageStudentDto dto = new AdminStorageStudentDto();
        dto.setPage(pageable.getPageNumber());
        dto.setSize(pageable.getPageSize());
        return SERVICE.selStorage(dto, pageable);
    }

    @PatchMapping("/storage")
    @Operation(summary = "보관 여부 결정")
    public AdminStorageStudentPatchRes patchStorage(@RequestParam Long istudent) {
        AdminStorageStudentPatchDto dto = new AdminStorageStudentPatchDto();
        dto.setIstudent(istudent);
        return SERVICE.patchStorage(dto);
    }
}

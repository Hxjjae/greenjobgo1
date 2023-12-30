package com.green.greenjobgo1.admin.student;

import com.green.greenjobgo1.admin.student.model.AdminStudentRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin-student")
@Tag(name = "관리자 학생관리")
public class AdminStudentController {

    private final AdminStudentService SERVICE;

    @GetMapping
    @Operation(summary = "학생 전체 조회")
    public AdminStudentRes getStudentList() {
        return SERVICE.selStudentList();
    }
}

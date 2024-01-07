package com.green.greenjobgo1.student;

import com.green.greenjobgo1.student.model.StudentInsDto;
import com.green.greenjobgo1.student.model.StudentInsRes;
import com.green.greenjobgo1.student.model.StudentSelDto;
import com.green.greenjobgo1.student.model.StudentSelRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
@Tag(name = "수강생 CRUD")
public class StudentController {

    private final StudentService SERVICE;
    @GetMapping
    @Operation(summary = "수강생 기본정보 보기")
    public StudentSelRes getStudent(@RequestParam Long istudent) {
        StudentSelDto dto = new StudentSelDto();
        dto.setIstudent(istudent);
        return SERVICE.selStudent(dto);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "수강생 파일 및 링크 업로드")
    public StudentInsRes postResume(@RequestPart(required = false) MultipartFile file,
                                    @RequestParam Long istudent,
                                    @RequestParam Long iFileCategory,
                                    @RequestParam(required = false) String fileLink) {
        StudentInsDto dto = new StudentInsDto();
        dto.setIstudent(istudent);
        dto.setIFileCategory(iFileCategory);
        dto.setFileLink(fileLink);
        return SERVICE.insFile(file, dto);
    }
}

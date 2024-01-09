package com.green.greenjobgo1.student;

import com.green.greenjobgo1.student.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, path = "/file")
    @Operation(summary = "수강생 파일 및 링크 업로드")
    public StudentInsTotalRes postFile(@RequestPart(required = false) MultipartFile file,
                                       @RequestParam Long istudent,
                                       @RequestParam Long iFileCategory,
                                       @RequestParam(required = false) String introducedLine,
                                       @RequestParam(required = false) String fileLink) {
        StudentInsDto dto = new StudentInsDto();
        StudentCertificateDto certDto = new StudentCertificateDto();
        dto.setIstudent(istudent);
        dto.setIFileCategory(iFileCategory);
        dto.setFileLink(fileLink);
        dto.setIntroducedLine(introducedLine);
        return SERVICE.insFile(file, dto);
    }

    @PostMapping("/certificate")
    @Operation(summary = "수강생 자격증 추가")
    public StudentCertificateRes postCertificate(@RequestParam Long istudent,
                                          @RequestParam List<String> certificates) {
        StudentCertificateDto dto = new StudentCertificateDto();
        dto.setIstudent(istudent);
        dto.setCertificates(certificates);
        return SERVICE.insCertificates(dto);
    }

    @PatchMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, path = "/file")
    @Operation(summary = "수강생 파일 수정")
    public StudentPatchTotalRes patchFile(@RequestPart(required = false) MultipartFile file,
                                          @RequestParam Long istudent,
                                          @RequestParam Long iFileCategory,
                                          @RequestParam(required = false) String introducedLine,
                                          @RequestParam(required = false) String fileLink) {
        StudentPatchDto dto = new StudentPatchDto();
        dto.setIstudent(istudent);
        dto.setIFileCategory(iFileCategory);
        dto.setFileLink(fileLink);
        dto.setIntroducedLine(introducedLine);
        return SERVICE.patchFile(file, dto);
    }

    @DeleteMapping
    @Operation(summary = "수강생 파일 삭제")
    public StudentDelRes delFile(@RequestParam Long istudent,
                                 @RequestParam Long ifile) {
        StudentDelDto dto = new StudentDelDto();
        dto.setIfile(ifile);
        dto.setIstudent(istudent);
        return SERVICE.delFile(dto);
    }
}

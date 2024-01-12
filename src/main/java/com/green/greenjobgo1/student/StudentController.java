package com.green.greenjobgo1.student;

import com.green.greenjobgo1.common.entity.StudentEntity;
import com.green.greenjobgo1.repository.StudentRepository;
import com.green.greenjobgo1.student.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
@Tag(name = "수강생 CRUD")
public class StudentController {

    private final StudentService SERVICE;
    private final StudentRepository STU_REP;

    @GetMapping
    @Operation(summary = "수강생 기본정보 보기")
    public ResponseEntity<StudentSelTotalRes> getStudent(@RequestParam Long istudent) {
        Optional<StudentEntity> stdId = STU_REP.findById(istudent);
        if (stdId.get().getEditableYn() == 1) {
            StudentSelDto dto = new StudentSelDto();
            dto.setIstudent(istudent);
            return ResponseEntity.ok(SERVICE.selStudent(dto));
        } else {
            throw new RuntimeException("editableYn이 비활성화 되어있습니다.");
        }
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, path = "/file")
    @Operation(summary = "수강생 파일 및 링크 업로드")
    @PreAuthorize("hasAnyRole('USER')")
    public StudentInsTotalRes postFile(@RequestPart(required = false) MultipartFile file,
                                       @RequestParam Long istudent,
                                       @RequestParam Long iFileCategory,
                                       @RequestParam(required = false) String introducedLine,
                                       @RequestParam(required = false) String fileLink) {
        Optional<StudentEntity> stdId = STU_REP.findById(istudent);
        if (stdId.get().getEditableYn() == 1) {
            StudentInsDto dto = new StudentInsDto();
            StudentCertificateDto certDto = new StudentCertificateDto();
            dto.setIstudent(istudent);
            dto.setIFileCategory(iFileCategory);
            dto.setFileLink(fileLink);
            dto.setIntroducedLine(introducedLine);
            return SERVICE.insFile(file, dto);
        } else {
            throw new RuntimeException("editableYn이 비활성화 되어있습니다.");
        }
    }

    @PostMapping("/certificate")
    @Operation(summary = "수강생 자격증 추가")
    @PreAuthorize("hasAnyRole('USER')")
    public StudentCertificateRes postCertificate(@RequestParam Long istudent,
                                          @RequestParam List<String> certificates) {
        Optional<StudentEntity> stdId = STU_REP.findById(istudent);
        if (stdId.get().getEditableYn() == 1) {
            StudentCertificateDto dto = new StudentCertificateDto();
            dto.setIstudent(istudent);
            dto.setCertificates(certificates);
            return SERVICE.insCertificates(dto);
        } else {
            throw new RuntimeException("editableYn이 비활성화 되어있습니다.");
        }
    }

    @PatchMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, path = "/file")
    @Operation(summary = "수강생 파일 수정")
    @PreAuthorize("hasAnyRole('USER')")
    public StudentPatchTotalRes patchFile(@RequestPart(required = false) MultipartFile file,
                                          @RequestParam Long istudent,
                                          @RequestParam Long iFileCategory,
                                          @RequestParam Long ifile,
                                          @RequestParam(required = false) String introducedLine,
                                          @RequestParam(required = false) String fileLink) {
        Optional<StudentEntity> stdId = STU_REP.findById(istudent);
        if (stdId.get().getEditableYn() == 1) {
            StudentPatchDto dto = new StudentPatchDto();
            dto.setIstudent(istudent);
            dto.setIFileCategory(iFileCategory);
            dto.setIfile(ifile);
            dto.setFileLink(fileLink);
            dto.setIntroducedLine(introducedLine);
            return SERVICE.patchFile(file, dto);
        } else {
            throw new RuntimeException("editableYn이 비활성화 되어있습니다.");
        }
    }

    @DeleteMapping("/file")
    @Operation(summary = "수강생 파일 삭제")
    @PreAuthorize("hasAnyRole('USER')")
    public StudentDelRes delFile(@RequestParam Long istudent,
                                 @RequestParam Long ifile) {
        Optional<StudentEntity> stdId = STU_REP.findById(istudent);
        if (stdId.get().getEditableYn() == 1) {
            StudentDelDto dto = new StudentDelDto();
            dto.setIfile(ifile);
            dto.setIstudent(istudent);
            return SERVICE.delFile(dto);
        } else {
            throw new RuntimeException("editableYn이 비활성화 되어있습니다.");
        }
    }

    @GetMapping("/company")
    @Operation(summary = "회사 리스트 조회",
            description = "companyCode: DB 회사 pk값 <br>" +
                    "area: 지역 <br>"+
                    "leaderName: 대표 이름 <br>"+
                    "companyName: 회사 명 <br>"+
                    "leaderName: 대표 이름 <br>"+
                    "homepage: 홈페이지링크 <br>"+
                    "manager : 담당자 이름<br>"+
                    "phonenumber : 전화번호 <br>"+
                    "dateConslusion: 체결일자 <br>")
    public ResponseEntity<StudentCompanyListVo> companyList(@ParameterObject @PageableDefault(direction = Sort.Direction.ASC, page = 1) Pageable pageable,
                                                     @RequestParam(required = false) String companyName){
        return ResponseEntity.ok(SERVICE.companyList(companyName, pageable));
    }


    private boolean isEditableYnAllowed() {
        // Implement your logic to check if editableYn is allowed for the current user
        // You may load user details from the security context or perform custom checks
        // This is just a placeholder; replace it with your actual logic
        return true;
    }
}

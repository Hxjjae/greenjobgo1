package com.green.greenjobgo1.admin.std_management;

import com.green.greenjobgo1.admin.std_management.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/student")
@Tag(name = "관리자 학생관리")
public class AdminStudentController {

    private final AdminStudentService SERVICE;

    @GetMapping
    @Operation(summary = "학생 조회", description = "istudnet : 수강생 PK \n" +
            "\n classfication : 카테고리 \n" +
            "\n subjectName : 수강과목 명 \n" +
            "\n startedAt : 강의시작일자\n" +
            "\n endedAt : 강의종료일자\n" +
            "\n name : 수강생 이름 \n" +
            "\n gender : 수강생 성별\n" +
            "\n address : 지역\n" +
            "\n mobileNumber : 휴대폰 번호 \n" +
            "\n certificate : 자격증 \n" +
            "\n file : 이력서 + 포트폴리오 ")
    public ResponseEntity<AdminStudentFindRes> getStudentList(@ParameterObject @PageableDefault(sort = "istudent", direction = Sort.Direction.ASC, page = 1) Pageable pageable,
                                                              @RequestParam(required = false) Long icategory,
                                                              @RequestParam(required = false) String subjectName
    ) {
        AdminStudentDto dto = new AdminStudentDto();
        dto.setIcategory(icategory);
        dto.setSubjectName(subjectName);
        return SERVICE.selStudentList(dto, pageable);
    }

    @GetMapping("/detail")
    @Operation(summary = "학생 상세 조회", description = "name : 수강생 이름 \n" +
            "\n birthday : 생일 \n" +
            "\n address : 주소 \n" +
            "\n addressDetail : 상세주소\n" +
            "\n email : 이메일 \n" +
            "\n startedAt : 권한시작일자\n" +
            "\n endedAt : 권한만료일자 \n" +
            "\n mobileNumber : 휴대폰 번호 \n" +
            "\n education : 학력 ")
    public AdminStudentDetailRes getStudentDetail(@RequestParam Long istudent) {
        AdminStudentDetailDto dto = new AdminStudentDetailDto();
        dto.setIstudent(istudent);
        return SERVICE.selStudentDetail(dto);
    }

    @GetMapping("/portfolio")
    @Operation(summary = "포트폴리오 조회", description = "img : 포트폴리오 대표 이미지 \n" +
            "\n istudent : 수강생 PK \n" +
            "\n studentName : 수강생 이름 \n" +
            "\n subjectname : 수강과목 이름")
    public ResponseEntity<AdminPortfolioFindRes> getPortfolio(@ParameterObject @PageableDefault(page = 1)
                                                              @SortDefault.SortDefaults({
                                                                      @SortDefault(sort = "storageYn", direction = Sort.Direction.DESC),
                                                                      @SortDefault(sort = "istudent", direction = Sort.Direction.ASC)
                                                              }) Pageable pageable,
                                                              @RequestParam(required = false) Long iclassfication,
                                                              @RequestParam(required = false) String studentName,
                                                              @RequestParam(required = false) String subjectName) {
        AdminPortfolioDto dto = new AdminPortfolioDto();
        dto.setIclassfication(iclassfication);
        dto.setStudentName(studentName);
        dto.setSubjectName(subjectName);

        return SERVICE.selPortfolio(dto, pageable);
    }

    @GetMapping("/storage")
    @Operation(summary = "보관함 리스트 조회" ,description = "img : 포트폴리오 대표 이미지 \n" +
            "\n istudent : 수강생 PK \n" +
            "\n studentName : 수강생 이름 \n" +
            "\n subjectname : 수강과목 이름")
    public ResponseEntity<AdminStorageStudentFindRes> getStorage(@ParameterObject @PageableDefault(sort = "istudent", direction = Sort.Direction.ASC, page = 1) Pageable pageable,
                                                                 @RequestParam(required = false) Long iclassfication,
                                                                 @RequestParam(required = false) String studentName,
                                                                 @RequestParam(required = false) String subjectName) {
        AdminStorageStudentDto dto = new AdminStorageStudentDto();
        dto.setIclassfication(iclassfication);
        dto.setStudentName(studentName);
        dto.setSubjectName(subjectName);
        return SERVICE.selStorage(dto, pageable);
    }


//    @GetMapping("/storage-detail")
//    @Operation(summary = "보관함 학생 상세조회")
//    public AdminStorageStudentDetailRes detailStorage(@RequestParam Long istudent) {
//        AdminStorageStudentDetailDto dto = new AdminStorageStudentDetailDto();
//        dto.setIstudent(istudent);
//        return SERVICE.detailStorage(dto);
//    }

    @PatchMapping("/storage")
    @Operation(summary = "보관 여부 결정")
    public AdminStorageStudentPatchRes patchStorage(@RequestParam Long istudent) {

        AdminStorageStudentPatchDto dto = new AdminStorageStudentPatchDto();
        dto.setIstudent(istudent);
        return SERVICE.patchStorage(dto);
    }

    @PatchMapping("/editable-yn")
    @Operation(summary = "학생 권한 수정")
    public AdminStudentRoleRes patchRole(@RequestParam Long istudent,
                                         @RequestParam LocalDate startedAt,
                                         @RequestParam LocalDate endedAt,
                                         @RequestParam Integer editableYn) {
        AdminStudentRoleDto dto = new AdminStudentRoleDto();
        dto.setIstudent(istudent);
        dto.setStartedAt(startedAt);
        dto.setEndedAt(endedAt);
        dto.setEditableYn(editableYn);
        return SERVICE.patchRole(dto);
    }
}

package com.green.greenjobgo1.admin.std_management;

import com.green.greenjobgo1.admin.std_management.model.*;
import com.green.greenjobgo1.common.entity.StudentEntity;
import com.green.greenjobgo1.repository.StudentRepository;
import com.green.greenjobgo1.student.model.StudentCertificateDto;
import com.green.greenjobgo1.student.model.StudentInsDto;
import com.green.greenjobgo1.student.model.StudentInsTotalRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/student")
@Tag(name = "관리자 학생관리")
public class AdminStudentController {

    private final AdminStudentService SERVICE;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, path = "/file")
    @Operation(summary = "관리자 수강생 파일 및 링크 업로드", description = "istudent = 학생pk \n" +
            "\niFileCategory = 파일카테고리 pk 1번 이력서, 2번 포트폴리오 파일, 3번 포트폴리오 링크, 4번 포트폴리오 대표 이미지\n" +
            "\nintroducedLine = 한줄 소개 \n" +
            "\nfileLink = 파일 링크 ! 3번을 택했을경우에는 이부분만 작성하시고 파일은 안올려도됩니다.")
    public AdminStudentInsTotalRes postFile(@RequestPart(required = false) MultipartFile file,
                                            @RequestParam Long istudent,
                                            @RequestParam Long iFileCategory,
                                            @RequestParam(required = false) String introducedLine,
                                            @RequestParam(required = false) String oneWord,
                                            @RequestParam(required = false) String fileLink) {

        AdminStudentInsDto dto = new AdminStudentInsDto();
        dto.setIstudent(istudent);
        dto.setIFileCategory(iFileCategory);
        dto.setFileLink(fileLink);
        dto.setIntroducedLine(introducedLine);
        dto.setOneWord(oneWord);
        return SERVICE.insFile(file, dto);
    }


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
    public AdminStudentDetailFindRes getStudentDetail(@RequestParam Long istudent) {
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
    @Operation(summary = "보관함 리스트 조회", description = "img : 포트폴리오 대표 이미지 \n" +
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

    @GetMapping("/role-list")
    @Operation(summary = "수강생 열람 가능기간 조회")
    public AdminStudentRoleSelListRes getRoleList() {
        return SERVICE.setRoleList();
    }

    @PatchMapping("/storage")
    @Operation(summary = "보관 여부 결정")
    public AdminStorageStudentPatchRes patchStorage(@RequestParam Long istudent,
                                                    @RequestParam int storageYn) {

        AdminStorageStudentPatchDto dto = new AdminStorageStudentPatchDto();
        dto.setIstudent(istudent);
        dto.setStorageYn(storageYn);
        return SERVICE.patchStorage(dto);
    }

    @PutMapping("/certificate")
    @Operation(summary = "자격증 수정")
    public AdminStudentCertificateRes putCertificate(@RequestParam Long istudent,
                                                     @RequestParam Long icertificate,
                                                     @RequestParam String certificate) {
        AdminStudentCertificateDto dto = new AdminStudentCertificateDto();
        dto.setIstudent(istudent);
        dto.setCertificate(certificate);
        dto.setIcertificate(icertificate);
        return SERVICE.updCertificate(dto);
    }

    @PutMapping("/certificate-list")
    @Operation(summary = "자격증 수정-리스트")
    public AdminStudentCertificateTotalRes putCertificateList(@RequestParam List<String> certificate,
                                                              @RequestParam Long istudent) {
        AdminStudentCertificateListDto dto = new AdminStudentCertificateListDto();
        dto.setCertificate(certificate);
        dto.setIstudent(istudent);
        return SERVICE.updCertificateList(dto);
    }

    @PatchMapping("/portfolio-main")
    @Operation(summary = "대표 포트폴리오 설정")
    public AdminStudentPortfolioMainRes patchPortfolioMain(@RequestParam Long istudent,
                                                           @RequestParam Long ifile,
                                                           @RequestParam Integer mainYn) {
        AdminStudentPortfolioMainDto dto = new AdminStudentPortfolioMainDto();
        dto.setIfile(ifile);
        dto.setIstudent(istudent);
        dto.setMainYn(mainYn);
        return SERVICE.patchPortfolioMain(dto);
    }


    @PatchMapping("/main")
    @Operation(summary = "메인 여부 결정 (대분류당 10개) 상한")
    public List<AdminMainPortfolioPatchRes> patchMain(@RequestParam List<Long> istudent,
                                                      @RequestParam int companyMainYn) {
        AdminMainPortfolioPatchDto dto = new AdminMainPortfolioPatchDto();
        dto.setIstudent(istudent);
        dto.setCompanyMainYn(companyMainYn);
        return SERVICE.patchMain(dto);
    }

    @PatchMapping("/editable-yn")
    @Operation(summary = "학생 권한 수정")
    public AdminStudentRoleRes patchRole(@RequestParam Long icourseSubject,
                                         @RequestParam LocalDate startedAt,
                                         @RequestParam LocalDate endedAt) {

        AdminStudentRoleDto dto = new AdminStudentRoleDto();
        dto.setIcourseSubject(icourseSubject);
        dto.setStartedAt(startedAt);
        dto.setEndedAt(endedAt);
        return SERVICE.patchRole(dto);
    }


    @PutMapping
    @Operation(summary = "학생 정보 수정")
    public AdminStudentUpdRes putStudent(@RequestParam Long istudent,
                                         @RequestParam(required = false) String studentName,
                                         @RequestParam(required = false) String address,
                                         @RequestParam(required = false) String email,
                                         @RequestParam(required = false) String education,
                                         @RequestParam(required = false) String mobileNumber,
                                         @RequestParam(required = false) Integer huntJobYn) {

        AdminStudentUpdDto dto = new AdminStudentUpdDto();
        dto.setIstudent(istudent);
        dto.setStudentName(studentName);
        dto.setAddress(address);
        dto.setEmail(email);
        dto.setEducation(education);
        dto.setMobileNumber(mobileNumber);
        dto.setHuntJobYn(huntJobYn);
        return SERVICE.updStudent(dto);
    }

    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, path = "/file")
    @Operation(summary = "학생 업로드 파일 수정", description = "istudent = 학생pk \n" +
            "\niFileCategory = 파일카테고리 pk 1번 이력서, 2번 포트폴리오 파일, 3번 포트폴리오 링크, 4번 포트폴리오 대표 이미지\n" +
            "\nifile = 파일 pk" +
            "\nintroducedLine = 한줄 소개 \n" +
            "\nfileLink = 파일 링크 ! 3번을 택했을경우에는 이부분만 작성하시고 파일은 안올려도됩니다.")
    public AdminStudentFileUpdTotalRes putFile(@RequestPart(required = false) MultipartFile file,
                                               @RequestParam Long istudent,
                                               @RequestParam Long iFileCategory,
                                               @RequestParam Long ifile,
                                               @RequestParam(required = false) String introducedLine,
                                               @RequestParam(required = false) String oneWord,
                                               @RequestParam(required = false) String fileLink) {
        AdminStudentFileUpdDto dto = new AdminStudentFileUpdDto();
        dto.setIstudent(istudent);
        dto.setIFileCategory(iFileCategory);
        dto.setIfile(ifile);
        dto.setFileLink(fileLink);
        dto.setIntroducedLine(introducedLine);
        dto.setOneWord(oneWord);
        return SERVICE.updFile(file, dto);

    }

    @DeleteMapping
    @Operation(summary = "학생 삭제")
    public AdminStudentDelRes delStudent(@RequestParam Long istudent) {
        AdminStudentDelDto dto = new AdminStudentDelDto();
        dto.setIstudent(istudent);
        return SERVICE.delStudent(dto);
    }

    @DeleteMapping("/certificate")
    @Operation(summary = "자격증 삭제")
    public AdminStudentCertificateRes delCertificatee(@RequestParam Long istudent,
                                                      @RequestParam Long icertificate) {
        AdminStudentCertificateDto dto = new AdminStudentCertificateDto();
        dto.setIstudent(istudent);
        dto.setIcertificate(icertificate);
        return SERVICE.delCertificate(dto);
    }

    @DeleteMapping("/file")
    @Operation(summary = "학생 파일 삭제")
    public AdminStudentFileDelRes delFile(@RequestParam Long ifile) {
        AdminStudentFileDelDto dto = new AdminStudentFileDelDto();
        dto.setIfile(ifile);
        return SERVICE.delFile(dto);
    }
}

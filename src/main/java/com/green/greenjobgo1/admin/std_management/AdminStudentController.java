package com.green.greenjobgo1.admin.std_management;

import com.green.greenjobgo1.admin.std_management.model.*;
import com.green.greenjobgo1.common.entity.StudentEntity;
import com.green.greenjobgo1.common.security.config.exception.CommonErrorCode;
import com.green.greenjobgo1.common.security.config.exception.RestApiException;
import com.green.greenjobgo1.student.model.StudentCertificateDto;
import com.green.greenjobgo1.student.model.StudentCertificateRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/student")
/** name = "관리자 학생관리" , description = " UPLOAD_FAILED(453, \"파일을 업로드 할 수 없습니다.\"),\n" +
        "    \nDELETE_FAILED(454, \"파일을 삭제할 수 없습니다.\"),\n" +
        "    \nCOMPANY_MAIN_YN_FAILED(455, \"특정 분야 카테고리의 대표포트폴리오가 10개가 넘습니다.\"),\n" +
        "    \nMAIN_YN_FAILED(456, \"메인 포트폴리오는 1개만 설정할 수 있습니다.\"),\n" +
        "    \nONE_WORD_EMPTY(457, \"업로드하신 파일 또는 링크의 한줄소개가 비어있습니다.\"),\n" +
        "    \nINTRODUCED_LINE_EMPTY(458, \"업로드하신 이력서의 한줄소개가 비어있습니다.\")") **/
public class AdminStudentController {

    private final AdminStudentService SERVICE;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, path = "/file")
    /** summary = "관리자 수강생 파일 및 링크 업로드",
     * description = "istudent = 학생pk \n" +
            "\n iFileCategory = 파일카테고리 pk 1번 이력서, 2번 포트폴리오 파일, 3번 포트폴리오 링크, 4번 포트폴리오 대표 이미지\n" +
            "\n introducedLine = 한줄 소개 \n" +
            "\n fileLink = 파일 링크 ! 3번을 택했을경우에는 이부분만 작성하시고 파일은 안올려도됩니다." **/
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

    @GetMapping("/sub-category-list")
    /** "일괄삭제 리스트 GET" **/
    public AdminStudentSubjectCategoryRes getSubjectCategory(@PageableDefault(sort = "icourseSubject", direction = Sort.Direction.DESC, page = 1) Pageable pageable,
                                                             @RequestParam(required = false) Long iclassification,
                                                             @RequestParam(required = false) Long icourseSubject) {
        AdminStudentCategoryDto dto = new AdminStudentCategoryDto();
        dto.setIclassfication(iclassification);
        dto.setIcourseSubject(icourseSubject);
        return SERVICE.selSubjectCategoryList(dto, pageable);
    }

    @GetMapping("/dropbox-category")
    /** summary = "과정별 드롭박스 GET" **/
    public AdminStudentSubjectDropBoxRes getSubjectDropbox(@RequestParam Long iclassification) {
        AdminStudentSubjectDropBoxDto dto = new AdminStudentSubjectDropBoxDto();
        dto.setIclassification(iclassification);
        return SERVICE.selSubjectDropBox(dto);
    }


    @GetMapping
    /** summary = "학생 조회", description = "istudnet : 수강생 PK \n" +
            "\n classfication : 카테고리 \n" +
            "\n subjectName : 수강과목 명 \n" +
            "\n startedAt : 강의시작일자\n" +
            "\n endedAt : 강의종료일자\n" +
            "\n name : 수강생 이름 \n" +
            "\n gender : 수강생 성별\n" +
            "\n address : 지역\n" +
            "\n mobileNumber : 휴대폰 번호 \n" +
            "\n certificate : 자격증 \n" +
            "\n file : 이력서 + 포트폴리오 " **/
    public ResponseEntity<AdminStudentFindRes> getStudentList(@PageableDefault(sort = "istudent", direction = Sort.Direction.ASC, page = 1) Pageable pageable,
                                                              @RequestParam(required = false) Long icategory,
                                                              @RequestParam(required = false) String subjectName) {
        AdminStudentDto dto = new AdminStudentDto();
        dto.setIcategory(icategory);
        dto.setSubjectName(subjectName);
        return SERVICE.selStudentList(dto, pageable);
    }

    @GetMapping("/detail")
    /** summary = "학생 상세 조회", description = "name : 수강생 이름 \n" +
            "\n birthday : 생일 \n" +
            "\n address : 주소 \n" +
            "\n addressDetail : 상세주소\n" +
            "\n email : 이메일 \n" +
            "\n startedAt : 권한시작일자\n" +
            "\n endedAt : 권한만료일자 \n" +
            "\n mobileNumber : 휴대폰 번호 \n" +
            "\n education : 학력 " **/
    public AdminStudentDetailFindRes getStudentDetail(@RequestParam Long istudent) {
        AdminStudentDetailDto dto = new AdminStudentDetailDto();
        dto.setIstudent(istudent);
        return SERVICE.selStudentDetail(dto);
    }

    @GetMapping("/portfolio")
    /** summary = "포트폴리오 조회", description = "img : 포트폴리오 대표 이미지 \n" +
            "\n istudent : 수강생 PK \n" +
            "\n studentName : 수강생 이름 \n" +
            "\n subjectname : 수강과목 이름" **/
    public ResponseEntity<AdminPortfolioFindRes> getPortfolio(@PageableDefault(page = 1)
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
    /** summary = "보관함 리스트 조회", description = "img : 포트폴리오 대표 이미지 \n" +
            "\n istudent : 수강생 PK \n" +
            "\n studentName : 수강생 이름 \n" +
            "\n subjectname : 수강과목 이름" **/
    public ResponseEntity<AdminStorageStudentFindRes> getStorage(@PageableDefault(sort = "istudent", direction = Sort.Direction.ASC, page = 1) Pageable pageable,
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
    /** summary = "수강생 열람 가능기간 조회" **/
    public AdminStudentRoleSelListRes getRoleList() {
        return SERVICE.setRoleList();
    }

    @PatchMapping("/storage")
    /** summary = "보관 여부 결정" **/
    public AdminStorageStudentPatchRes patchStorage(@RequestParam Long istudent,
                                                    @RequestParam int storageYn) {

        AdminStorageStudentPatchDto dto = new AdminStorageStudentPatchDto();
        dto.setIstudent(istudent);
        dto.setStorageYn(storageYn);
        return SERVICE.patchStorage(dto);
    }

    @PutMapping("/certificate")
    /** summary = "자격증 수정" **/
    public AdminStudentCertificateRes putCertificate(@RequestParam Long istudent,
                                                     @RequestParam Long icertificate,
                                                     @RequestParam String certificate) {
        AdminStudentCertificateDto dto = new AdminStudentCertificateDto();
        dto.setIstudent(istudent);
        dto.setCertificate(certificate);
        dto.setIcertificate(icertificate);
        return SERVICE.updCertificate(dto);
    }

//    @PutMapping("/certificate-list")
//    @Operation(summary = "자격증 수정-리스트")
//    public AdminStudentCertificateTotalRes putCertificateList(@RequestParam List<String> certificate,
//                                                              @RequestParam Long istudent) {
//        AdminStudentCertificateListDto dto = new AdminStudentCertificateListDto();
//        dto.setCertificate(certificate);
//        dto.setIstudent(istudent);
//        return SERVICE.updCertificateList(dto);
//    }

    @PatchMapping("/portfolio-main")
    /** summary = "대표 포트폴리오 설정" **/
    public AdminStudentPortfolioMainRes patchPortfolioMain(@RequestParam Long istudent,
                                                           @RequestParam Long ifile) {
        AdminStudentPortfolioMainDto dto = new AdminStudentPortfolioMainDto();
        dto.setIfile(ifile);
        dto.setIstudent(istudent);
        return SERVICE.patchPortfolioMain(dto);
    }


    @PatchMapping("/main")
    /** summary = "메인 여부 결정 (대분류당 10개) 상한" **/
    public List<AdminMainPortfolioPatchRes> patchMain(@RequestParam List<Long> istudent,
                                                      @RequestParam int companyMainYn) {
        AdminMainPortfolioPatchDto dto = new AdminMainPortfolioPatchDto();
        dto.setIstudent(istudent);
        dto.setCompanyMainYn(companyMainYn);
        return SERVICE.patchMain(dto);
    }

    @PatchMapping("/editable-yn")
    /** summary = "학생 권한 수정" **/
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
    /** summary = "학생 정보 수정" **/
    public AdminStudentUpdRes putStudent(@RequestParam Long istudent,
                                         @RequestParam(required = false) String studentName,
                                         @RequestParam(required = false) String address,
                                         @RequestParam(required = false) String email,
                                         @RequestParam(required = false) String education,
                                         @RequestParam(required = false) String mobileNumber,
                                         @RequestParam(required = false) Integer huntJobYn,
                                         @RequestParam(required = false) LocalDate birthday,
                                         @RequestParam(required = false) String gender) {

        AdminStudentUpdDto dto = new AdminStudentUpdDto();
        dto.setIstudent(istudent);
        dto.setStudentName(studentName);
        dto.setAddress(address);
        dto.setEmail(email);
        dto.setEducation(education);
        dto.setMobileNumber(mobileNumber);
        dto.setHuntJobYn(huntJobYn);
        dto.setBirthday(birthday);
        dto.setGender(gender);
        return SERVICE.updStudent(dto);
    }

    @DeleteMapping
    /** summary = "학생 삭제" **/
    public AdminStudentDelRes delStudent(@RequestParam Long istudent) {
        AdminStudentDelDto dto = new AdminStudentDelDto();
        dto.setIstudent(istudent);
        return SERVICE.delStudent(dto);
    }

    @DeleteMapping("/certificate")
    /** summary = "자격증 삭제" **/
    public AdminStudentCertificateRes delCertificatee(@RequestParam Long istudent,
                                                      @RequestParam Long icertificate) {
        AdminStudentCertificateDto dto = new AdminStudentCertificateDto();
        dto.setIstudent(istudent);
        dto.setIcertificate(icertificate);
        return SERVICE.delCertificate(dto);
    }

    @DeleteMapping("/file")
    /** summary = "학생 파일 삭제" **/
    public AdminStudentFileDelRes delFile(@RequestParam Long ifile) {
        AdminStudentFileDelDto dto = new AdminStudentFileDelDto();
        dto.setIfile(ifile);
        return SERVICE.delFile(dto);
    }

    @DeleteMapping("/student-list")
    /** summary = "학생 일괄 삭제" **/
    public AdminStudentDelListRes delStudentList(@RequestParam Long iclassification,
                                                 @RequestParam Long icourseSubject) {
        AdminStudentDelDto dto = new AdminStudentDelDto();
        dto.setIclassification(iclassification);
        dto.setIcourseSubject(icourseSubject);
        return SERVICE.delStudentList(dto);
    }

    @PostMapping("/certificate")
    /** summary = "수강생 자격증 추가", description = "istudent = 수강생 pk \n" +
            "\n certificates = 자격증 list 로 입력" **/
    public AdminStudentCertificateListRes postCertificate(@RequestParam Long istudent,
                                                 @RequestParam List<String> certificates) {
            StudentCertificateDto dto = new StudentCertificateDto();
            dto.setIstudent(istudent);
            dto.setCertificates(certificates);
            return SERVICE.insCertificates(dto);

    }

    @GetMapping("/oneyearago")
    /** summary = "1년이 지난 학생 조회",
            description = "classification: 대분류<br>"+
                    "subjectName: 수강과목 <br>"+
                    "round: 기수(회차)<br>"+
                    "startedAt: 과정 시작날짜 <br>"+
                    "endedAt: 과정 종료날짜<br>"+
                    "istudent: 학생pk번호<br>"+
                    "studentName: 학생이름<br>"+
                    "gender: 성별<br>"+
                    "portfolio: 포트폴리오 개수<br>"+
                    "storageYn: 저장된 포트폴리오 (보관함) 유무 1이면 보관함<br>"+
                    "studentName: <br>" **/
    public AdminStudentOneYearRes getOneYearStudent(@PageableDefault(page = 1)
                                                    @SortDefault(sort = "istudent", direction = Sort.Direction.ASC) Pageable pageable,
                                                    @RequestParam(required = false) Long iclassification,
                                                    @RequestParam(required = false) Long icourseSubject,
                                                    @RequestParam(required = false) String studentName) {
        return SERVICE.getStudentOneYear(pageable, iclassification, icourseSubject, studentName);
    }

    @DeleteMapping("/oneyearago")
    /** summary = "1년이 지난 학생 삭제",description = "리턴값이 1이면 삭제완료 처리<br>"+
            "<예외처리><br>"+
    "파일 삭제 실패시: code: 454," **/
    public int delStudentOneYear(@RequestParam List<Long> istudent) {
         return SERVICE.delStudentOneYear(istudent);
    }

}

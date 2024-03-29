package com.green.greenjobgo1.student;

import com.green.greenjobgo1.admin.std_management.model.*;
import com.green.greenjobgo1.common.entity.StudentEntity;
import com.green.greenjobgo1.common.security.config.exception.CommonErrorCode;
import com.green.greenjobgo1.common.security.config.exception.RestApiException;
import com.green.greenjobgo1.common.security.config.security.AuthenticationFacade;
import com.green.greenjobgo1.common.security.config.security.model.MyUserDetails;
import com.green.greenjobgo1.repository.StudentRepository;
import com.green.greenjobgo1.student.model.*;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
//import org.springdoc.core.annotations.ParameterObject;
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
//@Tag(name = "수강생 CRUD", description = "NOT_AUTHORIZED(452, \"editableYn이 활성화 되지 않았습니다.\"),\n" +
//        "    \nUPLOAD_FAILED(453, \"파일을 업로드 할 수 없습니다.\"),\n" +
//        "    \nDELETE_FAILED(454, \"파일을 삭제할 수 없습니다.\")" +
//        "    \nMAIN_YN_FAILED(456, \"메인 포트폴리오는 1개만 설정할 수 있습니다.\"),\n" +
//        "    \nONE_WORD_EMPTY(457, \"업로드하신 파일 또는 링크의 한줄소개가 비어있습니다.\"),\n" +
//        "    \nINTRODUCED_LINE_EMPTY(458, \"업로드하신 이력서의 한줄소개가 비어있습니다.\");")
public class StudentController {

    private final StudentService SERVICE;
    private final StudentRepository STU_REP;
    private final AuthenticationFacade USERPK;


    @GetMapping
//    @Operation(summary = "수강생 기본정보 보기")
    public ResponseEntity<StudentSelTotalRes> getStudent() {
        MyUserDetails loginUser = USERPK.getLoginUser();
        StudentSelDto dto = new StudentSelDto();
        dto.setIstudent(loginUser.getIuser());
        return ResponseEntity.ok(SERVICE.selStudent(dto));
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, path = "/file")
//    @Operation(summary = "수강생 파일 및 링크 업로드", description = "istudent = 수강생 pk \n" +
//            "\n iFileCategory = 1 이력서 , 2 포트폴리오 파일, 3 포트폴리오 링크, 4 포트폴리오 대표이미지\n" +
//            "\n introducedLine = 이력서 한줄소개 \n" +
//            "\n oneWord = 파일별 한줄소개" +
//            "\n fileLink = 포트폴리오 링크 주소 \n" +
//            "\n file = 파일 업로드")
    @PreAuthorize("hasAnyRole('USER')")
    public StudentInsTotalRes postFile(@RequestPart(required = false) MultipartFile file,
                                       @RequestParam Long istudent,
                                       @RequestParam Long iFileCategory,
                                       @RequestParam(required = false) String introducedLine,
                                       @RequestParam(required = false) String oneWord,
                                       @RequestParam(required = false) String fileLink) {
        Optional<StudentEntity> stdId = STU_REP.findById(istudent);
        if (stdId.get().getEditableYn() == 1) {
            StudentInsDto dto = new StudentInsDto();
            dto.setIstudent(istudent);
            dto.setIFileCategory(iFileCategory);
            dto.setFileLink(fileLink);
            dto.setIntroducedLine(introducedLine);
            dto.setOneWord(oneWord);
            return SERVICE.insFile(file, dto);
        } else {
            throw new RestApiException(CommonErrorCode.NOT_AUTHORIZED);
        }
    }

    @PostMapping("/certificate")
//    @Operation(summary = "수강생 자격증 추가", description = "istudent = 수강생 pk \n" +
//            "\n certificates = 자격증 list 로 입력")
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
            throw new RestApiException(CommonErrorCode.NOT_AUTHORIZED);
        }
    }

    @PatchMapping("/huntjob-yn")
//    @Operation(summary = "취업 여부", description = "istudent = 학생 pk,\n" +
//            "\n huntJobYn = 취업 여부 0 1 둘중에 뭘로 취업인지 구직중인지 정해야함")
    @PreAuthorize("hasAnyRole('USER')")
    public StudentHuntJobRes patchHuntJob(@RequestParam Long istudent,
                                          @RequestParam int huntJobYn) {
        Optional<StudentEntity> stdId = STU_REP.findById(istudent);
        if (stdId.get().getEditableYn() == 1) {
            StudentHuntJobDto dto = new StudentHuntJobDto();
            dto.setIstudent(istudent);
            dto.setHuntJobYn(huntJobYn);
            return SERVICE.patchHuntJob(dto);
        } else {
            throw new RestApiException(CommonErrorCode.NOT_AUTHORIZED);
        }
    }

    @PutMapping("/certificate-list")
//    @Operation(summary = "자격증 수정-리스트")
    @PreAuthorize("hasAnyRole('USER')")
    public StudentCertificateTotalRes putCertificateList(@RequestParam List<String> certificate,
                                                         @RequestParam Long istudent) {

        Optional<StudentEntity> stdId = STU_REP.findById(istudent);
        if (stdId.get().getEditableYn() == 1) {
            StudentCertificateListDto dto = new StudentCertificateListDto();
            dto.setCertificate(certificate);
            dto.setIstudent(istudent);
            return SERVICE.updCertificateList(dto);
        } else {
            throw new RestApiException(CommonErrorCode.NOT_AUTHORIZED);
        }
    }

    @DeleteMapping("/file")
//    @Operation(summary = "수강생 파일 삭제")
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
//    @Operation(summary = "회사 리스트 조회",
//            description = "companyCode: DB 회사 pk값 <br>" +
//                    "area: 지역 <br>" +
//                    "leaderName: 대표 이름 <br>" +
//                    "companyName: 회사 명 <br>" +
//                    "leaderName: 대표 이름 <br>" +
//                    "homepage: 홈페이지링크 <br>" +
//                    "manager : 담당자 이름<br>" +
//                    "phonenumber : 전화번호 <br>" +
//                    "dateConslusion: 체결일자 <br>")
    public ResponseEntity<StudentCompanyListVo> companyList(@PageableDefault(direction = Sort.Direction.ASC, page = 1) Pageable pageable,
                                                            @RequestParam(required = false) String companyName) {
        return ResponseEntity.ok(SERVICE.companyList(companyName, pageable));
    }

    @PatchMapping("/portfolio-main")
//    @Operation(summary = "대표 포트폴리오 설정")
    @PreAuthorize("hasAnyRole('USER')")
    public StudentPortfolioMainRes patchPortfolioMain(@RequestParam Long istudent,
                                                      @RequestParam Long ifile) {

        Optional<StudentEntity> stdId = STU_REP.findById(istudent);
        if (stdId.get().getEditableYn() == 1) {
            StudentPortfolioMainDto dto = new StudentPortfolioMainDto();
            dto.setIfile(ifile);
            dto.setIstudent(istudent);
            return SERVICE.patchPortfolioMain(dto);
        } else {
            throw new RestApiException(CommonErrorCode.NOT_AUTHORIZED);
        }
    }

    @DeleteMapping("/certificate")
//    @Operation(summary = "자격증 삭제")
    @PreAuthorize("hasAnyRole('USER')")
    public CertificateRes delCertificatee(@RequestParam Long istudent,
                                          @RequestParam Long icertificate) {

        Optional<StudentEntity> stdId = STU_REP.findById(istudent);
        if (stdId.get().getEditableYn() == 1) {
            StudentCertificateDto dto = new StudentCertificateDto();
            dto.setIstudent(istudent);
            dto.setIcertificate(icertificate);
            return SERVICE.delCertificate(dto);
        } else {
            throw new RestApiException(CommonErrorCode.NOT_AUTHORIZED);
        }
    }
}

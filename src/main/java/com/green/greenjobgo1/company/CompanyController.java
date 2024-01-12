package com.green.greenjobgo1.company;

import com.green.greenjobgo1.admin.employeeProfile.model.EmployeeProfileVo;
import com.green.greenjobgo1.company.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "기업 수강생 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService service;
    @GetMapping("/student")
    @Operation(summary = "수강생을 검색합니다.", description = "<br>"+
            "maxpage: 총페이지 수 <br>"+
            "totalcount: 총 학생 수 <br>"+
            "subjectName: 과목명  <br>"+
            "studentName: 수강생 이름 <br>"+
            "istudent: 수강생 pk 번호 <br>"+
            "img : 포트폴리오 썸네일 이미지 <br>"+
            "sort 정렬: istudent,ASC or istudent,DESC 넣어주시면 됩니다. <br>"+
            "icategory: 1번 IT분야, 2번 건축 기계 분야, 3번 UIUX분야, 4번 영상분야, 6번 편집디자인 분야<br>"+
            "<br>")
    public CompanyStdRes getstudent(@ParameterObject @PageableDefault(page = 1)
                                        @SortDefault(sort = "istudent", direction = Sort.Direction.ASC)Pageable pageable,
                                    @RequestParam(required = false)Long icategory,
                                    @RequestParam(required = false)String subjectName,
                                    @RequestParam(required = false)String studentName)
    {
        return service.getstudent(pageable,icategory,subjectName,studentName);
    }

    @GetMapping("/student/{istudent}")
    @Operation(summary = "수강생을 상세 조회 합니다.", description = "<br>"+
            "startedAt: 수강과정 시작날짜 <br>"+
            "endedAt: 수강과정 종료 날짜 <br>"+
            "education: 학력 <br>"+
            "certificates: 자격증 <br>"+
            "file : <br>"+
            "<br>")
    public CompanystdDetailRes studentdetail(@PathVariable Long istudent){
         return service.detailStd(istudent);
    }


    @GetMapping("/mainstudent")
    public List<CompanyMainVo> mainstudent(@RequestParam(required = false) Long icategory){
        return service.mainselstd(icategory);
    }

    @GetMapping("/employee")
    @Operation(summary = "직원 프로필 리스트",description = "사진위치: /home/download/employee/pk번호/사진 <br>" +
            "oneWord: 한마디 <br>"+
            "name: 직원 이름 <br>"+
            "counselingNumber : 상담 전화 <br>"+
            "phone : 휴대폰 번호 <br>"+
            "email : 이메일 <br>")

    public ResponseEntity<List<EmployeeProfileVo>> getProfile(){
        return ResponseEntity.ok(service.getProfile());
    }
}

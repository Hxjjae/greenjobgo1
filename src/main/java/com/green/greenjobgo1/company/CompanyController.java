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
            "file: 썸네일 이미지 <br>"+
            "istudent: 수강생 pk번호 <br>"+
            "name: 수강생 이름 <br>"+
            "subjectName: 과목명 <br>"+
            "classification: 대분류 카테고리 명<br>"+
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
    @Operation(summary = "수강생을 상세조회.")
    public CompanystdDetailRes studentdetail(@PathVariable Long istudent){
         return service.detailStd(istudent);
    }


    @GetMapping("/mainstudent")
    public List<CompanyMainVo> list(){
        return null;
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

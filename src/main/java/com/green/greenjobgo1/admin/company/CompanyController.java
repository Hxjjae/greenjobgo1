package com.green.greenjobgo1.admin.company;

import com.green.greenjobgo1.admin.company.model.CompanyParam;
import com.green.greenjobgo1.admin.company.model.CompanyVo;
import com.green.greenjobgo1.config.entity.CompanyEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Tag(name = "기업아이디 관리")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/company")
public class CompanyController {

    private final CompanyService service;

    @PostMapping("/sign-up")
    @Operation(summary = "회원가입", description = "스웨거에서 기업아이디를 생성하기 위한 메소드입니다."+
                                    "")
    public ResponseEntity<CompanyEntity> insCompany(@RequestBody CompanyParam param){
        return ResponseEntity.ok(service.insCompany(param));
    }
    @GetMapping
    public ResponseEntity<List<CompanyVo>> getCompany(){
        return ResponseEntity.ok(service.getCompany());
    }

    @PatchMapping("")
    @Operation(summary = "열람권한 수정", description = """
            열람권한을 수정합니다.
            """)
    public ResponseEntity<CompanyEntity> patchCompany(@RequestParam Long icompany,
                                                      @RequestParam(required = false) String pw,
                                                      @RequestParam(required = false) LocalDate startedAt,
                                                      @RequestParam(required = false) LocalDate endedAt
                                                      ){
        return ResponseEntity.ok(service.patchCompany(icompany,pw,startedAt,endedAt));
    }

}

package com.green.greenjobgo1.admin.company;

import com.green.greenjobgo1.admin.company.model.CompanyParam;
import com.green.greenjobgo1.admin.company.model.CompanyVo;
import com.green.greenjobgo1.common.entity.CompanyEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Tag(name = "기업아이디 관리")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/company")
public class AdminCompanyController {

    private final AdminCompanyService service;

    @PostMapping("/sign-up")
    @Operation(summary = "회원가입", description = "스웨거에서 기업아이디를 생성하기 위한 메소드입니다. <br>"+
            "id : 아이디 <br>" +
            "pw : 패스워드 <br>" +
            "startedAt : 권한부여(시작일) <br>" +
            "endedAt : 권한부여(종료일) <br>"  )
    public ResponseEntity<CompanyEntity> insCompany(@RequestBody CompanyParam param){
        return ResponseEntity.ok(service.insCompany(param));
    }
    @GetMapping
    @Operation(summary = "기업 아이디 조회", description = "기업아이디를 조회하기 위한 메소드입니다. <br>"+
            "icompany : PK번호 <br>" +
            "id : 아이디 <br>" +
            "startedAt : 권한부여(시작일) <br>" +
            "endedAt : 권한부여(종료일) <br>"  )
    public ResponseEntity<List<CompanyVo>> getCompany(){
        return ResponseEntity.ok(service.getCompany());
    }

    @PatchMapping()
    @Operation(summary = "열람권한 수정", description ="아이디 열람권한 및 비밀번호 수정을 하기 위한 메소드 입니다.<br>"+
            "icompany : PK번호 <br>" +
            "pw : 패스워드 <br>" +
            "startedAt : 권한부여(시작일) <br>" +
            "endedAt : 권한부여(종료일) <br>" )
    public ResponseEntity<CompanyEntity> patchCompany(@RequestParam Long icompany,
                                                      @RequestParam(required = false) String pw,
                                                      @RequestParam(required = false) LocalDate startedAt,
                                                      @RequestParam(required = false) LocalDate endedAt
                                                      ){
        return ResponseEntity.ok(service.patchCompany(icompany,pw,startedAt,endedAt));
    }
    @DeleteMapping("/{icompany}")
    @Operation(summary = "기업 아이디 삭제", description = "기업아이디를 삭제하기 위한 메소드입니다. <br>"+
            "icompany : PK번호 <br>")
    public ResponseEntity<Integer> delCompany(@PathVariable Long icompany){
        return ResponseEntity.ok(service.delCompany(icompany));
    }

}

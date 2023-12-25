package com.green.greenjobgo1.admin.companylist;

import com.green.greenjobgo1.admin.companylist.model.CompanyNameDto;
import com.green.greenjobgo1.admin.companylist.model.CompanylistDto;
import com.green.greenjobgo1.config.entity.CompanyListEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "companylist")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companylist")
public class CompanylistController {

    private final CompanylistServicempl service;

    @PostMapping
    @Operation(summary = "회사명 추가",description = "")
    public ResponseEntity<CompanyListEntity> companyName(@RequestBody CompanyNameDto dto){
        CompanyListEntity entity = service.companyName(dto);
        return ResponseEntity.ok(entity);
    }


    @GetMapping
    @Operation(summary = "회사명 리스트 조회",description = "")
    public ResponseEntity<List<CompanyListEntity>> companyList(){
        List<CompanyListEntity> list = service.companyList();
        return ResponseEntity.ok(list);
    }

    @PatchMapping
    @Operation(summary = "회사명 수정",description = "")
    public ResponseEntity<CompanyListEntity> patchCompanyName(@RequestBody CompanylistDto dto){
        CompanyListEntity entity = service.patchCompanyName(dto);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/{companyCode}")
    @Operation(summary = "회사명 삭제",description = "리턴값이 1이면 삭제완료")
    public ResponseEntity<Integer> delCompanyName(@PathVariable Long companyCode){
        return ResponseEntity.ok(service.delCompanyName(companyCode));
    }
}

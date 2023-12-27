package com.green.greenjobgo1.admin.companylist;

import com.green.greenjobgo1.admin.companylist.model.CompanyNameDto;
import com.green.greenjobgo1.admin.companylist.model.CompanylistDto;
import com.green.greenjobgo1.admin.companylist.model.CompanylistVo;
import com.green.greenjobgo1.config.entity.CompanyListEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Tag(name = "companylist")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/companylist")
public class CompanylistController {

    private final CompanylistServicempl service;

    @PostMapping
    @Operation(summary = "회사명 추가",description = "dateConslusion: 체결일자, sector: 업종")
    public ResponseEntity<CompanyListEntity> companyName(@RequestBody CompanyNameDto dto){
        CompanyListEntity entity = service.companyName(dto);
        return ResponseEntity.ok(entity);
    }
    @PostMapping(value = "/excel",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "엑셀로 입력",description = "리턴값이 1이면 정상입력")
    public ResponseEntity<Integer> addExcel(@RequestPart MultipartFile file){
        return ResponseEntity.ok(service.addExcel(file));
    }

    @GetMapping
    @Operation(summary = "회사명 리스트 조회",description = "")
    public ResponseEntity<CompanylistVo> companyList(Pageable page){
        return ResponseEntity.ok(service.companyList(page));
    }

    @PatchMapping
    @Operation(summary = "회사명 수정",description = "")
    public ResponseEntity<CompanyListEntity> patchCompanyName(@RequestParam Long companyCode,
                                                              @RequestParam (required = false) String companyName,
                                                              @RequestParam (required = false) String sector,
                                                              @RequestParam (required = false) String manger,
                                                              @RequestParam (required = false) String phoneNumber,
                                                              @RequestParam (required = false) String dateConslusion){
        CompanyListEntity entity = service.patchCompanyName(companyCode,companyName,sector,manger,phoneNumber,dateConslusion);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/{companyCode}")
    @Operation(summary = "회사명 삭제",description = "리턴값이 1이면 삭제완료")
    public ResponseEntity<Integer> delCompanyName(@PathVariable Long companyCode){
        return ResponseEntity.ok(service.delCompanyName(companyCode));
    }
}

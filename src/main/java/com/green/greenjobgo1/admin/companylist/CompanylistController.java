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
    @Operation(summary = "회사명 추가",description = "companyCode: DB 회사 pk값 <br>" +
            "companyName: 회사 명 <br>"+
            "JobField: 채용(직업)분야 <br>"+
            "sector : 업종 <br>"+
            "manger : 담당자 <br>"+
            "phonenumber : 전화번호 <br>"+
            "dateConslusion: 체결일자 <br>")
    public ResponseEntity<CompanyListEntity> companyName(@RequestBody CompanyNameDto dto){
        CompanyListEntity entity = service.companyName(dto);
        return ResponseEntity.ok(entity);
    }
    @PostMapping(value = "/excel",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "엑셀로 추가",description = "리턴값이 1이면 정상 추가 됨")
    public ResponseEntity<Integer> addExcel(@RequestPart MultipartFile companyfile){
        return ResponseEntity.ok(service.addExcel(companyfile));
    }

    @GetMapping
    @Operation(summary = "회사명 리스트 조회",
            description = "companyCode: DB 회사 pk값 <br>" +
                    "area: 지역 <br>"+
                    "leaderName: 대표이름 <br>"+
                    "companyName: 회사 명 <br>"+
                    "sector : 업종 <br>"+
                    "manger : 담당자 <br>"+
                    "phonenumber : 전화번호 <br>"+
                    "dateConslusion: 체결일자 <br>")
    public ResponseEntity<CompanylistVo> companyList(@RequestParam int page,
                                                     @RequestParam int size,
                                                     @RequestParam(required = false) String companyName){
        return ResponseEntity.ok(service.companyList(page,size,companyName));
    }

    @PatchMapping
    @Operation(summary = "회사명 수정",description = "companyCode: DB 회사 pk값 <br>" +
            "companyName: 회사 명 <br>"+
            "sector : 업종 <br>"+
            "manger : 담당자 <br>"+
            "phonenumber : 전화번호 <br>"+
            "dateConslusion: 체결일자 <br>")
    public ResponseEntity<CompanyListEntity> patchCompanyName(@RequestParam Long companyCode,
                                                              @RequestParam (required = false) String area,
                                                              @RequestParam (required = false) String companyName,
                                                              @RequestParam (required = false) String sector,
                                                              @RequestParam (required = false) String manger,
                                                              @RequestParam (required = false) String leaderName,
                                                              @RequestParam (required = false) String jobField,
                                                              @RequestParam (required = false) String phoneNumber,
                                                              @RequestParam (required = false) String dateConslusion){
        CompanyListEntity entity = service.patchCompanyName(companyCode,area,companyName,sector,manger,leaderName,jobField,phoneNumber,dateConslusion);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/{companyCode}")
    @Operation(summary = "회사명 삭제",description = "리턴값이 1이면 삭제완료")
    public ResponseEntity<Integer> delCompanyName(@PathVariable Long companyCode){
        return ResponseEntity.ok(service.delCompanyName(companyCode));
    }
}
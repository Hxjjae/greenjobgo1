package com.green.greenjobgo1.admin.companylist;

import com.green.greenjobgo1.admin.companylist.model.CompanyNameDto;
import com.green.greenjobgo1.admin.companylist.model.CompanylistVo;
import com.green.greenjobgo1.common.entity.CompanyListEntity;
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
@Tag(name = "companylist")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/companylist")
public class CompanylistController {

    private final CompanylistService service;

    @PostMapping
    @Operation(summary = "회사 추가",description =
            "area: 지역 명 ex) 대구,서울 <br>"+
            "companyName: 회사 명 <br>"+
            "leaderName: 대표 이름 <br>"+
            "homepage: 홈페이지링크 <br>"+
            "manager : 담당자 이름 <br>"+
            "phonenumber : 전화번호 <br>"+
            "dateConslusion: 체결일자 <br>")
    public ResponseEntity<CompanyListEntity> companyName(@RequestBody CompanyNameDto dto){
        CompanyListEntity entity = service.companyName(dto);
        return ResponseEntity.ok(entity);
    }
    @PostMapping(value = "/excel",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "엑셀로 추가",description = "엑셀파일을 넣어주세요 <br> 리턴값이 1이면 정상 추가 됨")
    public ResponseEntity<Integer> addExcel(@RequestPart MultipartFile companyfile){
        return ResponseEntity.ok(service.addExcel(companyfile));
    }

    @GetMapping
    @Operation(summary = "회사 리스트 조회",
            description = "companyCode: DB 회사 pk값 <br>" +
                    "area: 지역 <br>"+
                    "leaderName: 대표 이름 <br>"+
                    "companyName: 회사 명 <br>"+
                    "leaderName: 대표 이름 <br>"+
                    "homepage: 홈페이지링크 <br>"+
                    "manager : 담당자 이름<br>"+
                    "phonenumber : 전화번호 <br>"+
                    "dateConslusion: 체결일자 <br>")
    public ResponseEntity<CompanylistVo> companyList(@RequestParam int page,
                                                     @RequestParam int size,
                                                     @RequestParam(required = false) String companyName){
        return ResponseEntity.ok(service.companyList(page,size,companyName));
    }

    @PatchMapping
    @Operation(summary = "회사정보 수정",description = "companyCode: DB 회사 pk값 <br>" +
            "area: 지역 <br>"+
            "companyName: 회사 명 <br>"+
            "leaderName: 대표 이름 <br>"+
            "homepage: 홈페이지링크 <br>"+
            "manager : 담당자 <br>"+
            "phonenumber : 전화번호 <br>"+
            "dateConslusion: 체결일자 <br>")
    public ResponseEntity<CompanyListEntity> patchCompanyName(@RequestParam Long companyCode,
                                                              @RequestParam (required = false) String area,
                                                              @RequestParam (required = false) String companyName,
                                                              @RequestParam (required = false) String manager,
                                                              @RequestParam (required = false) String leaderName,
                                                              @RequestParam (required = false) String homepage,
                                                              @RequestParam (required = false) String phoneNumber,
                                                              @RequestParam (required = false) LocalDate dateConslusion){
        CompanyListEntity entity = service.patchCompanyName(companyCode,area,companyName,manager,leaderName,homepage,phoneNumber,dateConslusion);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/{companyCode}")
    @Operation(summary = "회사 삭제",description = "리턴값이 1이면 삭제완료"+
            "companyCode: PK값 보내주시면 됩니다. <br>")
    public ResponseEntity<Integer> delCompanyName(@PathVariable List<Long> companyCode){
        return ResponseEntity.ok(service.delCompanyName(companyCode));
    }
}

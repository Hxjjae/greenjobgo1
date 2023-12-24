package com.green.greenjobgo1.admin.companylist;

import com.green.greenjobgo1.admin.companylist.model.AdminCompanylistDto;
import com.green.greenjobgo1.config.entity.CompanyListEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "companylist")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companylist")
public class AdminCompanylistController {

    private final AdminCompanylistServicempl service;

    @PostMapping
    @Operation(summary = "회사명 추가",description = "")
    public CompanyListEntity companyName(@RequestBody AdminCompanylistDto dto){
        return service.companyName(dto);
    }


    @GetMapping
    @Operation(summary = "회사명 리스트 조회",description = "")
    public List<CompanyListEntity> companyList(){
        return service.companyList();
    }

}

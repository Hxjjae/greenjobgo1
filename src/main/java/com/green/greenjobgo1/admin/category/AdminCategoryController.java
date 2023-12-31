package com.green.greenjobgo1.admin.category;

import com.green.greenjobgo1.admin.category.model.*;
import com.green.greenjobgo1.config.entity.CategorySubjectEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.NamedStoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/category")
@Tag(name = "관리자 카테고리 CRUD")
public class AdminCategoryController {

    @Autowired
    private final AdminCategoryService SERVICE;

    @PostMapping
    @Operation(summary = "카테고리 추가", description = "")
    public AdminCategoryInsRes postAdminCategory(@RequestBody AdminCategoryInsDto dto) {
        return SERVICE.insCategory(dto);
    }

    @GetMapping
    @Operation(summary = "카테고리 리스트 보기", description = "Responses : \n" +
            "\niclassification = 대분류 pk\n" +
            "\nclassification = 대분류 이름\n" +
            "\ndelYn = 삭제 여부")
    public ResponseEntity<List<AdminCategoryVo>> getAdminCategory() {
        List<AdminCategoryVo> adminCategoryVoList = SERVICE.selCategoryList();
        return ResponseEntity.ok().body(adminCategoryVoList);
    }

    @PutMapping
    @Operation(summary = "카테고리 수정", description = "")
    public AdminCategoryVo putAdminCategory(@RequestParam Long iclassification, @RequestParam String classification) {
        CategorySubjectEntity entity = new CategorySubjectEntity();
        entity.setIclassification(iclassification);
        entity.setClassification(classification);
        return SERVICE.updAdminCategory(entity);
    }

    @DeleteMapping
    @Operation(summary = "카테고리 삭제", description = "")
    public AdminCategoryVo delAdminCategory(@RequestParam Long iclassification) {
        CategorySubjectEntity entity = new CategorySubjectEntity();
        entity.setIclassification(iclassification);
        return SERVICE.delAdminCategory(entity);
    }
}

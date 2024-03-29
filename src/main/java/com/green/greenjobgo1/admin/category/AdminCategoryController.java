package com.green.greenjobgo1.admin.category;

import com.green.greenjobgo1.admin.category.model.*;
import com.green.greenjobgo1.common.entity.CategorySubjectEntity;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/category")
/** "관리자 카테고리 CRUD" **/
public class AdminCategoryController {

    @Autowired
    private final AdminCategoryService SERVICE;

    @PostMapping
    /** "카테고리 추가" **/
    public AdminCategoryInsRes postAdminCategory(@RequestBody AdminCategoryInsDto dto) {
        return SERVICE.insCategory(dto);
    }

    @GetMapping
    /** summary = "카테고리 리스트 보기", description = "Responses : \n" +
            "\niclassification = 대분류 pk\n" +
            "\nclassification = 대분류 이름\n" +
            "\ndelYn = 삭제 여부" **/
    public ResponseEntity<List<AdminCategoryVo>> getAdminCategory() {
        List<AdminCategoryVo> adminCategoryVoList = SERVICE.selCategoryList();
        return ResponseEntity.ok().body(adminCategoryVoList);
    }

    @PutMapping
    /** "카테고리 수정" **/
    public AdminCategoryVo putAdminCategory(@RequestParam Long iclassification, @RequestParam String classification) {
        CategorySubjectEntity entity = new CategorySubjectEntity();
        entity.setIclassification(iclassification);
        entity.setClassification(classification);
        return SERVICE.updAdminCategory(entity);
    }

    @DeleteMapping
    /** "카테고리 삭제" **/
    public AdminCategoryVo delAdminCategory(@RequestParam Long iclassification) {
        CategorySubjectEntity entity = new CategorySubjectEntity();
        entity.setIclassification(iclassification);
        return SERVICE.delAdminCategory(entity);
    }
}

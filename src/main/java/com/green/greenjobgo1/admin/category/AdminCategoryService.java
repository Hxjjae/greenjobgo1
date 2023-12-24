package com.green.greenjobgo1.admin.category;

import com.green.greenjobgo1.admin.category.model.AdminCategoryInsDto;
import com.green.greenjobgo1.admin.category.model.AdminCategoryInsRes;
import com.green.greenjobgo1.config.entity.CategorySubjectEntity;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminCategoryService {
    private final AdminCategoryRepository AC_REP;

    public AdminCategoryInsRes insCategory(AdminCategoryInsDto dto) {
        CategorySubjectEntity entity = new CategorySubjectEntity();

        entity.setClassification(dto.getClassification());

        CategorySubjectEntity result = AC_REP.save(entity);

        return AdminCategoryInsRes.builder()
                .iclassfication(result.getIClassification())
                .classification(result.getClassification())
                .build();
    }
}

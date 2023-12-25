package com.green.greenjobgo1.admin.category;

import com.green.greenjobgo1.admin.category.model.AdminCategoryDto;
import com.green.greenjobgo1.admin.category.model.AdminCategoryInsDto;
import com.green.greenjobgo1.admin.category.model.AdminCategoryInsRes;
import com.green.greenjobgo1.admin.category.model.AdminCategoryVo;
import com.green.greenjobgo1.config.entity.CategorySubjectEntity;
import jakarta.persistence.EntityNotFoundException;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                .iclassification(result.getIclassification())
                .classification(result.getClassification())
                .build();
    }

    public List<AdminCategoryVo> selCategoryList() {
        List<CategorySubjectEntity> all = AC_REP.findAll();
        return all.stream().map(list -> AdminCategoryVo.builder()
                .iclassification(list.getIclassification())
                .classification(list.getClassification())
                .build()).toList();
    }

    public AdminCategoryVo updAdminCategory(CategorySubjectEntity entity) {
        Optional<CategorySubjectEntity> byId = AC_REP.findById(entity.getIclassification());


        if (byId.isPresent()) {
            CategorySubjectEntity categorySubjectEntity = byId.get();
            categorySubjectEntity.setClassification(entity.getClassification());

            CategorySubjectEntity save = AC_REP.save(categorySubjectEntity);

            return AdminCategoryVo.builder()
                    .classification(save.getClassification())
                    .build();

        } else {
            throw new EntityNotFoundException("찾을 수 없는 pk 입니다.");
        }
    }
}

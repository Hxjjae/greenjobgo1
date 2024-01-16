package com.green.greenjobgo1.admin.category;

import com.green.greenjobgo1.admin.category.model.AdminCategoryInsDto;
import com.green.greenjobgo1.admin.category.model.AdminCategoryInsRes;
import com.green.greenjobgo1.admin.category.model.AdminCategoryVo;
import com.green.greenjobgo1.common.entity.CategorySubjectEntity;
import com.green.greenjobgo1.repository.AdminCategoryRepository;
import jakarta.persistence.EntityNotFoundException;
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
        List<CategorySubjectEntity> all = AC_REP.findByDelYn(0);
        return all.stream().map(list -> AdminCategoryVo.builder()
                .iclassification(list.getIclassification())
                .classification(list.getClassification())
                .delYn(list.getDelYn())
                .build()).toList();
    }

    public AdminCategoryVo updAdminCategory(CategorySubjectEntity entity) {
        Optional<CategorySubjectEntity> byId = AC_REP.findById(entity.getIclassification());


        if (byId.isPresent()) {
            if (byId.get().getDelYn() == 0) {
                CategorySubjectEntity csEntity = byId.get();
                csEntity.setClassification(entity.getClassification());

                CategorySubjectEntity save = AC_REP.save(csEntity);

                return AdminCategoryVo.builder()
                        .iclassification(entity.getIclassification())
                        .classification(save.getClassification())
                        .build();
            } else {
                throw new EntityNotFoundException("해당 컬럼은 삭제된 컬럼 입니다.");
            }


        } else {
            throw new EntityNotFoundException("찾을 수 없는 pk 입니다.");
        }
    }

    public AdminCategoryVo delAdminCategory(CategorySubjectEntity entity) {
        Optional<CategorySubjectEntity> byId = AC_REP.findById(entity.getIclassification());

        if (byId.isPresent()) {
            CategorySubjectEntity csEntity = byId.get();
            csEntity.setDelYn(1);

            CategorySubjectEntity save = AC_REP.save(csEntity);
            return AdminCategoryVo.builder()
                    .delYn(save.getDelYn())
                    .build();
        } else {
            throw new EntityNotFoundException("찾을 수 없는 pk 입니다.");
        }
    }
}

package com.green.greenjobgo1.admin.category.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminCategoryVo {
    private Long iclassification;
    private String classification;
    private int delYn;

}

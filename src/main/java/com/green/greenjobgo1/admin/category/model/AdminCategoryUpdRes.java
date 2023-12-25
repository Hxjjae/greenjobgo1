package com.green.greenjobgo1.admin.category.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminCategoryUpdRes {
    private Long iclassification;
    private String classification;

}

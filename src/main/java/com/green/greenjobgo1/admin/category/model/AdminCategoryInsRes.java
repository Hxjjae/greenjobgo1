package com.green.greenjobgo1.admin.category.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class AdminCategoryInsRes {

    private Long iclassification;
    private String classification;

}

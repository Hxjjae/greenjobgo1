package com.green.greenjobgo1.admin.std_management.model;

import com.green.greenjobgo1.common.utils.PagingUtils;
import com.querydsl.core.Tuple;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.print.Pageable;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminStudentSubjectCategoryRes {
    private PagingUtils page;
    private List<AdminStudentSubjectCategoryListRes> subject;
}

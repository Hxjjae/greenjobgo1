package com.green.greenjobgo1.admin.student.model;

import com.green.greenjobgo1.common.utils.PagingUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class AdminStudentFindRes {
    private PagingUtils page;
    private List<AdminStudentRes> res;
}

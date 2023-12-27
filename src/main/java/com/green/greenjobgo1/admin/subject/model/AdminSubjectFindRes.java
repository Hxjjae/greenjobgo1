package com.green.greenjobgo1.admin.subject.model;

import com.green.greenjobgo1.common.utils.PagingUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class AdminSubjectFindRes {
    private PagingUtils page;
    private List<AdminSubjectRes> res;
}

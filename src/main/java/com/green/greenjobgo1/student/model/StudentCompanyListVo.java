package com.green.greenjobgo1.student.model;

import com.green.greenjobgo1.admin.companylist.model.CompanylistRes;
import com.green.greenjobgo1.common.utils.PagingUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentCompanyListVo {
    private PagingUtils page;
    private List<CompanylistRes> list;

}

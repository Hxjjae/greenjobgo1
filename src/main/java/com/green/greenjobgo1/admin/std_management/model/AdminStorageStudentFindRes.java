package com.green.greenjobgo1.admin.std_management.model;

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
public class AdminStorageStudentFindRes {
    private PagingUtils page;
    private List<AdminStorageStudentRes> res;
}

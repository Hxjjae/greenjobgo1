package com.green.greenjobgo1.admin.company;

import com.green.greenjobgo1.admin.company.model.CompanyDto;
import com.green.greenjobgo1.admin.company.model.CompanyVo;
import com.green.greenjobgo1.config.entity.CompanyEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyServicempl {

    public CompanyVo inscompany(CompanyDto dto){
        CompanyEntity entity = new CompanyEntity();

        return null;
    }
}

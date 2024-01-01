package com.green.greenjobgo1.admin.company;

import com.green.greenjobgo1.admin.company.model.CompanyDto;
import com.green.greenjobgo1.admin.company.model.CompanyParam;
import com.green.greenjobgo1.admin.company.model.CompanyVo;
import com.green.greenjobgo1.config.entity.CompanyEntity;
import com.green.greenjobgo1.config.entity.CompanyListEntity;
import com.green.greenjobgo1.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyService {

    private final PasswordEncoder PW_ENCODER;
    private final CompanyRepository companyRep;

    public CompanyEntity insCompany(CompanyParam param){
        final String COMPANY = "ROLE_COMPANY";
        CompanyEntity companyentity = companyRep.findById(param.getId());
        if (companyentity == null) {
            CompanyEntity entity = CompanyEntity.builder().id(param.getId())
                    .password(PW_ENCODER.encode(param.getPw()))
                    .role(COMPANY)
                    .startedAt(param.getStartedAt())
                    .endedAt(param.getEndedAt())
                    .build();
            companyRep.save(entity);

            return entity;
        }

        return null;
    }

    public List<CompanyVo> getCompany(){
        List<CompanyEntity> companylist = companyRep.findAll();

       return companylist.stream().map(item->CompanyVo.builder()
                .icompany(item.getIcompany())
                .id(item.getId())
                .startedAt(item.getStartedAt())
                .endedAt(item.getEndedAt()).build()).toList();
    }

    public CompanyEntity patchCompany(Long icompany, String pw, LocalDate startedAt, LocalDate endedAt) {
        CompanyEntity entity = companyRep.findById(icompany).get();
        String encode = null;
        if (pw!=null){
            encode = PW_ENCODER.encode(pw);
        }


        // null이 아닌 경우에만 값이 들어갑니다.
        Optional.ofNullable(encode).ifPresent(entity::setPassword);
        Optional.ofNullable(startedAt).ifPresent(entity::setStartedAt);
        Optional.ofNullable(endedAt).ifPresent(entity::setEndedAt);

        companyRep.save(entity);
        return entity;
    }
}

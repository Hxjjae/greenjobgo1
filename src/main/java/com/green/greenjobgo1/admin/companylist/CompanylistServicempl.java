package com.green.greenjobgo1.admin.companylist;

import com.green.greenjobgo1.admin.companylist.model.CompanyNameDto;
import com.green.greenjobgo1.admin.companylist.model.CompanylistDto;
import com.green.greenjobgo1.config.entity.CompanyListEntity;
import com.green.greenjobgo1.config.entity.QCompanyListEntity;
import com.green.greenjobgo1.repository.CompanylistRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanylistServicempl {
    private final JPAQueryFactory jpaQueryFactory;
    private final CompanylistRepository companylistRep;

    QCompanyListEntity qCompanyList = QCompanyListEntity.companyListEntity;

    public CompanyListEntity companyName(CompanyNameDto dto){
        CompanyListEntity entity = new CompanyListEntity();
        entity.setCompanyName(dto.getCompanyName());

        companylistRep.save(entity);

        return entity;
    }

    public List<CompanyListEntity> companyList(){
        List<CompanyListEntity> companylist = jpaQueryFactory.select(Projections.constructor(CompanyListEntity.class, qCompanyList.companyCode, qCompanyList.companyName))
                .from(qCompanyList)
                .fetch();

        return companylist;

    }

    public CompanyListEntity patchCompanyName(CompanylistDto dto){
        CompanyListEntity entity = new CompanyListEntity();
        entity.setCompanyCode(dto.getCompanyCode());
        entity.setCompanyName(dto.getCompanyName());
        companylistRep.save(entity);
        return entity;
    }

    public int delCompanyName(Long companyCode){
        Optional<CompanyListEntity> optEntity = companylistRep.findById(companyCode);

        if (optEntity.isPresent()) {
            companylistRep.deleteById(companyCode);
            return 1;
        } else {
            return 0;
        }
    }



}

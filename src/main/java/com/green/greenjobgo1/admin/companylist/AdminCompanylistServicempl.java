package com.green.greenjobgo1.admin.companylist;

import com.green.greenjobgo1.admin.companylist.model.AdminCompanylistDto;
import com.green.greenjobgo1.config.entity.CompanyListEntity;
import com.green.greenjobgo1.config.entity.QCompanyEntity;
import com.green.greenjobgo1.config.entity.QCompanyListEntity;
import com.green.greenjobgo1.repository.CompanylistRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminCompanylistServicempl {
    private final JPAQueryFactory jpaQueryFactory;
    private final CompanylistRepository companylistRep;

    QCompanyListEntity qCompanyList = QCompanyListEntity.companyListEntity;

    public CompanyListEntity companyName(AdminCompanylistDto dto){
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

}

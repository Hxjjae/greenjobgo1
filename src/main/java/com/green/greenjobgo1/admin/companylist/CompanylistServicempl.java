package com.green.greenjobgo1.admin.companylist;

import com.green.greenjobgo1.admin.companylist.model.CompanyExcel;
import com.green.greenjobgo1.admin.companylist.model.CompanyNameDto;
import com.green.greenjobgo1.admin.companylist.model.CompanylistDto;
import com.green.greenjobgo1.common.utils.ExcelUtil;
import com.green.greenjobgo1.config.entity.CompanyListEntity;
import com.green.greenjobgo1.config.entity.QCompanyListEntity;
import com.green.greenjobgo1.repository.CompanylistRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanylistServicempl {
    private final JPAQueryFactory jpaQueryFactory;
    private final CompanylistRepository companylistRep;
    private final ExcelUtil excelUtil;

    QCompanyListEntity qCompanyList = QCompanyListEntity.companyListEntity;

    public CompanyListEntity companyName(CompanyNameDto dto) {
        CompanyListEntity entity = new CompanyListEntity();
        entity.setCompanyName(dto.getCompanyName());

        companylistRep.save(entity);

        return entity;
    }

    public List<CompanyListEntity> companyList() {
        List<CompanyListEntity> companylist = jpaQueryFactory.select(Projections.constructor(CompanyListEntity.class, qCompanyList.companyCode, qCompanyList.companyName))
                .from(qCompanyList)
                .fetch();

        return companylist;

    }

    public CompanyListEntity patchCompanyName(CompanylistDto dto) {
        CompanyListEntity entity = new CompanyListEntity();
        entity.setCompanyCode(dto.getCompanyCode());
        entity.setCompanyName(dto.getCompanyName());
        companylistRep.save(entity);
        return entity;
    }

    public int delCompanyName(Long companyCode) {
        Optional<CompanyListEntity> optEntity = companylistRep.findById(companyCode);

        if (optEntity.isPresent()) {
            companylistRep.deleteById(companyCode);
            return 1;
        } else {
            return 0;
        }
    }

    @Transactional
    public int addExcel(MultipartFile file) {

        List<CompanyExcel> listUser = new ArrayList<>();

        // 엑셀의 셀데이터를 객체에 담기
        List<Map<String, Object>> listMap = excelUtil.getListData(file, 1, 3);

        for (Map<String, Object> map : listMap) {
            CompanyExcel company = new CompanyExcel();

            // 각 셀의 데이터를 VO에 set한다.
            company.setNum(map.get("0").toString());
            company.setDate(map.get("1").toString());
            company.setCompanyname(map.get("2").toString());

            listUser.add(company);
        }

        //DB에 저장
        for (CompanyExcel oneUser : listUser) {
            CompanyListEntity entity = new CompanyListEntity();
            entity.setCompanyName(oneUser.getCompanyname());
            CompanyListEntity save = companylistRep.save(entity);
            if (save.getCompanyCode() == null){
                return 0;
            }
        }
        return 1;
    }
}

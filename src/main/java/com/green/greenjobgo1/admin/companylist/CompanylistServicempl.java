package com.green.greenjobgo1.admin.companylist;

import com.green.greenjobgo1.admin.companylist.model.*;
import com.green.greenjobgo1.common.utils.ExcelUtil;
import com.green.greenjobgo1.config.entity.CompanyListEntity;
import com.green.greenjobgo1.config.entity.QCompanyListEntity;
import com.green.greenjobgo1.repository.CompanylistRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

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
        entity.setManager(dto.getManger());
        entity.setSector(dto.getManger());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setDateConslusion(dto.getDateConslusion());
        entity.setCompanyName(dto.getCompanyName());

        companylistRep.save(entity);

        return entity;
    }

    public CompanylistVo companyList(int page,int size,String companyName) {
        //page 값이 1이상인 경우 -1
        int page2 = (page > 0) ? (page - 1) : 0;

        Pageable pageable = PageRequest.of(page2, size);

        List<CompanyListEntity> companylist = jpaQueryFactory.select(Projections.constructor(CompanyListEntity.class,
                        qCompanyList.companyCode,
                        qCompanyList.area,
                        qCompanyList.companyName,
                        qCompanyList.sector,
                        qCompanyList.leaderName,
                        qCompanyList.manager,
                        qCompanyList.phoneNumber,
                        qCompanyList.dateConslusion
                        ))
                .from(qCompanyList)
                .where(eqCompanyName(companyName))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = jpaQueryFactory.select(qCompanyList.companyCode.count())
                .from(qCompanyList)
                .where()
                .where(eqCompanyName(companyName))
                .fetchOne();


        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();

        int maxpage = (int) Math.ceil((double) count / pageable.getPageSize());
        log.info("maxpage:{}",maxpage);

        List<CompanylistRes> list = companylist.stream().map(item ->
                CompanylistRes.builder().companyCode(item.getCompanyCode())
                        .companyName(item.getCompanyName())
                        .area(item.getArea())
                        .leaderName(item.getLeaderName())
                        .dateConslusion(item.getDateConslusion())
                        .manger(item.getManager())
                        .phoneNumber(item.getPhoneNumber())
                        .sector(item.getSector()).build()).toList();


        // 순번 넣기
        for (int i = 0; i < companylist.size(); i++) {
            list.get(i).setCompanyNumber(i+1L);
        }

        return CompanylistVo.builder().list(list).maxpage(maxpage).build();

    }
    private BooleanExpression eqCompanyName(String companyName) {
        if(companyName == null || companyName.isEmpty()) {
            return null;
        }
        return qCompanyList.companyName.like("%"+companyName+"%");

    }

    public CompanyListEntity patchCompanyName(Long companyCode, String companyName, String sector,String manger,String phoneNumber,String dateConslusion ) {
        CompanyListEntity entity = companylistRep.findById(companyCode).get();
        entity.setCompanyCode(companyCode);
        if (companyName!=null){
            entity.setCompanyName(companyName);
        }
        if (sector!=null){
            entity.setSector(sector);
        }
        if (manger!=null){
            entity.setManager(manger);
        }
        if (phoneNumber!=null){
            entity.setPhoneNumber(phoneNumber);
        }
        if (dateConslusion!=null){
            entity.setDateConslusion(dateConslusion);
        }

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
    public int addExcel(MultipartFile companyfile) {

        List<CompanyExcel> listUser = new ArrayList<>();

        // 엑셀의 셀데이터를 객체에 담기
        List<Map<String, Object>> listMap = excelUtil.getListData(companyfile, 1, 5);

        for (Map<String, Object> map : listMap) {
            CompanyExcel company = new CompanyExcel();

            // 각 셀의 데이터를 VO에 set한다.
            company.setDateConslusion(map.get("1").toString());
            company.setCompanyname(map.get("2").toString());
            company.setLeaderName(map.get("3").toString());
            company.setSector(map.get("5").toString());

            listUser.add(company);
        }

        //DB에 저장
        for (CompanyExcel oneUser : listUser) {
            CompanyListEntity entity = new CompanyListEntity();
            entity.setCompanyName(oneUser.getCompanyname());
            entity.setDateConslusion(oneUser.getDateConslusion());
            entity.setSector(oneUser.getSector());
            entity.setLeaderName(oneUser.getLeaderName());

            CompanyListEntity save = companylistRep.save(entity);
            if (save.getCompanyCode() == null){
                return 0;
            }
        }
        return 1;
    }
}

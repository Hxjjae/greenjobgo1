package com.green.greenjobgo1.admin.companylist;

import com.green.greenjobgo1.admin.companylist.model.*;
import com.green.greenjobgo1.common.utils.ExcelUtil;
import com.green.greenjobgo1.config.entity.CompanyListEntity;
import com.green.greenjobgo1.config.entity.QCompanyListEntity;
import com.green.greenjobgo1.repository.CompanylistRepository;
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

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanylistService {
    private final JPAQueryFactory jpaQueryFactory;
    private final CompanylistRepository companylistRep;
    private final ExcelUtil excelUtil;

    QCompanyListEntity qCompanyList = QCompanyListEntity.companyListEntity;

    public CompanyListEntity companyName(CompanyNameDto dto) {

        CompanyListEntity companyListEntity = CompanyListEntity.builder()
                .area(dto.getArea())
                .companyName(dto.getCompanyName())
                .leaderName(dto.getLeaderName())
                .jobField(dto.getJobField())
                .manager(dto.getManager())
                .phoneNumber(dto.getPhoneNumber())
                .dateConslusion(dto.getDateConslusion()).build();

        companylistRep.save(companyListEntity);

        return companyListEntity;
    }

    public CompanylistVo companyList(int page,int size,String companyName) {
        //page 값이 1이상인 경우 -1
        int page2 = (page > 0) ? (page - 1) : 0;
        Pageable pageable = PageRequest.of(page2, size);

        List<CompanyListEntity> companylist = jpaQueryFactory.select(Projections.constructor(CompanyListEntity.class,
                        qCompanyList.companyCode,
                        qCompanyList.area,
                        qCompanyList.companyName,
                        qCompanyList.leaderName,
                        qCompanyList.jobField,
                        qCompanyList.manager,
                        qCompanyList.phoneNumber,
                        qCompanyList.dateConslusion))
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

        int totalcount = Math.toIntExact(count);

        int maxpage = (int) Math.ceil((double) count / pageable.getPageSize());
        log.info("maxpage:{}",maxpage);

        List<CompanylistRes> list = companylist.stream().map(item ->
                CompanylistRes.builder().companyCode(item.getCompanyCode())
                        .companyName(item.getCompanyName())
                        .area(item.getArea())
                        .leaderName(item.getLeaderName())
                        .jobField(item.getJobField())
                        .dateConslusion(item.getDateConslusion())
                        .manager(item.getManager())
                        .phoneNumber(item.getPhoneNumber()).build()).toList();

        return CompanylistVo.builder().list(list).maxpage(maxpage).totalcount(totalcount).build();

    }
    private BooleanExpression eqCompanyName(String companyName) {
        if(companyName == null || companyName.isEmpty()) {
            return null;
        }
        return qCompanyList.companyName.like("%"+companyName+"%");

    }

    public CompanyListEntity patchCompanyName(Long companyCode, String area, String companyName, String manager
            , String leaderName, String jobField, String phoneNumber, LocalDate dateConslusion ) {
        CompanyListEntity entity = companylistRep.findById(companyCode).get();
        entity.setCompanyCode(companyCode);


        // null이 아닌 경우에만 값이 들어갑니다.
        Optional.ofNullable(area).ifPresent(entity::setArea);
        Optional.ofNullable(companyName).ifPresent(entity::setCompanyName);
        Optional.ofNullable(manager).ifPresent(entity::setManager);
        Optional.ofNullable(leaderName).ifPresent(entity::setLeaderName);
        Optional.ofNullable(jobField).ifPresent(entity::setJobField);
        Optional.ofNullable(phoneNumber).ifPresent(entity::setPhoneNumber);
        Optional.ofNullable(dateConslusion).ifPresent(entity::setDateConslusion);

        companylistRep.save(entity);
        return entity;
    }

    public int delCompanyName(List<Long> companyCode) {
        for (int i = 0; i <companyCode.size(); i++) {
            Optional<CompanyListEntity> optEntity = companylistRep.findById(companyCode.get(i));
            if (optEntity.isPresent()) {
                companylistRep.deleteById(companyCode.get(i));
            } else {
                return 0;
            }
        }
            return 1;

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

            listUser.add(company);
        }


        //DB에 저장
        for (CompanyExcel oneUser : listUser) {
            int dateConslusion = Integer.parseInt(oneUser.getDateConslusion());

            LocalDate date = LocalDate.of(1900, 1, 1).plusDays( dateConslusion- 2);

            CompanyListEntity entity = new CompanyListEntity();
            entity.setCompanyName(oneUser.getCompanyname());
            entity.setDateConslusion(date);
            entity.setLeaderName(oneUser.getLeaderName());
            log.info("기업이름 :{}",oneUser.getCompanyname());
            CompanyListEntity byCompanyName = companylistRep.findByCompanyName(oneUser.getCompanyname());


            if (byCompanyName == null){
                CompanyListEntity save = companylistRep.save(entity);
                if (save.getCompanyCode() == null){
                    return 0;
                }
            }

        }
        return 1;
    }
}

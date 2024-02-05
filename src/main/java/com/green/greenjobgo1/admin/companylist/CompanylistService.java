package com.green.greenjobgo1.admin.companylist;

import com.green.greenjobgo1.admin.companylist.model.*;
import com.green.greenjobgo1.common.entity.QCompanyListEntity;
import com.green.greenjobgo1.common.security.config.exception.CommonErrorCode;
import com.green.greenjobgo1.common.security.config.exception.RestApiException;
import com.green.greenjobgo1.common.utils.ExcelUtil;
import com.green.greenjobgo1.common.entity.CompanyListEntity;
import com.green.greenjobgo1.repository.CompanylistRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanylistService {
    private final JPAQueryFactory jpaQueryFactory;
    private final CompanylistRepository companylistRep;
    private final ExcelUtil excelUtil;

    QCompanyListEntity qCompanyList = QCompanyListEntity.companyListEntity;

    public CompanyInsVo companyName(CompanyNameDto dto) {
        CompanyListEntity byCompanyName = companylistRep.findByCompanyName(dto.getCompanyName());
        if (byCompanyName!=null){
            throw new RestApiException(CommonErrorCode.COMPANYLIST_DUPLICATE);
        }
        if (dto.getLeaderName()==null){
            throw new RestApiException(CommonErrorCode.LEADERNAME_NULL);
        }
        if (dto.getArea()==null){
            throw new RestApiException(CommonErrorCode.AREA_NULL);
        }
        if (dto.getManager()==null){
            throw new RestApiException(CommonErrorCode.MANAGER_NULL);
        }
        if (dto.getPhoneNumber()==null){
            throw new RestApiException(CommonErrorCode.PHONE_NULL);
        }

        CompanyListEntity companyListEntity = CompanyListEntity.builder()
                .area(dto.getArea())
                .companyName(dto.getCompanyName())
                .leaderName(dto.getLeaderName())
                .homepage(dto.getHomepage())
                .manager(dto.getManager())
                .phoneNumber(dto.getPhoneNumber())
                .dateConslusion(dto.getDateConslusion()).build();

        companylistRep.save(companyListEntity);
        return CompanyInsVo.builder().status("success")
                .message("성공적으로 입력 되었습니다.")
                .companyCode(companyListEntity.getCompanyCode())
                .area(companyListEntity.getArea())
                .companyName(companyListEntity.getCompanyName())
                .leaderName(companyListEntity.getLeaderName())
                .homepage(companyListEntity.getHomepage())
                .manager(companyListEntity.getManager())
                .phoneNumber(companyListEntity.getPhoneNumber())
                .dateConslusion(companyListEntity.getDateConslusion()).build();
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
                        qCompanyList.homepage,
                        qCompanyList.manager,
                        qCompanyList.phoneNumber,
                        qCompanyList.dateConslusion))
                .from(qCompanyList)
                .where(eqCompanyName(companyName))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qCompanyList.companyCode.desc())
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
                        .homepage(item.getHomepage())
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
            , String leaderName, String homepage, String phoneNumber, LocalDate dateConslusion ) {
        CompanyListEntity entity = companylistRep.findById(companyCode).get();
        CompanyListEntity byCompanyName = companylistRep.findByCompanyName(companyName);
        entity.setCompanyCode(companyCode);

        //이름중복 예외처리
        if (byCompanyName!=null){
            throw new RestApiException(CommonErrorCode.COMPANYLIST_DUPLICATE);
        }

        // null이 아닌 경우에만 값이 들어갑니다.
        Optional.ofNullable(area).ifPresent(entity::setArea);
        Optional.ofNullable(companyName).ifPresent(entity::setCompanyName);
        Optional.ofNullable(manager).ifPresent(entity::setManager);
        Optional.ofNullable(leaderName).ifPresent(entity::setLeaderName);
        Optional.ofNullable(homepage).ifPresent(entity::setHomepage);
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

    public void downloadCompanylist(HttpServletResponse response) throws IOException {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("기업 리스트 다운로드");
        //Row row = null;
        Cell cell = null;

        // Header 넣기
        int cellNum = 0;
        Row header = sheet.createRow(0); // 첫번째줄 헤더
        String[] headerName = new String[]{"지역", "기업명", "홈페이지", "대표명", "담당자", "연락처", "체결일자"};


        for (int i = 0; i < headerName.length; i++) {
            header.createCell(i+1).setCellValue(headerName[i]);
        }

        //헤더 색넣기

        CellStyle paleBlue = wb.createCellStyle();
        paleBlue.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
        paleBlue.setFillPattern(FillPatternType.SOLID_FOREGROUND);

         //각 셀에 스타일 적용
        IntStream.rangeClosed(0, 7).forEach(col -> {
            CellStyle style=paleBlue;
            header.getCell(col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(style);
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
        });
        List<CompanyListEntity> companyList = companylistRep.findAll();

        int rowNum = 1;
        int num = 1;
        for (CompanyListEntity company:companyList) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(num++);
            row.createCell(1).setCellValue(company.getArea());
            row.createCell(2).setCellValue(company.getCompanyName());
            row.createCell(3).setCellValue(company.getHomepage());
            row.createCell(4).setCellValue(company.getLeaderName());
            row.createCell(5).setCellValue(company.getManager());
            row.createCell(6).setCellValue(company.getPhoneNumber());

            LocalDate dateConslusion = company.getDateConslusion();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = dateConslusion.format(formatter);
            row.createCell(7).setCellValue(formattedDate);
        }


        // Download
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment;filename=company.xlsx");

        try {
            wb.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            wb.close();
        }
    }
}

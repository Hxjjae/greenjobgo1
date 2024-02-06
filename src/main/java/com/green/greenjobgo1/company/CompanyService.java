package com.green.greenjobgo1.company;


import com.green.greenjobgo1.admin.employeeProfile.model.EmployeeProfileVo;
import com.green.greenjobgo1.common.entity.*;
import com.green.greenjobgo1.common.utils.MyFileUtils;
import com.green.greenjobgo1.common.utils.PagingUtils;
import com.green.greenjobgo1.company.model.*;
import com.green.greenjobgo1.repository.AdminCategoryRepository;
import com.green.greenjobgo1.repository.EmployeeProfileRepository;
import com.green.greenjobgo1.repository.StudentRepository;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyService {
    private final JPAQueryFactory jpaQueryFactory;
    private final EmployeeProfileRepository EmployeeProfileRep;
    private final StudentRepository studentRep;
    private final AdminCategoryRepository AdminCategoryRep;

    QStudentEntity qstudent = QStudentEntity.studentEntity;
    QStudentCourseSubjectEntity qstudentCourseSubject = QStudentCourseSubjectEntity.studentCourseSubjectEntity;
    QCourseSubjectEntity qCourseSubject  = QCourseSubjectEntity.courseSubjectEntity;
    QFileEntity qfileEntity = QFileEntity.fileEntity;
    QCategorySubjectEntity qCategorySubjectEntity = QCategorySubjectEntity.categorySubjectEntity;
    QCertificateEntity certificate = QCertificateEntity.certificateEntity;

    public CompanyStdRes getstudent(Pageable pageable,Long icategory,String subjectName,String studentName){

        log.info("pageable:{}",pageable.getSort());
        List<CompanyStdVo> StudentEntity  = jpaQueryFactory.select(
                Projections.bean(CompanyStdVo.class,
                        qCourseSubject.subjectName,
                        qstudent.name.as("studentName"),
                        qstudent.istudent,
                        qfileEntity.file.as("img")
                )).from(qstudent)
                .innerJoin(qstudentCourseSubject)
                .on(qstudentCourseSubject.studentEntity.istudent.eq(qstudent.istudent))
                .innerJoin(qCourseSubject)
                .on(qCourseSubject.icourseSubject.eq(qstudentCourseSubject.courseSubjectEntity.icourseSubject))
                .innerJoin(qfileEntity)
                .on(qfileEntity.studentEntity.istudent.eq(qstudent.istudent))
                .innerJoin(qCategorySubjectEntity)
                .on(qCategorySubjectEntity.iclassification.eq(qCourseSubject.categorySubjectEntity.iclassification))
                .where(qfileEntity.fileCategoryEntity.iFileCategory.eq(4L))
                .where(qstudent.delYn.eq(0))
                .where(qstudent.huntJobYn.eq(0))
                .where(eqcategory(icategory))
                .where(eqsubjectName(subjectName))
                .where(eqstudentName(studentName))
                .orderBy(getorder(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        long count = jpaQueryFactory.select(
                        qstudent.istudent.count()
                ).from(qstudent)
                .innerJoin(qstudentCourseSubject)
                .on(qstudentCourseSubject.studentEntity.istudent.eq(qstudent.istudent))
                .innerJoin(qCourseSubject)
                .on(qCourseSubject.icourseSubject.eq(qstudentCourseSubject.courseSubjectEntity.icourseSubject))
                .innerJoin(qfileEntity)
                .on(qfileEntity.studentEntity.istudent.eq(qstudent.istudent))
                .innerJoin(qCategorySubjectEntity)
                .on(qCategorySubjectEntity.iclassification.eq(qCourseSubject.categorySubjectEntity.iclassification))
                .where(qfileEntity.fileCategoryEntity.iFileCategory.eq(4L))
                .where(qstudent.delYn.eq(0))
                .where(qstudent.huntJobYn.eq(0))
                .where(eqcategory(icategory))
                .where(eqsubjectName(subjectName))
                .where(eqstudentName(studentName))
                .fetchOne();


        int totalcount = Math.toIntExact(count);
        int maxpage = (int) Math.ceil((double) count / pageable.getPageSize());

        PagingUtils utils = new PagingUtils(pageable.getPageNumber() + 1,  (int)count, pageable);
        utils.setIdx( (int)count);

        log.info("maxpage:{}",maxpage);

        //url 위치 붙여주기
        List<CompanyStdVo> list = StudentEntity.stream().map(item -> CompanyStdVo.builder()
                .img("/img/student/"+item.getIstudent()+"/"+item.getImg())
                .istudent(item.getIstudent())
                .studentName(item.getStudentName())
                .subjectName(item.getSubjectName()).build()).toList();

        return CompanyStdRes.builder().page(utils).maxpage(maxpage).totalcount(totalcount).vo(list).build();

    }

    public CompanyStdlistRes getstudentlist(Pageable pageable, Long icategory, String subjectName, String studentName){

        log.info("pageable:{}",pageable.getSort());
        List<CompanyStdlistVo> StudentEntity = jpaQueryFactory.select(
                        Projections.bean(CompanyStdlistVo.class,
                                qCourseSubject.subjectName,
                                qstudent.introducedLine,
                                qstudent.name.as("studentName"),
                                qstudent.istudent,
                                qfileEntity.file.as("img"),
                                qCourseSubject.startedAt,
                                qCourseSubject.endedAt
                        )).from(qstudent)
                .innerJoin(qstudentCourseSubject)
                .on(qstudentCourseSubject.studentEntity.istudent.eq(qstudent.istudent))
                .innerJoin(qCourseSubject)
                .on(qCourseSubject.icourseSubject.eq(qstudentCourseSubject.courseSubjectEntity.icourseSubject))
                .innerJoin(qfileEntity)
                .on(qfileEntity.studentEntity.istudent.eq(qstudent.istudent))
                .innerJoin(qCategorySubjectEntity)
                .on(qCategorySubjectEntity.iclassification.eq(qCourseSubject.categorySubjectEntity.iclassification))
                .where(qfileEntity.fileCategoryEntity.iFileCategory.eq(4L))
                .where(qstudent.delYn.eq(0))
                .where(qstudent.huntJobYn.eq(0))
                .where(eqcategory(icategory))
                .where(eqsubjectName(subjectName))
                .where(eqstudentName(studentName))
                .orderBy(getorder(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        long count = jpaQueryFactory.select(
                        qstudent.istudent.count()
                ).from(qstudent)
                .innerJoin(qstudentCourseSubject)
                .on(qstudentCourseSubject.studentEntity.istudent.eq(qstudent.istudent))
                .innerJoin(qCourseSubject)
                .on(qCourseSubject.icourseSubject.eq(qstudentCourseSubject.courseSubjectEntity.icourseSubject))
                .innerJoin(qfileEntity)
                .on(qfileEntity.studentEntity.istudent.eq(qstudent.istudent))
                .innerJoin(qCategorySubjectEntity)
                .on(qCategorySubjectEntity.iclassification.eq(qCourseSubject.categorySubjectEntity.iclassification))
                .where(qfileEntity.fileCategoryEntity.iFileCategory.eq(4L))
                .where(qstudent.delYn.eq(0))
                .where(qstudent.huntJobYn.eq(0))
                .where(eqcategory(icategory))
                .where(eqsubjectName(subjectName))
                .where(eqstudentName(studentName))
                .fetchOne();


        int totalcount = Math.toIntExact(count);
        int maxpage = (int) Math.ceil((double) count / pageable.getPageSize());

        PagingUtils utils = new PagingUtils(pageable.getPageNumber() + 1,  (int)count, pageable);
        utils.setIdx( (int)count);

        log.info("maxpage:{}",maxpage);

        //url 위치 붙여주기
        List<CompanyStdlistVo> list = StudentEntity.stream().map(item -> CompanyStdlistVo.builder()
                        .img("/img/student/" + item.getIstudent() + "/" + item.getImg())
                        .introducedLine(item.getIntroducedLine())
                        .istudent(item.getIstudent())
                        .studentName(item.getStudentName())
                        .subjectName(item.getSubjectName())
                        .startedAt(item.getStartedAt())
                        .endedAt(item.getEndedAt()).build())
                .toList();

        return CompanyStdlistRes.builder().page(utils).maxpage(maxpage).totalcount(totalcount).vo(list).build();

    }

    private OrderSpecifier[] getorder(Pageable pageable) {
        List<OrderSpecifier> orders = new LinkedList();
        if(!pageable.getSort().isEmpty()) {
            for(Sort.Order order : pageable.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;

                switch(order.getProperty().toLowerCase()) {
                    case "istudent": orders.add(new OrderSpecifier(direction,qstudent.istudent)); break;
                    case "subjectName": orders.add(new OrderSpecifier(direction,qCourseSubject.subjectName)); break;
                    case "name": orders.add(new OrderSpecifier(direction,qstudent.name)); break;

                }
            }
        }
        return orders.stream().toArray(OrderSpecifier[]::new);
    }

    private BooleanExpression eqcategory(Long icategory) {
        if(icategory == null || icategory == 0) {
            return null;
        }
        return qCategorySubjectEntity.iclassification.eq(icategory);
    }

    private BooleanExpression eqsubjectName(String subjectName) {
        if(subjectName == null || subjectName.isEmpty()) {
            return null;
        }
        return qCourseSubject.subjectName.like("%"+subjectName+"%");
    }
    private BooleanExpression eqstudentName(String studentName) {
        if(studentName == null || studentName.isEmpty()) {
            return null;
        }
        return qstudent.name.like("%"+studentName+"%");
    }
    private BooleanExpression eqistudent(Long istudent) {
        if(istudent == null || istudent == 0) {
            return null;
        }
        return qstudent.istudent.eq(istudent);
    }

    public CompanystdDetailRes detailStd(Long istudent){
        StudentEntity student = studentRep.findById(istudent).get();

        CompanyStdDetail vo = jpaQueryFactory.select(Projections.constructor(CompanyStdDetail.class,
                        qstudent.name,
                        qstudent.gender,
                        qstudent.age,
                        qstudent.birthday,
                        qstudent.huntJobYn,
                        qstudent.address,
                        qstudent.id.as("email"),
                        qCourseSubject.startedAt,
                        qCourseSubject.endedAt,
                        qCourseSubject.subjectName,
                        qstudent.mobileNumber,
                        qstudent.education,
                        qstudent.introducedLine
                )).from(qstudent)
                .innerJoin(qstudentCourseSubject)
                .on(qstudentCourseSubject.studentEntity.istudent.eq(qstudent.istudent))
                .innerJoin(qCourseSubject)
                .on(qCourseSubject.icourseSubject.eq(qstudentCourseSubject.courseSubjectEntity.icourseSubject))
                .where(eqistudent(istudent))
                .fetchOne();

        List<CompanyStudentCertificateRes> res = jpaQueryFactory.select(
                        Projections.bean(CompanyStudentCertificateRes.class, certificate.icertificate, certificate.certificate))
                .from(certificate)
                .join(certificate.studentEntity, qstudent)
                .where(qstudent.istudent.eq(istudent)).fetch();

        CompanythumbnailVo thumbnail = jpaQueryFactory.select(Projections.bean(CompanythumbnailVo.class,
                        qfileEntity.file,qfileEntity.originFileName
                )).from(qfileEntity)
                .innerJoin(qfileEntity)
                .on(qfileEntity.studentEntity.istudent.eq(qstudent.istudent))
                .where(qstudent.istudent.eq(istudent))
                .where(qfileEntity.fileCategoryEntity.iFileCategory.eq(4L))
                .fetchOne();

         CompanyAboutMeVo aboutMe = jpaQueryFactory.select(Projections.bean(CompanyAboutMeVo.class,
                        qfileEntity.file
                         ,qfileEntity.originFileName
                )).from(qfileEntity)
                .innerJoin(qfileEntity)
                .on(qfileEntity.studentEntity.istudent.eq(qstudent.istudent))
                .where(qstudent.istudent.eq(istudent))
                .where(qfileEntity.fileCategoryEntity.iFileCategory.eq(1L))
                .fetchOne();

        List<CompanyStdfileImgVo> fileLink = jpaQueryFactory.select(Projections.bean(CompanyStdfileImgVo.class,
                        qfileEntity.file
                        ,qfileEntity.originFileName
                        ,qfileEntity.oneWord,
                        qfileEntity.mainYn
                )).from(qfileEntity)
                .innerJoin(qfileEntity)
                .on(qfileEntity.studentEntity.istudent.eq(qstudent.istudent))
                .where(qstudent.istudent.eq(istudent))
                .where(qfileEntity.fileCategoryEntity.iFileCategory.eq(3L))
                .fetch();


        List<CompanystdportfolioVo> portfolio= jpaQueryFactory.select(Projections.bean(CompanystdportfolioVo.class,
                        qfileEntity.file
                        ,qfileEntity.originFileName
                        ,qfileEntity.oneWord
                        ,qfileEntity.mainYn
                )).from(qfileEntity)
                .innerJoin(qfileEntity)
                .on(qfileEntity.studentEntity.istudent.eq(qstudent.istudent))
                .where(qstudent.istudent.eq(istudent))
                .where(qfileEntity.fileCategoryEntity.iFileCategory.eq(2L))
                .fetch();

        CompanyStdDetailVo build = CompanyStdDetailVo.builder()
                .name(vo.getName())
                .gender(vo.getGender())
                .age(vo.getAge())
                .birthday(vo.getBirthday())
                .huntJobYn(vo.getHuntJobYn())
                .address(vo.getAddress())
                .email(vo.getEmail())
                .startedAt(vo.getStartedAt())
                .endedAt(vo.getEndedAt())
                .mobileNumber(vo.getMobileNumber())
                .education(vo.getEducation())
                .introducedLine(vo.getIntroducedLine())
                .certificates(res)
                .subject(vo.getSubjectName()).build();

        //CompanythumbnailVo aboutMeNull = CompanythumbnailVo.builder().file("").build();

        CompanyStdfileRes build1 = CompanyStdfileRes.builder()
                .thumbnail(thumbnail)
                .aboutMe(aboutMe)
                .portfolio((portfolio.stream().map(item-> CompanystdportfolioVo.builder()
                        .file(item.getFile())
                        .originFileName(item.getOriginFileName())
                        .oneWord(item.getOneWord()).mainYn(item.getMainYn()).build()).toList()))
                .fileLink(fileLink.stream().map(item-> CompanyStdfileImgVo.builder()
                        .file(item.getFile())
                        .oneWord(item.getOneWord()).mainYn(item.getMainYn())
                        .build()).toList()).build();
        return CompanystdDetailRes.builder().vo(build).file(build1).build();
    }

    public CompanyMainRes mainselstd(Long icategory){
        List<CompanyMainVo> list = jpaQueryFactory.select(Projections.constructor(CompanyMainVo.class,
                        qfileEntity.file.as("img"),
                        qstudent.istudent,
                        qstudent.name,
                        qCourseSubject.subjectName))
                .from(qstudent)
                .innerJoin(qfileEntity)
                .on(qfileEntity.studentEntity.istudent.eq(qstudent.istudent))
                .innerJoin(qstudentCourseSubject)
                .on(qstudentCourseSubject.studentEntity.istudent.eq(qstudent.istudent))
                .innerJoin(qCourseSubject)
                .on(qCourseSubject.icourseSubject.eq(qstudentCourseSubject.courseSubjectEntity.icourseSubject))
                .innerJoin(qCategorySubjectEntity)
                .on(qCategorySubjectEntity.iclassification.eq(qCourseSubject.categorySubjectEntity.iclassification))
                .where(eqcategory(icategory))
                .where(qfileEntity.fileCategoryEntity.iFileCategory.eq(4L))
                .where(qstudent.companyMainYn.eq(1))
                .where(qstudent.huntJobYn.eq(1))
                .fetch();

        List<CompanyMainVo> mainvolist = list.stream().map(item -> CompanyMainVo.builder()
                .img("/img/student/" + item.getIstudent() + "/" + item.getImg())
                .istudent(item.getIstudent())
                .name(item.getName())
                .subjectName(item.getSubjectName()).build()).toList();

        List<CategorySubjectEntity> all = AdminCategoryRep.findByDelYn(0);

        List<CompanyCateVo> vo = all.stream().map(item -> CompanyCateVo.builder()
                .iclassification(item.getIclassification())
                .classification(item.getClassification()).build()).toList();

        return CompanyMainRes.builder().list(mainvolist).vo(vo).build();

    }

    public List<EmployeeProfileVo> getProfile(){
        List<EmployeeProfileEntity> entityList = EmployeeProfileRep.findByDelYn(0);

        return entityList.stream().map(profile -> EmployeeProfileVo.builder()
                .oneWord(profile.getOneWord())
                .iemply(profile.getIemply())
                .counselingNumber (profile.getCounselingNumber())
                .name(profile.getName())
                .phoneNumber(profile.getPhoneNumber())
                .email(profile.getEmail())
                .profilePic("/img/employee/"+profile.getIemply()+"/"+profile.getProfilePic()).build()).toList();
    }
    @Value("${file.dir}")
    private String FILE_DIR;

    public String studyPdf(MultipartFile pic) throws IOException, DocumentException {

        String fileDir = MyFileUtils.getAbsolutePath(FILE_DIR);
        String centerPath = String.format("%s/student/%s", MyFileUtils.getAbsolutePath(fileDir),"1");
        String originFileName = pic.getOriginalFilename();
        String savedFileName = MyFileUtils.makeRandomFileNm(originFileName);
        String savedFilePath = String.format("%s/%s",centerPath, savedFileName);

        //MultipartFile 타입의 pic을 -> pdf 파일로 변환 해서 -> pdf파일에 대한 권한을 제한한다.
        // MultipartFile pic 을 PDF를 읽어오기
        InputStream inputStream = pic.getInputStream();
        PdfReader pdfReader = new PdfReader(inputStream);

        // 읽기 전용으로 만들기 위한 객체 생성
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfStamper pdfStamper = new PdfStamper(pdfReader, outputStream);
        // 모든 권한 제거하기 null은 사용자한테 비밀번호를 받지 않겠다는 뜻, 여기서 0은 모든 권한을 비활성화(제한)하겠다는 뜻
        pdfStamper.setEncryption(null, null, 0, PdfWriter.ENCRYPTION_AES_128);
       //인쇄만 가능하도록 제한하기 PdfWriter.ALLOW_PRINTING을 주면 인쇄만 가능
        //pdfStamper.setEncryption(null, "read-only-password".getBytes(),
                //PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
        pdfStamper.setFullCompression();
        pdfStamper.close();

        // PDF 파일로 저장
        try (FileOutputStream fileOutputStream = new FileOutputStream(savedFilePath)) {
            fileOutputStream.write(outputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}

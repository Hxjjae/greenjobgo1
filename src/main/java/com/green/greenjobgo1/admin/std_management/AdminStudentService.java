package com.green.greenjobgo1.admin.std_management;

import com.green.greenjobgo1.admin.std_management.model.*;
import com.green.greenjobgo1.common.entity.*;
import com.green.greenjobgo1.common.security.config.exception.CommonErrorCode;
import com.green.greenjobgo1.common.security.config.exception.RestApiException;
import com.green.greenjobgo1.common.utils.MyFileUtils;
import com.green.greenjobgo1.common.utils.PagingUtils;
import com.green.greenjobgo1.company.CompanyService;
import com.green.greenjobgo1.repository.*;
import com.green.greenjobgo1.student.model.CertificateRes;
import com.green.greenjobgo1.student.model.StudentCertificateDto;
import com.green.greenjobgo1.student.model.StudentCertificateRes;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Service
@Slf4j
@RequiredArgsConstructor
public class AdminStudentService {

    private final StudentRepository STU_REP;
    private final CourseSubjectRepository COS_REP;
    private final FileRepository FILE_REP;
    private final CertificateRepository CERT_REP;
    private final StudentCourseSubjectRepository SCS_REP;
    private final AdminCategoryRepository A_CATE_REP;
    private final FileCategoryRepository FILE_CATE_REP;
    private final AdminStudentQdsl adminStudentQdsl;
    private final CompanyService SERVICE;
    private final PasswordEncoder PW_ENCODER;

    @Value("${file.dir}")
    private String FILE_DIR;


    @Transactional
    public AdminStudentInsTotalRes insFile(MultipartFile file, AdminStudentInsDto dto) {
        Optional<FileCategoryEntity> fileCateId = FILE_CATE_REP.findById(dto.getIFileCategory());
        Optional<StudentEntity> stdId = STU_REP.findById(dto.getIstudent());


        if (!fileCateId.isPresent() || !stdId.isPresent()) {
            return null;
        }

        FileEntity entity = new FileEntity();
        entity.setFileCategoryEntity(fileCateId.get());
        entity.setCreatedAt(LocalDate.now());
        entity.setStudentEntity(stdId.get());
        entity.setOneWord(dto.getOneWord());

        if (file != null) {
            entity.setOriginFileName(file.getOriginalFilename());
        }

        StudentEntity studentEntity = stdId.get();

        if (dto.getIFileCategory() == 1) {
            if (dto.getIntroducedLine() == null || dto.getIntroducedLine().equals(" ")
                || dto.getIntroducedLine().isEmpty()) {
                throw new RestApiException(CommonErrorCode.INTRODUCED_LINE_EMPTY);
            } else {
                studentEntity.setIstudent(dto.getIstudent());
                studentEntity.setIntroducedLine(dto.getIntroducedLine());
            }
        } else if (dto.getIFileCategory() == 2 || dto.getIFileCategory() == 3){
            if (dto.getOneWord() == null) {
                throw new RestApiException(CommonErrorCode.ONE_WORD_EMPTY);
            }
        }

        StudentEntity studentSave = (dto.getIntroducedLine() != null) ? STU_REP.save(studentEntity) : studentEntity;
        String savedFileNm;

        Long iFileCategory = fileCateId.get().getIFileCategory();

        if (iFileCategory == 1 || iFileCategory == 2 || iFileCategory == 4) {
            savedFileNm = MyFileUtils.makeRandomFileNm(file.getOriginalFilename());
        } else if (iFileCategory == 3) {
            savedFileNm = (dto.getFileLink() != null) ? dto.getFileLink() : null;
        } else {
            return null;
        }

        if (savedFileNm != null) {
            entity.setFile(savedFileNm);
            FileEntity result = FILE_REP.save(entity);


            String centerPath = String.format("%s/student/%d", MyFileUtils.getAbsolutePath(FILE_DIR), entity.getStudentEntity().getIstudent());
            String savedFilePath = String.format("%s/%s", centerPath, savedFileNm);
            File fileTargetDir = new File(centerPath);

            if (!fileTargetDir.exists()) {
                if (!fileTargetDir.mkdirs()) {
                    String errorMessage = "디렉토리를 생성할 수 없습니다. 경로: " + fileTargetDir.getAbsolutePath();
                    log.error(errorMessage);
                    throw new RuntimeException(errorMessage);
                }
            }

            if (iFileCategory == 1 || iFileCategory == 4) {
                File fileTarget = new File(String.format("%s/%s", centerPath, savedFileNm));
                if (iFileCategory == 1) {
                    Long resumeCount = adminStudentQdsl.countByResume(studentSave.getIstudent());

                    if (resumeCount > 1) {
                        throw new RestApiException(CommonErrorCode.UPLOAD_FAILED);
                    } else {
                        try {
                            file.transferTo(fileTarget);
                        } catch (IOException e) {
                            throw new RestApiException(CommonErrorCode.UPLOAD_FAILED);
                        }
                    }
                } else {
                    Long thumbnailCount = adminStudentQdsl.countByThumbnail(studentSave.getIstudent());
                    if (thumbnailCount > 1) {
                        throw new RestApiException(CommonErrorCode.UPLOAD_FAILED);
                    } else {
                        try {
                            file.transferTo(fileTarget);
                        } catch (IOException e) {
                            throw new RestApiException(CommonErrorCode.UPLOAD_FAILED);
                        }
                    }
                }

            } else if (iFileCategory == 2) {
                Long fileCount = adminStudentQdsl.countByFile(studentSave.getIstudent());

                if (fileCount > 5) {
                    throw new RestApiException(CommonErrorCode.UPLOAD_FAILED);
                } else {
                    try {
                        InputStream inputStream = file.getInputStream();
                        PdfReader pdfReader = new PdfReader(inputStream);

                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                        PdfStamper pdfStamper = new PdfStamper(pdfReader, outputStream);
                        pdfStamper.setEncryption(null, null, 0, PdfWriter.ENCRYPTION_AES_128);

                        pdfStamper.close();

                        try (FileOutputStream fileOutputStream = new FileOutputStream(savedFilePath)) {
                            fileOutputStream.write(outputStream.toByteArray());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        throw new RestApiException(CommonErrorCode.UPLOAD_FAILED);
                    } catch (DocumentException e) {
                        throw new RestApiException(CommonErrorCode.UPLOAD_FAILED);
                    }

                }
            } else if (iFileCategory == 3) {
                Long fileLinkCount = adminStudentQdsl.countByFileLink(studentSave.getIstudent());
                if (fileLinkCount > 5) {
                    throw new RestApiException(CommonErrorCode.UPLOAD_FAILED);
                } else {
                    savedFileNm = (dto.getFileLink() != null) ? dto.getFileLink() : null;
                }
            }

            if (file != null) {
                AdminStudentInsRes res = AdminStudentInsRes.builder()
                        .file(result.getFile())
                        .ifile(result.getIfile())
                        .createdAt(result.getCreatedAt())
                        .istudent(result.getStudentEntity().getIstudent())
                        .oneWord(result.getOneWord())
                        .originFileName(file.getOriginalFilename())
                        .build();

                AdminStudentIntroducedLineRes std = AdminStudentIntroducedLineRes.builder()
                        .introducedLine(studentSave.getIntroducedLine())
                        .build();

                return AdminStudentInsTotalRes.builder()
                        .res(res)
                        .std(std)
                        .build();
            } else {
                AdminStudentInsRes res = AdminStudentInsRes.builder()
                        .file(result.getFile())
                        .ifile(result.getIfile())
                        .createdAt(result.getCreatedAt())
                        .istudent(result.getStudentEntity().getIstudent())
                        .oneWord(result.getOneWord())
                        .build();

                AdminStudentIntroducedLineRes std = AdminStudentIntroducedLineRes.builder()
                        .introducedLine(studentSave.getIntroducedLine())
                        .build();

                return AdminStudentInsTotalRes.builder()
                        .res(res)
                        .std(std)
                        .build();
            }

        }
        return null;
    }


    public ResponseEntity<AdminStudentFindRes> selStudentList(AdminStudentDto dto, Pageable pageable) {
        long maxPage = adminStudentQdsl.stdIdx(dto);

        PagingUtils utils = new PagingUtils(pageable.getPageNumber() + 1, (int) maxPage, pageable);
        utils.setIdx((int) maxPage);

        List<AdminStudentRes> list = adminStudentQdsl.studentVos(dto, pageable);

        AdminStudentFindRes build = AdminStudentFindRes.builder()
                .page(utils)
                .res(list.stream().map(item -> AdminStudentRes.builder()
                        .istudent(item.getIstudent())
                        .address(item.getAddress())
                        .endedAt(item.getEndedAt())
                        .startedAt(item.getStartedAt())
                        .name(item.getName())
                        .gender(item.getGender())
                        .education(item.getEducation())
                        .classification(item.getClassification())
                        .certificate(adminStudentQdsl.certificateCount(item.getIstudent()))
                        .mobileNumber(item.getMobileNumber())
                        .subjectName(item.getSubjectName())
                        .file(adminStudentQdsl.fileCount(item.getIstudent()))
                        .huntJobYn(item.getHuntJobYn())
                        .build()).toList())
                .build();
        return ResponseEntity.ok(build);
    }

    public AdminStudentDetailFindRes selStudentDetail(AdminStudentDetailDto dto) {
        Optional<StudentEntity> byId = STU_REP.findById(dto.getIstudent());

        AdminStudentImg img = adminStudentQdsl.img(dto);
        AdminStudentResume resume = adminStudentQdsl.resume(dto);
        List<AdminStudentFileRes> files = adminStudentQdsl.fileVos(dto);
        List<AdminStudentFileLink> fileLinks = adminStudentQdsl.fileLinks(dto);
        List<AdminStudentCertificateRes> certiRes = adminStudentQdsl.certificateRes(dto.getIstudent());
        AdminStudentDetailSubjectRes subjectList = adminStudentQdsl.subjectList(dto.getIstudent());

        if (byId.isPresent()) {
            return AdminStudentDetailFindRes.builder()
                    .res(AdminStudentDetailRes.builder()
                            .age(byId.get().getAge())
                            .gender(byId.get().getGender())
                            .name(byId.get().getName())
                            .startedAt(subjectList.getStartedAt())
                            .endedAt(subjectList.getEndedAt())
                            .birthday(byId.get().getBirthday())
                            .address(byId.get().getAddress())
                            .education(byId.get().getEducation())
                            .email(byId.get().getId())
                            .mobileNumber(byId.get().getMobileNumber())
                            .huntJobYn(byId.get().getHuntJobYn())
                            .introducedLine(byId.get().getIntroducedLine())
                            .Certificates(certiRes)
                            .subject(subjectList)
                            .build())
                    .file(AdminStudentFile.builder()
                            .img(img)
                            .resume(resume)
                            .portfolio(files)
                            .fileLinks(fileLinks)
                            .build())
                    .build();
        } else {
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
    }

    public ResponseEntity<AdminStorageStudentFindRes> selStorage(AdminStorageStudentDto dto, Pageable pageable) {
        long maxPage = adminStudentQdsl.storageIdx(dto);
        PagingUtils utils = new PagingUtils(pageable.getPageNumber() + 1, (int) maxPage, pageable);
        utils.setIdx((int) maxPage);

        List<AdminStorageStudentRes> list = adminStudentQdsl.storageVos(dto, pageable);

        AdminStorageStudentFindRes build = AdminStorageStudentFindRes.builder()
                .page(utils)
                .res(list.stream().map(item -> AdminStorageStudentRes.builder()
                        .istudent(item.getIstudent())
                        .img(String.format("/img/student/%s/%s", item.getIstudent(), item.getImg()))
                        .studentName(item.getStudentName())
                        .subjectName(item.getSubjectName())
                        .companyMainYn(item.getCompanyMainYn())
                        .huntJobYn(item.getHuntJobYn())
                        .storageYn(item.getStorageYn())
                        .build()).toList())
                .build();
        return ResponseEntity.ok(build);
    }

    public ResponseEntity<AdminPortfolioFindRes> selPortfolio(AdminPortfolioDto dto, Pageable pageable) {
        long portfolioIdx = adminStudentQdsl.portfolioIdx(dto);
        PagingUtils utils = new PagingUtils(pageable.getPageNumber() + 1, (int) portfolioIdx, pageable);
        utils.setIdx((int) portfolioIdx);

        List<AdminPortfolioRes> list = adminStudentQdsl.portfolioVos(dto, pageable);

        AdminPortfolioFindRes build = AdminPortfolioFindRes.builder()
                .page(utils)
                .res(list.stream().map(item -> AdminPortfolioRes.builder()
                        .istudent(item.getIstudent())
                        .studentName(item.getStudentName())
                        .subjectName(item.getSubjectName())
                        .huntJobYn(item.getHuntJobYn())
                        .img(String.format("/img/student/%s/%s", item.getIstudent(), item.getImg()))
                        .storageYn(item.getStorageYn())
                        .build()).toList())
                .build();
        return ResponseEntity.ok(build);
    }

    public AdminStudentRoleSelListRes setRoleList() {
        List<AdminStudentRoleSelRes> list = adminStudentQdsl.roleList();

        return AdminStudentRoleSelListRes.builder()
                .res(list)
                .build();
    }


    public AdminStorageStudentPatchRes patchStorage(AdminStorageStudentPatchDto dto) {
        Optional<StudentEntity> byId = STU_REP.findById(dto.getIstudent());

        if (byId.isPresent()) {
            if (dto.getStorageYn() == 1) {
                byId.get().setStorageYn(1);

            } else {
                byId.get().setStorageYn(0);
                byId.get().setCompanyMainYn(0);
            }
            StudentEntity save = STU_REP.save(byId.get());
            return AdminStorageStudentPatchRes.builder()
                    .istudent(save.getIstudent())
                    .storageYn(save.getStorageYn())
                    .companyMainYn(save.getCompanyMainYn())
                    .build();
        } else {
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
    }

    @Transactional
    public List<AdminMainPortfolioPatchRes> patchMain(AdminMainPortfolioPatchDto dto) {
        if (dto.getIstudent() != null) {
            List<StudentEntity> stdList = STU_REP.findAllById(dto.getIstudent());
            List<AdminMainPortfolioPatchRes> resultList = new ArrayList<>();

            for (int i = 0; i <stdList.size(); i++) {
                if (stdList.get(i).getHuntJobYn()==1) {
                    throw new RestApiException(CommonErrorCode.MAIN_FAIL);
                }
            }

            if (!stdList.isEmpty()) {
                for (StudentEntity studentEntity : stdList) {
                    Long rowCount = adminStudentQdsl.rowCount(studentEntity.getCategorySubjectEntity().getIclassification());

                    if (dto.getCompanyMainYn() == 1) {

                        if (rowCount < 10) {
                            studentEntity.setCompanyMainYn(1);

                        } else {
                            throw new RestApiException(CommonErrorCode.COMPANY_MAIN_YN_FAILED);
                        }
                    } else {
                        studentEntity.setCompanyMainYn(0);
                    }
                    StudentEntity savedStudent = STU_REP.save(studentEntity);
                    AdminMainPortfolioPatchRes build = AdminMainPortfolioPatchRes.builder()
                            .istudent(savedStudent.getIstudent())
                            .companyMainYn(savedStudent.getCompanyMainYn())
                            .build();

                    resultList.add(build);
                }
                return resultList;
            }
        } else {
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
        return null;
    }


    public AdminStudentDelRes delStudent(AdminStudentDelDto dto) {
        Optional<StudentEntity> stdId = STU_REP.findById(dto.getIstudent());

        if (stdId.isPresent()) {
            stdId.get().setDelYn(1);
            StudentEntity save = STU_REP.save(stdId.get());

            return AdminStudentDelRes.builder()
                    .istudent(save.getIstudent())
                    .delYn(save.getDelYn())
                    .build();
        } else {
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
    }

    public AdminStudentRoleRes patchRole(AdminStudentRoleDto dto) {
        Optional<CourseSubjectEntity> subjectId = COS_REP.findById(dto.getIcourseSubject());

        List<StudentCourseSubjectEntity> scsList = subjectId.get().getScsList();

        List<StudentEntity> resultList = new ArrayList<>();

        for (StudentCourseSubjectEntity scs : scsList) {
            StudentEntity studentEntity = scs.getStudentEntity();
            studentEntity.setStartedAt(dto.getStartedAt());
            studentEntity.setEndedAt(dto.getEndedAt());

            LocalDate currentDate = LocalDate.now();
            if (currentDate.isAfter(studentEntity.getStartedAt().minusDays(1)) && currentDate.isBefore(studentEntity.getEndedAt())) {
                studentEntity.setEditableYn(1);
            } else {
                studentEntity.setEditableYn(0);
            }

            resultList.add(studentEntity);
        }

        List<StudentEntity> savedStudents = STU_REP.saveAll(resultList);

        StudentEntity selectedStudent = savedStudents.isEmpty() ? new StudentEntity() : savedStudents.get(0);

        return AdminStudentRoleRes.builder()
                .startedAt(selectedStudent.getStartedAt())
                .endedAt(selectedStudent.getEndedAt())
                .editableYn(selectedStudent.getEditableYn())
                .icourseSubject(dto.getIcourseSubject())
                .build();
    }

    @Transactional
    public AdminStudentUpdRes updStudent(AdminStudentUpdDto dto) {
        Optional<StudentEntity> stdId = STU_REP.findById(dto.getIstudent());

        if (stdId.isPresent()) {
            StudentEntity student = stdId.get();

            student.setName(dto.getStudentName());
            student.setId(dto.getEmail());
            student.setAddress(dto.getAddress());
            student.setEducation(dto.getEducation());
            student.setMobileNumber(dto.getMobileNumber());
            student.setHuntJobYn(dto.getHuntJobYn());
            student.setGender(dto.getGender());
            student.setAge(dto.getAge());
            student.setBirthday(LocalDate.now().minusYears(student.getAge()));

            if (dto.getHuntJobYn()==1){
                student.setCompanyMainYn(0);
            }
//            /* TODO 생일변경으로 바꾸기 */
//            String birthday = student.getBirthday().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//            String pwFirst = birthday.substring(2, 3) + birthday.substring(5,6) + birthday.substring(8,9);
//            String phone = student.getMobileNumber();
//            String pwSecond = phone.substring(9);
//
//            student.setPw(PW_ENCODER.encode(pwFirst + pwSecond));


            StudentEntity stdSave = STU_REP.save(student);

            return AdminStudentUpdRes.builder()
                    .istudent(stdSave.getIstudent())
                    .studentName(stdSave.getName())
                    .email(stdSave.getId())
                    .address(stdSave.getAddress())
                    .education(stdSave.getEducation())
                    .mobileNumber(stdSave.getMobileNumber())
                    .huntJobYn(stdSave.getHuntJobYn())
                    .age(stdSave.getAge())
                    .gender(stdSave.getGender())
                    .birthday(stdSave.getBirthday())
                    .build();
        } else {
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
    }

    public AdminStudentCertificateRes updCertificate(AdminStudentCertificateDto dto) {
        Optional<StudentEntity> stdId = STU_REP.findById(dto.getIstudent());

        if (stdId.get() != null) {
            AdminStudentCertificateRes certificateRes = adminStudentQdsl.certificate(dto);

            certificateRes.setIcertificate(dto.getIcertificate());
            certificateRes.setCertificate(dto.getCertificate());

            CertificateEntity entity = new CertificateEntity(certificateRes.getIcertificate(), stdId.get(), certificateRes.getCertificate());

            CertificateEntity save = CERT_REP.save(entity);
            return AdminStudentCertificateRes.builder()
                    .icertificate(save.getIcertificate())
                    .certificate(save.getCertificate())
                    .build();

        } else {
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
    }

    public AdminStudentCertificateRes delCertificate(AdminStudentCertificateDto dto) {
        AdminStudentCertificateRes certificate = adminStudentQdsl.certificate(dto);
        Optional<CertificateEntity> certId = CERT_REP.findById(certificate.getIcertificate());

        if (certId.isPresent()) {
            CertificateEntity entity = certId.get();

            try {
                CERT_REP.delete(entity);
            } catch (Exception e) {
                throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
            }

            return AdminStudentCertificateRes.builder()
                    .icertificate(entity.getIcertificate())
                    .certificate(entity.getCertificate())
                    .build();
        } else {
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
    }

    public AdminStudentFileDelRes delFile(AdminStudentFileDelDto dto) {
        Optional<FileEntity> fileId = FILE_REP.findById(dto.getIfile());
        StudentEntity studentEntity = fileId.get().getStudentEntity();

        if (fileId.isPresent()) {
            FileEntity fileEntity = fileId.get();

            String targetDir = String.format("%s/student/%d", FILE_DIR, fileEntity.getStudentEntity().getIstudent());
            File fileToDelete = new File(String.format("%s/%s", targetDir, fileEntity.getFile()));

            if (fileToDelete.exists()) {
                if (!fileToDelete.delete()) {
                    throw new RestApiException(CommonErrorCode.DELETE_FAILED);
                }
            }

            try {
                FILE_REP.delete(fileEntity);
            } catch (Exception e) {
                throw new RestApiException(CommonErrorCode.DELETE_FAILED);
            }
            if (fileEntity.getFileCategoryEntity().getIFileCategory() == 1) {
                studentEntity.setIntroducedLine("");
                STU_REP.save(studentEntity);
            }

            return AdminStudentFileDelRes.builder()
                    .ifile(fileEntity.getIfile())
                    .build();
        } else {
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }

    }


    public AdminStudentPortfolioMainRes patchPortfolioMain(AdminStudentPortfolioMainDto dto) {
        Optional<StudentEntity> stdId = STU_REP.findById(dto.getIstudent());
        List<FileEntity> files = stdId.get().getFiles();

        AdminFileMainVo adminFileMainVo = adminStudentQdsl.mainFile(stdId.get());

        if (stdId.isPresent()) {
                Long countByPortfolioMain = adminStudentQdsl.countByPortfolioMain(stdId.get().getIstudent());

            if (countByPortfolioMain < 1) {
                    Optional<FileEntity> fileId = FILE_REP.findById(dto.getIfile());
                    FileEntity fileEntity = new FileEntity();

                    fileEntity = fileId.get();
                    fileEntity.setMainYn(1);

                    FileEntity save = FILE_REP.save(fileEntity);

                    return AdminStudentPortfolioMainRes.builder()
                            .mainYn(save.getMainYn())
                            .ifile(save.getIfile())
                            .istudent(stdId.get().getIstudent())
                            .build();

                } else if (countByPortfolioMain == 1 && (adminFileMainVo.getIfile().equals(dto.getIfile()))) {
                    Optional<FileEntity> fileId = FILE_REP.findById(dto.getIfile());
                    FileEntity fileEntity = new FileEntity();

                    fileEntity = fileId.get();
                    fileEntity.setMainYn(0);

                    FileEntity save = FILE_REP.save(fileEntity);

                    return AdminStudentPortfolioMainRes.builder()
                            .mainYn(save.getMainYn())
                            .ifile(save.getIfile())
                            .istudent(stdId.get().getIstudent())
                            .build();
                } else {
                    throw new RestApiException(CommonErrorCode.MAIN_YN_FAILED);
                }

        } else {
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }

    }

    public AdminStudentCertificateTotalRes updCertificateList(AdminStudentCertificateListDto dto) {
        Optional<StudentEntity> stdId = STU_REP.findById(dto.getIstudent());
        List<String> dtoList = dto.getCertificate();

        if (stdId.isPresent()) {
            // 만약에 자격증 n개가 들어가 있고
            List<CertificateEntity> certificates = stdId.get().getCertificates();

            // dto가 기존 엔티티보다 사이즈가 크다면? 새로 수정한다
            if (certificates.size() <= dtoList.size()) {
                for (int i = 0; i < dtoList.size(); i++) {
                    if (i < certificates.size()) {
                        certificates.get(i).setCertificate(dtoList.get(i));
                    } else {
                        CertificateEntity entity = new CertificateEntity();
                        entity.setCertificate(dtoList.get(i));
                        entity.setStudentEntity(stdId.get());

                        certificates.add(entity);
                    }

                }
                List<CertificateEntity> save = CERT_REP.saveAll(certificates);

                return AdminStudentCertificateTotalRes.builder()
                        .res(save.stream().map(item -> AdminStudentCertificateRes.builder()
                                .certificate(item.getCertificate())
                                .icertificate(item.getIcertificate())
                                .build()).toList())
                        .istudent(stdId.get().getIstudent())
                        .build();
            } else {
                List<CertificateEntity> toRemove = certificates.subList(dtoList.size(), certificates.size());
                certificates.removeAll(toRemove);

                for (int i = 0; i < certificates.size(); i++) {
                    if (i < dtoList.size()) {
                        certificates.get(i).setCertificate(dtoList.get(i));
                    } else {
                        // dtoList 크기보다 certificates가 더 큰 경우 해당 항목 제거
                        certificates.remove(i);
                        i--; // 리스트 크기가 줄었으므로 인덱스 감소
                    }
                }
                List<CertificateEntity> save = CERT_REP.saveAll(certificates);

                return AdminStudentCertificateTotalRes.builder()
                        .res(save.stream().map(item -> AdminStudentCertificateRes.builder()
                                .certificate(item.getCertificate())
                                .icertificate(item.getIcertificate())
                                .build()).toList())
                        .istudent(stdId.get().getIstudent())
                        .build();
            }
        } else {
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
    }

    public AdminStudentSubjectCategoryRes selSubjectCategoryList(AdminStudentCategoryDto dto, Pageable pageable) {
        long maxPage = adminStudentQdsl.subjectCategoryCount(dto);
        PagingUtils utils = new PagingUtils(pageable.getPageNumber() + 1, (int) maxPage, pageable);
        utils.setIdx((int) maxPage);

        List<AdminStudentSubjectCategoryListRes> adminStudentSubjectCategoryRes = adminStudentQdsl.subjectCategoryList(dto, pageable);

        return AdminStudentSubjectCategoryRes.builder()
                .page(utils)
                .subject(adminStudentSubjectCategoryRes)
                .build();
    }

    public AdminStudentDelListRes delStudentList(AdminStudentDelDto dto) {
        Optional<CategorySubjectEntity> cateId = A_CATE_REP.findById(dto.getIclassification());

        if (cateId.isPresent()) {
            List<CourseSubjectEntity> subjectList = cateId.get().getCsList();
            for (CourseSubjectEntity subject : subjectList) {
                if (subject.getIcourseSubject() == dto.getIcourseSubject()) {
                    long differenceInYears = ChronoUnit.YEARS.between(subject.getEndedAt(), LocalDate.now());
                    List<StudentCourseSubjectEntity> scsList = subject.getScsList();
                    for (StudentCourseSubjectEntity scs : scsList) {
                        StudentEntity studentEntity = scs.getStudentEntity();
                        List<FileEntity> files = studentEntity.getFiles();
                        if (differenceInYears <= 1 && files.isEmpty()) {
                            log.info("1년 미만의 데이터이므로 삭제를 진행합니다.");
                            STU_REP.delete(studentEntity);
                        }
                    }
                }
            }

        } else {
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }

        return AdminStudentDelListRes.builder()
                .iclassification(dto.getIclassification())
                .icourseSubject(dto.getIcourseSubject())
                .build();
    }

    public AdminStudentOneYearRes getStudentOneYear(Pageable pageable, Long iclassification, Long icourseSubject, String studentName) {
        long count = adminStudentQdsl.oneYearStudentCount(iclassification, icourseSubject, studentName);

        PagingUtils utils = new PagingUtils(pageable.getPageNumber() + 1, (int) count, pageable);
        utils.setIdx((int) count);

        List<AdminStudentOneYearVo> studentOneYearlist = adminStudentQdsl.oneYearStudent(pageable, iclassification, icourseSubject, studentName);

        List<AdminStudentOneYearFileVo> list = studentOneYearlist.stream().map(item -> AdminStudentOneYearFileVo.builder()
                .classification(item.getClassification())
                .subjectName(item.getSubjectName())
                .round(item.getRound())
                .startedAt(item.getStartedAt())
                .endedAt(item.getEndedAt())
                .istudent(item.getIstudent())
                .studentName(item.getStudentName())
                .gender(item.getGender())
                .portfolio(adminStudentQdsl.fileCount(item.getIstudent()))
                .storageYn(item.getStorageYn()).build()).toList();
        return AdminStudentOneYearRes.builder().page(utils).vo(list).build();

    }

    @Transactional
    public int delStudentOneYear(List<Long> istudent) {
        String fileDir = MyFileUtils.getAbsolutePath(FILE_DIR);

        //포트폴리오 삭제
        for (Long student : istudent) {
            //학생 찾기
            StudentEntity studentEntity = STU_REP.findById(student).get();
            //학생 포트폴리오삭제
            List<FileEntity> allByStudentEntity = FILE_REP.findAllByStudentEntity(studentEntity);
            for (FileEntity fileEntity : allByStudentEntity) {

                log.info("file:{}", fileEntity.getFile());
                String targetDir = String.format("%s/student/%d", MyFileUtils.getAbsolutePath(fileDir), fileEntity.getStudentEntity().getIstudent());
                log.info("targetDir:{}", targetDir);
                File fileToDelete = new File(String.format("%s/%s", targetDir, fileEntity.getFile()));
                log.info("fileToDelete:{}", fileToDelete);
                FILE_REP.delete(fileEntity);

                fileToDelete.delete();

                if (fileToDelete.exists()) {
                    if (!fileToDelete.delete()) {
                        throw new RestApiException(CommonErrorCode.DELETE_FAILED);
                    }
                }

            }


            // 빈폴더 삭제
            String format = String.format("%s/student/%d", MyFileUtils.getAbsolutePath(fileDir), studentEntity.getIstudent());
            log.info("format:{}", format);
            File emptyDirectoryFile = new File(format);
            emptyDirectoryFile.delete();


            //학생 수강과목 삭제
            List<StudentCourseSubjectEntity> StudentEntity = SCS_REP.findByStudentEntity(studentEntity);
            for (StudentCourseSubjectEntity subject : StudentEntity) {
                SCS_REP.delete(subject);
            }

            //학생 자격증 삭제
            List<CertificateEntity> CertificateStudentEntity = CERT_REP.findByStudentEntity(studentEntity);
            for (CertificateEntity subject : CertificateStudentEntity) {
                CERT_REP.delete(subject);
            }

            //학생 삭제
            STU_REP.delete(studentEntity);
            log.info("studentEntity:{}", studentEntity.getId());

        }
        return 1;

    }

    public AdminStudentCertificateListRes insCertificates(StudentCertificateDto dto) {
        Optional<StudentEntity> stdId = STU_REP.findById(dto.getIstudent());
        if (stdId.isPresent()) {
            List<CertificateEntity> certificates = new ArrayList<>();
            List<CertificateRes> resultList = new ArrayList<>();

            StudentEntity student = stdId.get();

            for (int i = 0; i < dto.getCertificates().size(); i++) {
                CertificateEntity entity = new CertificateEntity();
                entity.setCertificate(dto.getCertificates().get(i));
                entity.setStudentEntity(student);

                CertificateEntity save = CERT_REP.save(entity);

                CertificateRes certRes = CertificateRes.builder()
                        .icertificate(save.getIcertificate())
                        .certificate(save.getCertificate())
                        .build();
                resultList.add(certRes);
                certificates.add(save);
            }

            student.setCertificates(certificates);


            return AdminStudentCertificateListRes.builder()
                    .istudent(student.getIstudent())
                    .res(resultList)
                    .build();
        } else {
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
    }

    public AdminStudentSubjectDropBoxRes selSubjectDropBox(AdminStudentSubjectDropBoxDto dto) {
        List<AdminStudentSubjectDropBox> dropBox = adminStudentQdsl.selSubjectDropBox(dto);

        return AdminStudentSubjectDropBoxRes.builder()
                .res(dropBox)
                .build();
    }


}

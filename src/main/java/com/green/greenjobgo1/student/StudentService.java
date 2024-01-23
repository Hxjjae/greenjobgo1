package com.green.greenjobgo1.student;

import com.green.greenjobgo1.admin.companylist.model.CompanylistRes;
import com.green.greenjobgo1.admin.std_management.model.*;
import com.green.greenjobgo1.common.entity.*;
import com.green.greenjobgo1.common.security.config.exception.CommonErrorCode;
import com.green.greenjobgo1.common.security.config.exception.RestApiException;
import com.green.greenjobgo1.common.utils.MyFileUtils;
import com.green.greenjobgo1.common.utils.PagingUtils;
import com.green.greenjobgo1.repository.*;
import com.green.greenjobgo1.student.model.*;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository STU_REP;
    private final FileRepository FILE_REP;
    private final FileCategoryRepository FILE_CATE_REP;
    private final CertificateRepository CERT_REP;
    private final EmployeeProfileRepository EMPL_REP;
    private final StudentQdsl studentQdsl;

    @Value("${file.dir}")
    private String FILE_DIR;


    @Transactional
    public StudentInsTotalRes insFile(MultipartFile file, StudentInsDto dto) {
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

        StudentEntity studentEntity = stdId.get();
        if (dto.getIFileCategory() == 1) {
            studentEntity.setIstudent(dto.getIstudent());
            studentEntity.setIntroducedLine(dto.getIntroducedLine());
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
                    Long resumeCount = studentQdsl.countByResume(studentSave.getIstudent());

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
                    Long thumbnailCount = studentQdsl.countByThumbnail(studentSave.getIstudent());
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
                Long fileCount = studentQdsl.countByFile(studentSave.getIstudent());

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

                        // PDF 파일로 저장
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
                Long fileLinkCount = studentQdsl.countByFileLink(studentSave.getIstudent());
                if (fileLinkCount > 5) {
                    throw new RestApiException(CommonErrorCode.UPLOAD_FAILED);
                } else {
                    savedFileNm = (dto.getFileLink() != null) ? dto.getFileLink() : null;
                }
            }


            StudentInsRes res = StudentInsRes.builder()
                    .file(result.getFile())
                    .ifile(result.getIfile())
                    .createdAt(result.getCreatedAt())
                    .istudent(result.getStudentEntity().getIstudent())
                    .oneWord(result.getOneWord())
                    .build();

            StudentIntroducedLineRes std = StudentIntroducedLineRes.builder()
                    .introducedLine(studentSave.getIntroducedLine())
                    .build();

            return StudentInsTotalRes.builder()
                    .res(res)
                    .std(std)
                    .build();
        }
        return null;
    }

    public StudentDelRes delFile(StudentDelDto dto) {
        Optional<FileEntity> fileId = FILE_REP.findById(dto.getIfile());

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
            return StudentDelRes.builder()
                    .ifile(fileEntity.getIfile())
                    .build();
        } else {
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
    }

    public StudentSelTotalRes selStudent(StudentSelDto dto) {
        Optional<StudentEntity> stdId = STU_REP.findById(dto.getIstudent());

        StudentImg img = studentQdsl.img(dto);
        StudentResume resume = studentQdsl.resume(dto);
        List<StudentFileRes> files = studentQdsl.fileVos(dto);
        List<StudentFileLink> fileLinks = studentQdsl.fileLinks(dto);
        List<StudentCertificateSelRes> certificateRes = studentQdsl.certificateRes(dto.getIstudent());
        StudentDetailSubjectRes subjectList = studentQdsl.subjectList(dto.getIstudent());
        Long countByFileAndFileLink = studentQdsl.countByFileAndFileLink(dto.getIstudent());
        Long countByResume = studentQdsl.countByResume(dto.getIstudent());

        if (stdId.isPresent()) {
            return StudentSelTotalRes.builder()
                    .std(StudentSelStudentRes.builder()
                            .istudent(stdId.get().getIstudent())
                            .age(stdId.get().getAge())
                            .gender(stdId.get().getGender())
                            .name(stdId.get().getName())
                            .startedAt(stdId.get().getStartedAt())
                            .endedAt(stdId.get().getEndedAt())
                            .birthday(stdId.get().getBirthday())
                            .address(stdId.get().getAddress())
                            .education(stdId.get().getEducation())
                            .email(stdId.get().getId())
                            .mobileNumber(stdId.get().getMobileNumber())
                            .huntJobYn(stdId.get().getHuntJobYn())
                            .introducedLine(stdId.get().getIntroducedLine())
                            .Certificates(certificateRes)
                            .subject(subjectList)
                            .build())
                    .file(StudentSelFileRes.builder()
                            .img(img)
                            .resume(resume)
                            .portfolio(files)
                            .fileLinks(fileLinks)
                            .build())
                    .portfolioYn((countByFileAndFileLink > 0) ? 1 : 0)
                    .aboutMeYn((countByResume > 0) ? 1 : 0)
                    .build();
        } else {
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
    }

    public StudentCertificateRes insCertificates(StudentCertificateDto dto) {
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


            return StudentCertificateRes.builder()
                    .istudent(student.getIstudent())
                    .res(resultList)
                    .build();
        } else {
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
    }


    public StudentCompanyListVo companyList(String companyName, Pageable pageable) {
        long count = studentQdsl.companyIdx(companyName);
        PagingUtils utils = new PagingUtils(pageable.getPageNumber() + 1, (int) count, pageable, 12);
        utils.setIdx((int) count);

        List<CompanyListEntity> companyList = studentQdsl.companyList(companyName, pageable);

        List<CompanylistRes> list = companyList.stream().map(item ->
                CompanylistRes.builder().companyCode(item.getCompanyCode())
                        .companyName(item.getCompanyName())
                        .area(item.getArea())
                        .leaderName(item.getLeaderName())
                        .homepage(item.getHomepage())
                        .dateConslusion(item.getDateConslusion())
                        .manager(item.getManager())
                        .phoneNumber(item.getPhoneNumber()).build()).toList();

        return StudentCompanyListVo.builder()
                .list(list)
                .page(utils)
                .build();

    }

    public StudentCertificateTotalRes updCertificateList(StudentCertificateListDto dto) {
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

                return StudentCertificateTotalRes.builder()
                        .res(save.stream().map(item -> CertificateRes.builder()
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

                return StudentCertificateTotalRes.builder()
                        .res(save.stream().map(item -> CertificateRes.builder()
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

    public StudentHuntJobRes patchHuntJob(StudentHuntJobDto dto) {
        Optional<StudentEntity> stdId = STU_REP.findById(dto.getIstudent());

        if (stdId.isPresent()) {
            StudentEntity entity = stdId.get();
            entity.setHuntJobYn(dto.getHuntJobYn());

            StudentEntity save = STU_REP.save(entity);

            return StudentHuntJobRes.builder()
                    .istudent(save.getIstudent())
                    .huntJobYn(save.getHuntJobYn())
                    .build();
        } else {
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
    }

    public StudentPortfolioMainRes patchPortfolioMain(StudentPortfolioMainDto dto) {
        Optional<StudentEntity> stdId = STU_REP.findById(dto.getIstudent());
        List<FileEntity> files = stdId.get().getFiles();

        if (stdId.isPresent()) {
            for (FileEntity file : files) {
                Long countByPortfolioMain = studentQdsl.countByPortfolioMain(dto.getIstudent());

                if (countByPortfolioMain < 1) {
                    Optional<FileEntity> fileId = FILE_REP.findById(dto.getIfile());
                    FileEntity fileEntity = new FileEntity();


                    fileEntity = fileId.get();
                    fileEntity.setMainYn(1);

                    FileEntity save = FILE_REP.save(fileEntity);

                    return StudentPortfolioMainRes.builder()
                            .mainYn(save.getMainYn())
                            .ifile(save.getIfile())
                            .istudent(stdId.get().getIstudent())
                            .build();

                } else if (countByPortfolioMain == 1) {
                    Optional<FileEntity> fileId = FILE_REP.findById(dto.getIfile());
                    FileEntity fileEntity = new FileEntity();

                    fileEntity = fileId.get();
                    fileEntity.setMainYn(0);

                    FileEntity save = FILE_REP.save(fileEntity);

                    return StudentPortfolioMainRes.builder()
                            .mainYn(save.getMainYn())
                            .ifile(save.getIfile())
                            .istudent(stdId.get().getIstudent())
                            .build();

                } else {
                    throw new RestApiException(CommonErrorCode.MAIN_YN_FAILED);
                }
            }
        } else {
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
        return null;
    }

    public CertificateRes delCertificate(StudentCertificateDto dto) {
        CertificateRes certificate = studentQdsl.certificate(dto);
        Optional<CertificateEntity> certId = CERT_REP.findById(certificate.getIcertificate());

        if (certId.isPresent()) {
            CertificateEntity entity = certId.get();

            try {
                CERT_REP.delete(entity);
            } catch (Exception e) {
                throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
            }

            return CertificateRes.builder()
                    .icertificate(entity.getIcertificate())
                    .certificate(entity.getCertificate())
                    .build();
        } else {
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
    }
}

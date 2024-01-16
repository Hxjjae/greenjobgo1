package com.green.greenjobgo1.admin.std_management;

import com.green.greenjobgo1.admin.std_management.model.*;
import com.green.greenjobgo1.common.entity.*;
import com.green.greenjobgo1.common.utils.MyFileUtils;
import com.green.greenjobgo1.common.utils.PagingUtils;
import com.green.greenjobgo1.company.CompanyService;
import com.green.greenjobgo1.repository.*;
import com.green.greenjobgo1.common.security.config.security.MyUserDetailsServiceImpl;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
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

            String targetDir = String.format("%s/student/%d", FILE_DIR, entity.getStudentEntity().getIstudent());
            File fileTargetDir = new File(targetDir);

            if (!fileTargetDir.exists()) {
                if (!fileTargetDir.mkdirs()) {
                    String errorMessage = "디렉토리를 생성할 수 없습니다. 경로: " + fileTargetDir.getAbsolutePath();
                    log.error(errorMessage);
                    throw new RuntimeException(errorMessage);
                }
            }

            if (iFileCategory == 1 || iFileCategory == 4) {
                File fileTarget = new File(String.format("%s/%s", targetDir, savedFileNm));
                try {
                    file.transferTo(fileTarget);
                } catch (IOException e) {
                    throw new RuntimeException("파일을 업로드 할 수 없습니다.");
                }
            } else if (iFileCategory == 2) {
                try {
                    String fileDir = MyFileUtils.getAbsolutePath(FILE_DIR);
                    String centerPath = String.format("%s/student/%d", MyFileUtils.getAbsolutePath(fileDir), entity.getStudentEntity().getIstudent() );
                    String originFileName = file.getOriginalFilename();
                    String savedFileName = MyFileUtils.makeRandomFileNm(originFileName);
                    String savedFilePath = String.format("%s/%s",centerPath, savedFileName);

                    //MultipartFile 타입의 pic을 -> pdf 파일로 변환 해서 -> pdf파일에 대한 권한을 제한한다.
                    // MultipartFile pic 을 PDF를 읽어오기
                    InputStream inputStream = file.getInputStream();
                    PdfReader pdfReader = new PdfReader(inputStream);

                    // 읽기 전용으로 만들기 위한 객체 생성
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                    PdfStamper pdfStamper = new PdfStamper(pdfReader, outputStream);
                    // 모든 권한 제거하기 null은 사용자한테 비밀번호를 받지 않겠다는 뜻, 여기서 0은 모든 권한을 비활성화(제한)하겠다는 뜻
                    pdfStamper.setEncryption(null, null, 0, PdfWriter.ENCRYPTION_AES_128);
                    //인쇄만 가능하도록 제한하기 PdfWriter.ALLOW_PRINTING을 주면 인쇄만 가능
//        pdfStamper.setEncryption(null, "read-only-password".getBytes(),
//                PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);

                    pdfStamper.close();

                    // PDF 파일로 저장
                    try (FileOutputStream fileOutputStream = new FileOutputStream(savedFilePath)) {
                        fileOutputStream.write(outputStream.toByteArray());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    throw new RuntimeException("파일을 업로드 할 수 없습니다. " + e);
                } catch (DocumentException e) {
                    throw new RuntimeException("파일을 업로드 할 수 없습니다. " + e);
                }
            } else if (iFileCategory == 3) {
                savedFileNm = (dto.getFileLink() != null) ? dto.getFileLink() : null;
            }
            if (iFileCategory == 1) {
                Long resumeCount = adminStudentQdsl.countByResume(studentSave.getIstudent());

                if (resumeCount > 1) {
                    throw new RuntimeException("한 수강생당 이력서는 1개까지만 올릴 수 있습니다.");
                }
            }

            if (iFileCategory == 2) {
                Long fileCount = adminStudentQdsl.countByFile(studentSave.getIstudent());

                if (fileCount > 5) {
                    throw new RuntimeException("한 수강생당 파일은 5개까지만 올릴 수 있습니다.");
                }
            }

            if (iFileCategory == 3) {
                Long fileLinkCount = adminStudentQdsl.countByFileLink(studentSave.getIstudent());
                if (fileLinkCount > 5) {
                    throw new RuntimeException("한 수강생당 파일링크는 5개까지만 올릴 수 있습니다.");
                }

            }


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
                            .startedAt(byId.get().getStartedAt())
                            .endedAt(byId.get().getEndedAt())
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
            throw new EntityNotFoundException("찾을 수 없는 pk 입니다.");
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
            }
            StudentEntity save = STU_REP.save(byId.get());
            return AdminStorageStudentPatchRes.builder()
                    .istudent(save.getIstudent())
                    .storageYn(save.getStorageYn())
                    .build();
        } else {
            throw new EntityNotFoundException("찾을 수 없는 pk 입니다.");
        }
    }

    @Transactional
    public List<AdminMainPortfolioPatchRes> patchMain(AdminMainPortfolioPatchDto dto) {
        if (dto.getIstudent() != null) {
            List<StudentEntity> stdList = STU_REP.findAllById(dto.getIstudent());
            List<AdminMainPortfolioPatchRes> resultList = new ArrayList<>();

            if (!stdList.isEmpty()) {
                for (StudentEntity studentEntity : stdList) {
                    Long rowCount = adminStudentQdsl.rowCount(studentEntity.getCategorySubjectEntity().getIclassification());

                    if (dto.getCompanyMainYn() == 1) {

                        if (rowCount < 10) {
                            studentEntity.setCompanyMainYn(1);

                        } else {
                            throw new RuntimeException("특정 카테고리의 row 값은 이미 10개입니다.");
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
            throw new EntityNotFoundException("찾을 수 없는 Pk 입니다.");
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
            throw new EntityNotFoundException("찾을 수 없는 pk 입니다.");
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
            if (currentDate.isAfter(studentEntity.getStartedAt()) && currentDate.isBefore(studentEntity.getEndedAt())) {
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
            student.setAge(dto.getAge());
            student.setGender(dto.getGender());

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
                    .build();
        } else {
            throw new EntityNotFoundException("찾을 수 없는 pk 입니다.");
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
            throw new EntityNotFoundException("찾을 수 없는 pk 입니다.");
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
                throw new RuntimeException("데이터베이스에서 데이터를 지울 수 없습니다.");
            }

            return AdminStudentCertificateRes.builder()
                    .icertificate(entity.getIcertificate())
                    .certificate(entity.getCertificate())
                    .build();
        } else {
            throw new EntityNotFoundException("찾을 수 없는 pk 입니다.");
        }
    }

    public AdminStudentFileDelRes delFile(AdminStudentFileDelDto dto) {
        Optional<FileEntity> fileId = FILE_REP.findById(dto.getIfile());

        if (fileId.isPresent()) {
            FileEntity fileEntity = fileId.get();

            String targetDir = String.format("%s/student/%d", FILE_DIR, fileEntity.getStudentEntity().getIstudent());
            File fileToDelete = new File(String.format("%s/%s", targetDir, fileEntity.getFile()));

            if (fileToDelete.exists()) {
                if (!fileToDelete.delete()) {
                    throw new RuntimeException("파일을 저장한 곳에서 삭제할 수 없습니다.");
                }
            }

            try {
                FILE_REP.delete(fileEntity);
            } catch (Exception e) {
                throw new RuntimeException("데이터베이스에서 파일 엔터티를 삭제할 수 없습니다.");
            }
            return AdminStudentFileDelRes.builder()
                    .ifile(fileEntity.getIfile())
                    .build();
        } else {
            throw new RuntimeException("ID에 해당하는 파일이 존재하지 않습니다: " + dto.getIfile());
        }

    }



    public AdminStudentPortfolioMainRes patchPortfolioMain(AdminStudentPortfolioMainDto dto) {
        Optional<StudentEntity> stdId = STU_REP.findById(dto.getIstudent());

        if (stdId.isPresent()) {
            Optional<FileEntity> fileId = FILE_REP.findById(dto.getIfile());
            FileEntity fileEntity = new FileEntity();

            if (dto.getMainYn() != null) {
                fileEntity = fileId.get();
                fileEntity.setMainYn(dto.getMainYn());
            }
            FileEntity save = FILE_REP.save(fileEntity);

            return AdminStudentPortfolioMainRes.builder()
                    .mainYn(save.getMainYn())
                    .ifile(save.getIfile())
                    .istudent(stdId.get().getIstudent())
                    .build();

        } else {
            throw new EntityNotFoundException("찾을 수 없는 Pk 입니다.");
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
            throw new EntityNotFoundException("찾을 수 없는 pk 입니다.");
        }
    }

    public AdminStudentCategoryRes selCategory(AdminStudentCategoryDto dto) {


        return null;
    }
}

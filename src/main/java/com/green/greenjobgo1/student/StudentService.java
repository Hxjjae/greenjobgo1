package com.green.greenjobgo1.student;

import com.green.greenjobgo1.admin.companylist.model.CompanylistRes;
import com.green.greenjobgo1.admin.std_management.model.AdminStudentCertificateRes;
import com.green.greenjobgo1.admin.std_management.model.AdminStudentInsRes;
import com.green.greenjobgo1.admin.std_management.model.AdminStudentInsTotalRes;
import com.green.greenjobgo1.admin.std_management.model.AdminStudentIntroducedLineRes;
import com.green.greenjobgo1.common.entity.*;
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


    //    public StudentInsTotalRes insFile(MultipartFile file, StudentInsDto dto) {
//        Optional<FileCategoryEntity> fileCateId = FILE_CATE_REP.findById(dto.getIFileCategory());
//        Optional<StudentEntity> stdId = STU_REP.findById(dto.getIstudent());
//
//        FileEntity entity = new FileEntity();
//        entity.setFileCategoryEntity(fileCateId.get());
//        entity.setCreatedAt(LocalDate.now());
//        entity.setStudentEntity(stdId.get());
//
//        StudentEntity studentEntity = new StudentEntity();
//        studentEntity.setIntroducedLine(dto.getIntroducedLine());
//
//        StudentEntity studentSave = studentEntity;
//        if (dto.getIntroducedLine() != null) {
//            studentSave = STU_REP.save(studentEntity);
//        }
//
//        if (fileCateId.get().getIFileCategory() == 1) {
//            String savedFileNm = MyFileUtils.makeRandomFileNm(file.getOriginalFilename());
//            entity.setFile(savedFileNm);
//            FileEntity result = FILE_REP.save(entity);
//
//            String targetDir = String.format("%s/student/%d", fileDir, entity.getStudentEntity().getIstudent());
//            File fileTargetDir = new File(targetDir);
//            if (!fileTargetDir.exists()) {
//                if (!fileTargetDir.mkdirs()) {
//                    log.error("Failed to create directory: {}", fileTargetDir.getAbsolutePath());
//                    throw new RuntimeException("이력서를 저장할 디렉토리를 생성할 수 없습니다.");
//                }
//            }
//            File fileTarget = new File(String.format("%s/%s", targetDir, savedFileNm));
//            try {
//                file.transferTo(fileTarget);
//            } catch (IOException e) {
//                e.printStackTrace();
//                throw new RuntimeException("이력서를 업로드 할 수 없습니다.");
//            }
//            StudentInsRes res = StudentInsRes.builder()
//                    .file(result.getFile())
//                    .ifile(result.getIfile())
//                    .createdAt(result.getCreatedAt())
//                    .istudent(result.getStudentEntity().getIstudent())
//                    .build();
//            StudentIntroducedLineRes std = StudentIntroducedLineRes.builder()
//                    .introducedLine(studentSave.getIntroducedLine())
//                    .build();
//            return StudentInsTotalRes.builder()
//                    .res(res)
//                    .std(std)
//                    .build();
//        } else if (fileCateId.get().getIFileCategory() == 2) {
//            String savedFileNm = MyFileUtils.makeRandomFileNm(file.getOriginalFilename());
//            entity.setFile(savedFileNm);
//            FileEntity result = FILE_REP.save(entity);
//
//            String targetDir = String.format("%s/student/%d", fileDir, entity.getStudentEntity().getIstudent());
//            File fileTargetDir = new File(targetDir);
//            if (!fileTargetDir.exists()) {
//                if (!fileTargetDir.mkdirs()) {
//                    log.error("Failed to create directory: {}", fileTargetDir.getAbsolutePath());
//                    throw new RuntimeException("포트폴리오를 저장할 디렉토리를 생성할 수 없습니다.");
//                }
//            }
//            File fileTarget = new File(String.format("%s/%s", targetDir, savedFileNm));
//            try {
//                file.transferTo(fileTarget);
//            } catch (IOException e) {
//                e.printStackTrace();
//                throw new RuntimeException("포트폴리오를 업로드 할 수 없습니다.");
//            }
//            StudentInsRes res = StudentInsRes.builder()
//                    .file(result.getFile())
//                    .ifile(result.getIfile())
//                    .createdAt(result.getCreatedAt())
//                    .istudent(result.getStudentEntity().getIstudent())
//                    .build();
//            StudentIntroducedLineRes std = StudentIntroducedLineRes.builder()
//                    .introducedLine(studentSave.getIntroducedLine())
//                    .build();
//            return StudentInsTotalRes.builder()
//                    .res(res)
//                    .std(std)
//                    .build();
//        } else if (fileCateId.get().getIFileCategory() == 3 && dto.getFileLink() != null) {
//            String savedFileNm = dto.getFileLink();
//            entity.setFile(savedFileNm);
//            FileEntity result = FILE_REP.save(entity);
//
//            StudentInsRes res = StudentInsRes.builder()
//                    .file(result.getFile())
//                    .ifile(result.getIfile())
//                    .createdAt(result.getCreatedAt())
//                    .istudent(result.getStudentEntity().getIstudent())
//                    .build();
//            StudentIntroducedLineRes std = StudentIntroducedLineRes.builder()
//                    .introducedLine(studentSave.getIntroducedLine())
//                    .build();
//            return StudentInsTotalRes.builder()
//                    .res(res)
//                    .std(std)
//                    .build();
//        } else if (fileCateId.get().getIFileCategory() == 4) {
//            String savedFileNm = MyFileUtils.makeRandomFileNm(file.getOriginalFilename());
//            entity.setFile(savedFileNm);
//            FileEntity result = FILE_REP.save(entity);
//
//            String targetDir = String.format("%s/student/%d", fileDir, entity.getStudentEntity().getIstudent());
//            File fileTargetDir = new File(targetDir);
//            if (!fileTargetDir.exists()) {
//                if (!fileTargetDir.mkdirs()) {
//                    log.error("Failed to create directory: {}", fileTargetDir.getAbsolutePath());
//                    throw new RuntimeException("대표 이미지를 저장할 디렉토리를 생성할 수 없습니다.");
//                }
//            }
//            File fileTarget = new File(String.format("%s/%s", targetDir, savedFileNm));
//            try {
//                file.transferTo(fileTarget);
//            } catch (IOException e) {
//                e.printStackTrace();
//                throw new RuntimeException("이미지를 업로드 할 수 없습니다.");
//            }
//            StudentInsRes res = StudentInsRes.builder()
//                    .file(result.getFile())
//                    .ifile(result.getIfile())
//                    .createdAt(result.getCreatedAt())
//                    .istudent(result.getStudentEntity().getIstudent())
//                    .build();
//            StudentIntroducedLineRes std = StudentIntroducedLineRes.builder()
//                    .introducedLine(studentSave.getIntroducedLine())
//                    .build();
//            return StudentInsTotalRes.builder()
//                    .res(res)
//                    .std(std)
//                    .build();
//        }
//        return null;
//    }

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
                Long resumeCount = studentQdsl.countByResume(studentSave.getIstudent());

                if (resumeCount > 1) {
                    throw new RuntimeException("한 수강생당 이력서는 1개까지만 올릴 수 있습니다.");
                }
            }

            if (iFileCategory == 2) {
                Long fileCount = studentQdsl.countByFile(studentSave.getIstudent());

                if (fileCount > 5) {
                    throw new RuntimeException("한 수강생당 파일은 5개까지만 올릴 수 있습니다.");
                }
            }

            if (iFileCategory == 3) {
                Long fileLinkCount = studentQdsl.countByFileLink(studentSave.getIstudent());
                if (fileLinkCount > 5) {
                    throw new RuntimeException("한 수강생당 파일링크는 5개까지만 올릴 수 있습니다.");
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
                    throw new RuntimeException("파일을 저장한 곳에서 삭제할 수 없습니다.");
                }
            }

            try {
                FILE_REP.delete(fileEntity);
            } catch (Exception e) {
                throw new RuntimeException("데이터베이스에서 파일 엔터티를 삭제할 수 없습니다.");
            }
            return StudentDelRes.builder()
                    .ifile(fileEntity.getIfile())
                    .build();
        } else {
            throw new RuntimeException("ID에 해당하는 파일이 존재하지 않습니다: " + dto.getIfile());
        }
    }





//    public StudentPatchTotalRes patchFile(MultipartFile file, StudentPatchDto dto) {
//        Optional<FileCategoryEntity> fileCateId = FILE_CATE_REP.findById(dto.getIFileCategory());
//        Optional<StudentEntity> stdId = STU_REP.findById(dto.getIstudent());
//        List<FileEntity> fileAll = FILE_REP.findAllByStudentEntity(stdId.get());
//
//        FileEntity entity = new FileEntity();
//
//        StudentEntity studentEntity = new StudentEntity();
//        studentEntity.setIntroducedLine(dto.getIntroducedLine());
//
//        StudentEntity studentSave = studentEntity;
//        if (dto.getIntroducedLine() != null) {
//            studentSave = STU_REP.save(studentEntity);
//        }
//
//        for (FileEntity fileEntity : fileAll) {
//            if (fileCateId.isPresent()) {
//                if (fileCateId.get().getIFileCategory() == 1) {
//                    FileEntity tempEntity = new FileEntity();
//                    FileEntity saveEntity = null;
//                    tempEntity = fileEntity;
//                    saveEntity = tempEntity;
//
//                    String savedFileNm = MyFileUtils.makeRandomFileNm(file.getOriginalFilename());
//                    entity.setFile(savedFileNm);
//                    FileEntity result = FILE_REP.save(entity);
//
//                    String targetDir = String.format("%s/student/%d", fileDir, entity.getStudentEntity().getIstudent());
//                    File fileTarget = new File(String.format("%s/%s", targetDir, savedFileNm));
//                    File tempFile = new File(targetDir);
//                    if (tempFile.exists()) {
//                        tempFile.delete();
//                    }
//                    fileEntity.setFile(null);
//                    try {
//                        FILE_REP.save(fileEntity);
//                    } catch (Exception e) {
//                        throw new RuntimeException("이력서 삭제 실패");
//                    }
//                    try {
//                        file.transferTo(fileTarget);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        throw new RuntimeException("이력서를 업로드 할 수 없습니다.");
//                    }
//                    FileEntity save = FILE_REP.save(entity);
//
//                    StudentPatchRes res = StudentPatchRes.builder()
//                            .file(save.getFile())
//                            .ifile(save.getIfile())
//                            .createdAt(save.getCreatedAt())
//                            .istudent(save.getStudentEntity().getIstudent())
//                            .build();
//                    StudentIntroducedLineRes std = StudentIntroducedLineRes.builder()
//                            .introducedLine(studentSave.getIntroducedLine())
//                            .build();
//                    return StudentPatchTotalRes.builder()
//                            .res(res)
//                            .std(std)
//                            .build();
//
//                } else if (fileCateId.get().getIFileCategory() == 2) {
//                    FileEntity tempEntity = new FileEntity();
//                    FileEntity saveEntity = null;
//                    tempEntity = fileEntity;
//                    saveEntity = tempEntity;
//
//                    String savedFileNm = MyFileUtils.makeRandomFileNm(file.getOriginalFilename());
//                    entity.setFile(savedFileNm);
//                    FileEntity result = FILE_REP.save(entity);
//
//                    String targetDir = String.format("%s/student/%d", fileDir, entity.getStudentEntity().getIstudent());
//                    File fileTarget = new File(String.format("%s/%s", targetDir, savedFileNm));
//                    File tempFile = new File(targetDir);
//                    if (tempFile.exists()) {
//                        tempFile.delete();
//                    }
//                    fileEntity.setFile(null);
//                    try {
//                        FILE_REP.save(fileEntity);
//                    } catch (Exception e) {
//                        throw new RuntimeException("포트폴리오 삭제 실패");
//                    }
//                    try {
//                        file.transferTo(fileTarget);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        throw new RuntimeException("포트폴리오를 업로드 할 수 없습니다.");
//                    }
//                    FileEntity save = FILE_REP.save(entity);
//
//                    StudentPatchRes res = StudentPatchRes.builder()
//                            .file(save.getFile())
//                            .ifile(save.getIfile())
//                            .createdAt(save.getCreatedAt())
//                            .istudent(save.getStudentEntity().getIstudent())
//                            .build();
//                    StudentIntroducedLineRes std = StudentIntroducedLineRes.builder()
//                            .introducedLine(studentSave.getIntroducedLine())
//                            .build();
//                    return StudentPatchTotalRes.builder()
//                            .res(res)
//                            .std(std)
//                            .build();
//                } else if (fileCateId.get().getIFileCategory() == 4) {
//                    FileEntity tempEntity = new FileEntity();
//                    FileEntity saveEntity = null;
//                    tempEntity = fileEntity;
//                    saveEntity = tempEntity;
//
//                    String savedFileNm = MyFileUtils.makeRandomFileNm(file.getOriginalFilename());
//                    entity.setFile(savedFileNm);
//                    FileEntity result = FILE_REP.save(saveEntity);
//
//                    String targetDir = String.format("%s/student/%d", fileDir, entity.getStudentEntity().getIstudent());
//                    File fileTarget = new File(String.format("%s/%s", targetDir, savedFileNm));
//                    File tempFile = new File(targetDir);
//                    if (tempFile.exists()) {
//                        tempFile.delete();
//                    }
//                    fileEntity.setFile(null);
//                    try {
//                        FILE_REP.save(fileEntity);
//                    } catch (Exception e) {
//                        throw new RuntimeException("대표 이미지 삭제 실패");
//                    }
//                    try {
//                        file.transferTo(fileTarget);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        throw new RuntimeException("포트폴리오 대표 이미지를 업로드 할 수 없습니다.");
//                    }
//                    FileEntity save = FILE_REP.save(result);
//
//                    StudentPatchRes res = StudentPatchRes.builder()
//                            .file(save.getFile())
//                            .ifile(save.getIfile())
//                            .createdAt(save.getCreatedAt())
//                            .istudent(save.getStudentEntity().getIstudent())
//                            .build();
//                    StudentIntroducedLineRes std = StudentIntroducedLineRes.builder()
//                            .introducedLine(studentSave.getIntroducedLine())
//                            .build();
//                    return StudentPatchTotalRes.builder()
//                            .res(res)
//                            .std(std)
//                            .build();
//                } else if (fileCateId.get().getIFileCategory() == 3) {
//                    FileEntity tempEntity = new FileEntity();
//                    FileEntity saveEntity = null;
//                    tempEntity = fileEntity;
//                    saveEntity = tempEntity;
//
//                    String savedFileNm = dto.getFileLink();
//                    entity.setFile(savedFileNm);
//                    FileEntity result = FILE_REP.save(saveEntity);
//
//                    StudentPatchRes res = StudentPatchRes.builder()
//                            .file(result.getFile())
//                            .ifile(result.getIfile())
//                            .createdAt(result.getCreatedAt())
//                            .istudent(result.getStudentEntity().getIstudent())
//                            .build();
//                    StudentIntroducedLineRes std = StudentIntroducedLineRes.builder()
//                            .introducedLine(studentSave.getIntroducedLine())
//                            .build();
//                    return StudentPatchTotalRes.builder()
//                            .res(res)
//                            .std(std)
//                            .build();
//                }
//                return null;
//            }
//        }
//    }


    public StudentSelTotalRes selStudent(StudentSelDto dto) {
        Optional<StudentEntity> stdId = STU_REP.findById(dto.getIstudent());
        StudentSelSubjectRes subjectRes = studentQdsl.subjectVo(dto.getIstudent());
        List<StudentCertificateSelRes> certificateRes = studentQdsl.certificateRes(dto.getIstudent());

        if (stdId != null) {
            StudentSelTotalRes build = StudentSelTotalRes.builder()
                    .std(StudentSelStudentRes.builder()
                            .istudent(stdId.get().getIstudent())
                            .name(stdId.get().getName())
                            .address(stdId.get().getAddress())
                            .mobileNumber(stdId.get().getMobileNumber())
                            .id(stdId.get().getId())
                            .education(stdId.get().getEducation())
                            .startedAt(stdId.orElseThrow().getStartedAt())
                            .endedAt(stdId.orElseThrow().getEndedAt())
                            .subject(subjectRes)
                            .certificates(certificateRes)
                            .build())
                    .build();
            return build;
        } else {
            log.info("studentSelRes : " + stdId);
            throw new RuntimeException("pk를 찾을 수 없음");
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

            StudentEntity save = STU_REP.save(student);

            return StudentCertificateRes.builder()
                    .istudent(save.getIstudent())
                    .res(resultList)
                    .build();
        } else {
            throw new RuntimeException("존재하지 않는 pk 입니다.");
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
            throw new EntityNotFoundException("찾을 수 없는 Pk 입니다.");
        }
    }
}

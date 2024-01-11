package com.green.greenjobgo1.student;

import com.green.greenjobgo1.admin.companylist.model.CompanylistRes;
import com.green.greenjobgo1.admin.companylist.model.CompanylistVo;
import com.green.greenjobgo1.admin.employeeProfile.model.EmployeeProfileVo;
import com.green.greenjobgo1.common.entity.*;
import com.green.greenjobgo1.common.utils.MyFileUtils;
import com.green.greenjobgo1.common.utils.PagingUtils;
import com.green.greenjobgo1.repository.*;
import com.green.greenjobgo1.student.model.*;
import com.querydsl.core.types.Projections;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    private String fileDir;


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

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setIstudent(stdId.get().getIstudent());
        studentEntity.setIntroducedLine(dto.getIntroducedLine());

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

        Long fileCount = studentQdsl.countByFileCategoryEntityIFileCategoryInAndStudentEntityIstudent(
                Arrays.asList(1L, 2L, 3L), studentSave.getIstudent());

        if (fileCount >= 5) {
            throw new RuntimeException("한 수강생당 파일은 5개까지만 올릴 수 있습니다.");
        }


        if (savedFileNm != null) {
            entity.setFile(savedFileNm);
            FileEntity result = FILE_REP.save(entity);

            String targetDir = String.format("%s/student/%d", fileDir, entity.getStudentEntity().getIstudent());
            File fileTargetDir = new File(targetDir);

            if (!fileTargetDir.exists() && !fileTargetDir.mkdirs()) {
                throw new RuntimeException("디렉토리를 생성할 수 없습니다.");
            }

            File fileTarget = new File(String.format("%s/%s", targetDir, savedFileNm));
            try {
                file.transferTo(fileTarget);
            } catch (IOException e) {
                throw new RuntimeException("파일을 업로드 할 수 없습니다.");
            }

            StudentInsRes res = StudentInsRes.builder()
                    .file(result.getFile())
                    .ifile(result.getIfile())
                    .createdAt(result.getCreatedAt())
                    .istudent(result.getStudentEntity().getIstudent())
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


    public StudentPatchTotalRes patchFile(MultipartFile file, StudentPatchDto dto) {
        Optional<FileCategoryEntity> fileCateId = FILE_CATE_REP.findById(dto.getIFileCategory());
        Optional<StudentEntity> stdId = STU_REP.findById(dto.getIstudent());
        List<FileEntity> fileAll = FILE_REP.findAllByStudentEntity(stdId.orElseThrow(EntityNotFoundException::new));

        FileEntity entity = new FileEntity();
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setIstudent(stdId.get().getIstudent());
        studentEntity.setIntroducedLine(dto.getIntroducedLine());
        StudentEntity studentSave = (dto.getIntroducedLine() != null) ? STU_REP.save(studentEntity) : studentEntity;



        for (FileEntity fileEntity : fileAll) {
            if (fileCateId.isPresent()) {
                Long fileCount = studentQdsl.countByFileCategoryEntityIFileCategoryInAndStudentEntityIstudent(
                        Arrays.asList(1L, 2L, 3L), studentEntity.getIstudent());

                if (fileCount >= 5) {
                    throw new RuntimeException("한 수강생당 파일은 5개까지만 올릴 수 있습니다.");
                }
                Long iFileCategory = fileCateId.get().getIFileCategory();
                if (iFileCategory == 1 || iFileCategory == 2 || iFileCategory == 4) {
                    fileUpload(file, dto, entity, fileEntity, studentSave);
                } else if (iFileCategory == 3) {
                    fileLinkUpload(dto, entity, fileEntity, studentSave);
                }
            }
        }
        FileEntity save = FILE_REP.save(entity);

        StudentPatchRes res = StudentPatchRes.builder()
                .file(save.getFile())
                .ifile(save.getIfile())
                .createdAt(save.getCreatedAt())
                .istudent(save.getStudentEntity().getIstudent())
                .build();
        StudentIntroducedLineRes std = StudentIntroducedLineRes.builder()
                .introducedLine(studentSave.getIntroducedLine())
                .build();

        return StudentPatchTotalRes.builder()
                .res(res)
                .std(std)
                .build();
    }

    public StudentDelRes delFile(StudentDelDto dto) {
        Optional<FileEntity> fileId = FILE_REP.findById(dto.getIfile());

        if (fileId.isPresent()) {
            FileEntity fileEntity = fileId.get();

            String targetDir = String.format("%s/student/%d", fileDir, fileEntity.getStudentEntity().getIstudent());
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


    private void fileUpload(MultipartFile file, StudentPatchDto dto, FileEntity entity,
                            FileEntity fileEntity, StudentEntity studentSave) {
        String savedFileNm = MyFileUtils.makeRandomFileNm(file.getOriginalFilename());
        entity.setFile(savedFileNm);

        try {
            handleFileOperations(file, entity, fileEntity, savedFileNm);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("파일 업로드 또는 삭제 중 오류 발생", e);
        }
    }

    private void fileLinkUpload(StudentPatchDto dto, FileEntity entity,
                                FileEntity fileEntity, StudentEntity studentSave) {
        String savedFileNm = dto.getFileLink();
        entity.setFile(savedFileNm);

        try {
            FILE_REP.save(fileEntity);
        } catch (Exception e) {
            throw new RuntimeException("파일 링크 업로드 중 오류 발생", e);
        }
    }

    private void handleFileOperations(MultipartFile file, FileEntity entity, FileEntity fileEntity, String savedFileNm) throws IOException {
        File targetDir = new File(String.format("%s/student/%d", fileDir, entity.getStudentEntity().getIstudent()));
        File fileTarget = new File(targetDir, savedFileNm);

        if (targetDir.exists()) {
            targetDir.delete();
        }

        fileEntity.setFile(null);
        FILE_REP.save(fileEntity);

        file.transferTo(fileTarget);
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


    public StudentSelRes selStudent(StudentSelDto dto) {
        StudentSelRes studentSelRes = studentQdsl.studentVo(dto.getIstudent());

        if (studentSelRes != null) {
            return StudentSelRes.builder()
                    .istudent(studentSelRes.getIstudent())
                    .name(studentSelRes.getName())
                    .subjectName(studentSelRes.getSubjectName())
                    .startedAt(studentSelRes.getStartedAt())
                    .endedAt(studentSelRes.getEndedAt())
                    .address(studentSelRes.getAddress())
                    .mobileNumber(studentSelRes.getMobileNumber())
                    .id(studentSelRes.getId())
                    .education(studentSelRes.getEducation())
                    .certificates(studentSelRes.getCertificates())
                    .build();
        } else {
            throw new EntityNotFoundException("찾을 수 없는 pk 입니다.");
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
}

package com.green.greenjobgo1.admin.std_management;

import com.green.greenjobgo1.admin.std_management.model.*;
import com.green.greenjobgo1.common.entity.*;
import com.green.greenjobgo1.common.utils.PagingUtils;
import com.green.greenjobgo1.repository.*;
import com.green.greenjobgo1.common.security.config.security.MyUserDetailsServiceImpl;
import com.green.greenjobgo1.student.model.StudentDelDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final AdminStudentQdsl adminStudentQdsl;
    private final MyUserDetailsServiceImpl userDetailsService;

    @Value("${file.dir}")
    private String fileDir;


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
                        .build()).toList())
                .build();
        return ResponseEntity.ok(build);
    }

    public AdminStudentDetailFindRes selStudentDetail(AdminStudentDetailDto dto) {
        Optional<StudentEntity> byId = STU_REP.findById(dto.getIstudent());
        List<FileEntity> fileList = FILE_REP.findAllByStudentEntity(byId.get());
        List<AdminStudentFile> files = adminStudentQdsl.fileVos(dto);
        List<AdminStudentCertificateRes> certiRes = adminStudentQdsl.certificateRes(dto.getIstudent());

        if (byId.isPresent()) {
            return AdminStudentDetailFindRes.builder()
                    .res(AdminStudentDetailRes.builder()
                            .name(byId.get().getName())
                            .startedAt(byId.get().getStartedAt())
                            .endedAt(byId.get().getEndedAt())
                            .birthday(byId.get().getBirthday())
                            .address(byId.get().getAddress())
                            .addressDetail(byId.get().getAddressDetail())
                            .education(byId.get().getEducation())
                            .email(byId.get().getId())
                            .mobileNumber(byId.get().getMobileNumber())
                            .Certificates(certiRes)
                            .build())
                    .file(files)
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
                        try {
                            if (rowCount < 10) {
                                studentEntity.setCompanyMainYn(1);
                            }
                        } catch (RuntimeException e) {
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
        List<StudentEntity> all = STU_REP.findAll();
        StudentEntity stdEntity = new StudentEntity();
        StudentEntity tempEntity = new StudentEntity();
        for (StudentEntity entity : all) {
            stdEntity = entity;
            tempEntity = stdEntity;
        }
        for (int i = 0; i < all.size(); i++) {
            tempEntity.setStartedAt(dto.getStartedAt());
            tempEntity.setEndedAt(dto.getEndedAt());
            tempEntity.setEditableYn(dto.getEditableYn());
        }
        StudentEntity save = STU_REP.save(tempEntity);


        return AdminStudentRoleRes.builder()
                .editableYn(save.getEditableYn())
                .startedAt(save.getStartedAt())
                .endedAt(save.getEndedAt())
                .build();

    }

    public AdminStudentUpdRes updStudent(AdminStudentUpdDto dto, List<String> certificateValue) {
        Optional<StudentEntity> stdId = STU_REP.findById(dto.getIstudent());

        if (stdId.isPresent()) {
            List<CertificateEntity> certificates = stdId.get().getCertificates();
            List<AdminStudentCertificateRes> resultList = new ArrayList<>();

            StudentEntity student = stdId.get();

            student.setName(dto.getStudentName());
            student.setId(dto.getEmail());
            student.setAddress(dto.getAddress());
            student.setEducation(dto.getEducation());


            for (int i = 0; i < certificates.size(); i++) {
                CertificateEntity certificate = certificates.get(i);
                if (i < certificateValue.size()) {
                    certificate.setCertificate(certificateValue.get(i));
                    CertificateEntity save = CERT_REP.save(certificate);
                    AdminStudentCertificateRes build = AdminStudentCertificateRes.builder()
                            .icertificate(save.getIcertificate())
                            .certificate(save.getCertificate())
                            .build();
                    resultList.add(build);
                }
            }
            student.setCertificates(certificates);

            StudentEntity stdSave = STU_REP.save(student);


            return AdminStudentUpdRes.builder()
                    .istudent(stdSave.getIstudent())
                    .studentName(stdSave.getName())
                    .email(stdSave.getId())
                    .address(stdSave.getAddress() + stdSave.getAddressDetail())
                    .education(stdSave.getEducation())
                    .certificate(resultList)
                    .build();
        } else {
            throw new EntityNotFoundException("찾을 수 없는 pk 입니다.");
        }

    }


}

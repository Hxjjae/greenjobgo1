package com.green.greenjobgo1.admin.std_management;

import com.green.greenjobgo1.admin.std_management.model.*;
import com.green.greenjobgo1.common.utils.PagingUtils;
import com.green.greenjobgo1.config.entity.*;
import com.green.greenjobgo1.repository.CourseSubjectRepository;
import com.green.greenjobgo1.repository.FileRepository;
import com.green.greenjobgo1.repository.StudentCourseSubjectRepository;
import com.green.greenjobgo1.repository.StudentRepository;
import com.green.greenjobgo1.security.config.security.MyUserDetailsServiceImpl;
import com.green.greenjobgo1.security.config.security.model.MyUserDetails;
import com.green.greenjobgo1.security.config.security.model.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminStudentService {

    private final StudentRepository STU_REP;
    private final CourseSubjectRepository COS_REP;
    private final FileRepository FILE_REP;
    private final StudentCourseSubjectRepository SCS_REP;
    private final AdminStudentQdsl adminStudentQdsl;
    private final MyUserDetailsServiceImpl userDetailsService;



    public ResponseEntity<AdminStudentFindRes> selStudentList(AdminStudentDto dto, Pageable pageable) {
        long maxPage = STU_REP.count();
        PagingUtils utils = new PagingUtils(pageable.getPageNumber() , (int) maxPage+1);
        utils.setIdx((int)maxPage);
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
                        .certificate(item.getCertificate())
                        .mobileNumber(item.getMobileNumber())
                        .subjectName(item.getSubjectName())
                        .build()).toList())
                .build();
        return ResponseEntity.ok(build);


    }

    public AdminStudentDetailRes selStudentDetail(AdminStudentDetailDto dto) {
        Optional<StudentEntity> byId = STU_REP.findById(dto.getIstudent());

        if (byId.isPresent()) {
            return AdminStudentDetailRes.builder()
                    .name(byId.get().getName())
                    .startedAt(byId.get().getStartedAt())
                    .endedAt(byId.get().getEndedAt())
                    .birthday(byId.get().getBirthday())
                    .address(byId.get().getAddress())
                    .addressDetail(byId.get().getAddressDetail())
                    .education(byId.get().getEducation())
                    .email(byId.get().getId())
                    .mobileNumber(byId.get().getMobileNumber())
                    .build();
        } else {
            throw new EntityNotFoundException("찾을 수 없는 pk 입니다.");
        }
    }

    public ResponseEntity<AdminStorageStudentFindRes> selStorage(AdminStorageStudentDto dto, Pageable pageable) {
        long maxPage = STU_REP.count();
        PagingUtils utils = new PagingUtils(pageable.getPageNumber(), (int) maxPage+1);

        List<AdminStorageStudentRes> list = adminStudentQdsl.storageVos(pageable);

        AdminStorageStudentFindRes build = AdminStorageStudentFindRes.builder()
                .page(utils)
                .res(list.stream().map(item -> AdminStorageStudentRes.builder()
                        .ifile(item.getIfile())
                        .file(item.getFile())
                        .studentName(item.getStudentName())
                        .subjectName(item.getSubjectName())
                        .build()).toList())
                .build();
        return ResponseEntity.ok(build);
    }

    public AdminStorageStudentDetailRes detailStorage(AdminStorageStudentDetailDto dto) {
        StudentEntity stdEntity = new StudentEntity();
        stdEntity.setIstudent(dto.getIstudent());
        Optional<StudentCourseSubjectEntity> scsId = SCS_REP.findByStudentEntity(stdEntity);
        Optional<StudentEntity> stuId = STU_REP.findById(scsId.get().getStudentEntity().getIstudent());
        Optional<CourseSubjectEntity> cosId = COS_REP.findById(scsId.get().getCourseSubjectEntity().getIcourseSubject());
        List<FileEntity> fileRepAll = FILE_REP.findAll();

        if (stuId.isPresent() && scsId.isPresent()) {
            return AdminStorageStudentDetailRes.builder()
                    .icourseSubject(cosId.get().getIcourseSubject())
                    .startedAt(cosId.get().getStartedAt())
                    .endedAt(cosId.get().getEndedAt())
                    .birthday(stuId.get().getBirthday())
                    .name(stuId.get().getName())
                    .gender(stuId.get().getGender())
                    .address(stuId.get().getAddress())
                    .education(stuId.get().getEducation())
                    .mobileNumber(stuId.get().getMobileNumber())
                    .file(fileRepAll.stream().map(item -> AdminStudentFile.builder()
                            .file(item.getFileCategoryEntity().getFile())
                            .build()).toList())
                    .build();
        } else {
            throw new EntityNotFoundException("찾을 수 없는 pk 입니다.");
        }
    }

    public AdminStorageStudentPatchRes patchStorage(AdminStorageStudentPatchDto dto) {
        Optional<StudentEntity> byId = STU_REP.findById(dto.getIstudent());

        if (byId.isPresent()) {
            return AdminStorageStudentPatchRes.builder()
                    .istudent(byId.get().getIstudent())
                    .storageYn(1)
                    .build();
        } else {
            throw new EntityNotFoundException("찾을 수 없는 pk 입니다.");
        }
    }

    public AdminStudentRoleRes patchRole(AdminStudentRoleDto dto) {
        Optional<StudentEntity> stdId = STU_REP.findById(dto.getIstudent());

        if (stdId.isPresent()) {
            stdId.get().setStartedAt(dto.getStartedAt());
            stdId.get().setEndedAt(dto.getEndedAt());

            Integer editableYn = dto.getEditableYn();

            if (editableYn != null) {
                stdId.get().setEditableYn(editableYn);
            }

            StudentEntity save = STU_REP.save(stdId.get());

            return AdminStudentRoleRes.builder()
                    .startedAt(save.getStartedAt())
                    .endedAt(save.getEndedAt())
                    .istudent(save.getIstudent())
                    .editableYn(save.getEditableYn())
                    .build();
        } else {
            throw new EntityNotFoundException("찾을 수 없는 pk 입니다.");
        }
    }
}

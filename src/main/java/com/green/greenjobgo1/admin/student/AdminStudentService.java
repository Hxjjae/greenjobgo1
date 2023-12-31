package com.green.greenjobgo1.admin.student;

import com.green.greenjobgo1.admin.student.model.*;
import com.green.greenjobgo1.common.utils.PagingUtils;
import com.green.greenjobgo1.config.entity.StudentEntity;
import com.green.greenjobgo1.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminStudentService {

    private final StudentRepository STU_REP;
    private final AdminStudentQdsl adminStudentQdsl;

    public ResponseEntity<AdminStudentFindRes> selStudentList(AdminStudentDto dto, Pageable pageable) {
        long maxPage = STU_REP.count();
        PagingUtils utils = new PagingUtils(dto.getPage(), (int) maxPage);
        dto.setStaIdx(utils.getStaIdx());

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
                    .build();
        } else {
            throw new EntityNotFoundException("찾을 수 없는 pk 입니다.");
        }
    }
}

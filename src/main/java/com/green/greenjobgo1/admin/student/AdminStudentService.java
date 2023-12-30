package com.green.greenjobgo1.admin.student;

import com.green.greenjobgo1.admin.student.model.AdminStudentDto;
import com.green.greenjobgo1.admin.student.model.AdminStudentFindRes;
import com.green.greenjobgo1.admin.student.model.AdminStudentRes;
import com.green.greenjobgo1.common.utils.PagingUtils;
import com.green.greenjobgo1.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminStudentService {

    private final StudentRepository STU_REP;
    private final AdminStudentQdsl adminStudentQdsl;

    public ResponseEntity<AdminStudentFindRes> selStudentList(AdminStudentDto dto, Pageable pageable) {
        long maxPage = STU_REP.count();
        PagingUtils utils = new PagingUtils(dto.getPage() ,(int)maxPage);
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
}

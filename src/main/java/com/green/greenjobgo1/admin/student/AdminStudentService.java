package com.green.greenjobgo1.admin.student;

import com.green.greenjobgo1.admin.student.model.AdminStudentRes;
import com.green.greenjobgo1.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminStudentService {

    private final StudentRepository STU_REP;

    public AdminStudentRes selStudentList() {

    }
}

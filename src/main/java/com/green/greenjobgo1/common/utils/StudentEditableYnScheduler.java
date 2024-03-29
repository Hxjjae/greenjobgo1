package com.green.greenjobgo1.common.utils;


import com.green.greenjobgo1.common.entity.StudentEntity;
import com.green.greenjobgo1.repository.StudentRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class StudentEditableYnScheduler {

    private final StudentRepository STU_REP;

    public StudentEditableYnScheduler(StudentRepository stuRep) {
        this.STU_REP = stuRep;
    }

    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
    public void updateEditableYn() {
        // endedAt이 현재 날짜보다 이전인 학생들
        LocalDate currentDate = LocalDate.now();
        List<StudentEntity> studentsToUpdateEndedAt = STU_REP.findByEndedAtBefore(currentDate);
        List<StudentEntity> studentsToUpdateStartedAt = STU_REP.findByStartedAtAfter(currentDate);

        // 조회된 학생들의 editableYn을 1로 설정
        studentsToUpdateStartedAt.forEach(student ->  student.setEditableYn(1));
        // 조회된 학생들의 editableYn을 0으로 설정
        studentsToUpdateEndedAt.forEach(student -> student.setEditableYn(0));

        STU_REP.saveAll(studentsToUpdateStartedAt);
        STU_REP.saveAll(studentsToUpdateEndedAt);
    }
}

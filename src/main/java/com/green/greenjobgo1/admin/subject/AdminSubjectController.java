package com.green.greenjobgo1.admin.subject;

import com.green.greenjobgo1.admin.category.model.AdminCategoryDto;
import com.green.greenjobgo1.admin.subject.model.*;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/subject")
/** name = "관리자 과목 CRUD" **/
public class AdminSubjectController {

    @Autowired
    private final AdminSubjectService SERVICE;

    @PostMapping
    /** summary = "수강과목 추가", description = "" **/
    public AdminSubjectInsRes postAdminSubject(@RequestBody AdminSubjectInsDto dto) {
        return SERVICE.insAdminSubject(dto);
    }

    @GetMapping
    /** summary = "수강과목 보기", description = "iclassficiation = 대분류 pk\n" +
            "subjectName = 과정 이름\n" +
            "condition = 과정 상태\n" +
            "delYn = 삭제 여부\n" +
            "\n Responses : " +
            "\n page = 현재 페이지\n" +
            "\n maxPage = 최대 페이지\n" +
            "\n row = 행 갯수(10고정)\n" +
            "\n icourseSubject = 과정 pk\n" +
            "\n iclassfication = 대분류 pk\n" +
            "\n courseSubjectName = 과정 이름\n" +
            "\n startedAt = 시작일자\n" +
            "\n endedAt = 종료일자\n" +
            "\n instructor = 강사명\n" +
            "\n lectureRoom = 강의실\n" +
            "\n delYn = 삭제 여부\n" **/
    public ResponseEntity<AdminSubjectFindRes> getAdminSubject(@PageableDefault(sort = "icourseSubject", page = 1) Pageable pageable,
                                                               @RequestParam(required = false) Long iclassification,
                                                               @RequestParam(required = false) String subjectName,
                                                               @RequestParam(required = false, defaultValue = "0") Integer condition,
                                                               @RequestParam(required = false, defaultValue = "0") Integer delYn) {
        AdminSubjectDto subjectDto = new AdminSubjectDto();
        AdminCategoryDto categoryDto = new AdminCategoryDto();
        categoryDto.setIclassification(iclassification);
        subjectDto.setSubjectName(subjectName);
        subjectDto.setDelYn(delYn);
        subjectDto.setSubjectCondition(condition);
        AdminSubjectFindRes res = SERVICE.selAdminSubject(subjectDto, categoryDto, pageable);
        return ResponseEntity.ok().body(res);
    }

    @PutMapping
    /** summary = "수강과목 수정", description = "\nicourseSubject = 과정 pk\n" +
            "\niclassfication = 대분류 pk\n" +
            "\ncourseSubjectName = 과정 이름\n" +
            "\nstartedAt = 시작일자\n" +
            "\nendedAt = 종료일자\n" +
            "\nInstructor = 강사명\n" +
            "\nLectureRoom = 강의실\n" +
            "\nround = 회차\n" +
            "\nclassTime = 수강시간" **/

    public AdminSubjectUpdRes putAdminSubject(@RequestParam Long icourseSubject,
                                              @RequestParam Long iclassification,
                                              @RequestParam String courseSubjectName,
                                              @RequestParam LocalDate startedAt,
                                              @RequestParam LocalDate endedAt,
                                              @RequestParam String instructor,
                                              @RequestParam String lectureRoom,
                                              @RequestParam Integer round,
                                              @RequestParam Integer classTime) {
        AdminSubjectUpdDto dto = new AdminSubjectUpdDto();
        dto.setIcourseSubject(icourseSubject);
        dto.setIclassification(iclassification);
        dto.setCourseSubjectName(courseSubjectName);
        dto.setEndedAt(endedAt);
        dto.setStartedAt(startedAt);
        dto.setInstructor(instructor);
        dto.setLectureRoom(lectureRoom);
        dto.setRound(round);
        dto.setClassTime(classTime);
        return SERVICE.updAdminSubject(dto);
    }

    @PatchMapping
    /** summary = "수강과목 상태 변경", description = "icourseSubject = 과정 pk\n" +
            "\ncondition = 과정 상태 (0 = 시작전 , 1 = 진행중, 2 = 종료)\n" **/
    public AdminSubjectPatchRes patchAdminSubject(@RequestParam Long icourseSubject,
                                                  @RequestParam int condition) {
        AdminSubjectPatchDto dto = new AdminSubjectPatchDto();
        dto.setIcourseSubject(icourseSubject);
        dto.setSubjectCondition(condition);
        return SERVICE.patchAdminSubject(dto);
    }

    @DeleteMapping
    /** summary = "수강과목 삭제", description = "" **/
    public List<AdminSubjectDelRes> delAdminSubject(@RequestParam List<Long> icourseSubject) {
        AdminSubjectDelDto dto = new AdminSubjectDelDto();
        dto.setIcourseSubject(icourseSubject);
        return SERVICE.delAdminSubject(dto);
    }
}

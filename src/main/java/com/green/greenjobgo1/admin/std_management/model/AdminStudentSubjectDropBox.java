package com.green.greenjobgo1.admin.std_management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminStudentSubjectDropBox {
    private Long icourseSubject;
    private String subjectName;
    private Integer round;
}

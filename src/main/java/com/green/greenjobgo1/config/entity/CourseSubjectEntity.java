package com.green.greenjobgo1.config.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "course_subject")
@Order(2)
@Entity
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CourseSubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, length = 20, name = "icourse_subject")
    private Long icourseSubject;

    @ManyToOne
    @JoinColumn(name = "iclassfication")
    private CategorySubjectEntity categorySubjectEntity;

    @OneToMany(mappedBy = "courseSubjectEntity")
    private List<StudentCourseSubjectEntity> scsList = new ArrayList<>();


    @Column(nullable = false, length = 20, name = "subject_name")
    private String subjectName;

    @Column(nullable = false, name = "started_at")
    private LocalDate startedAt;

    @Column(nullable = false, name = "ended_at")
    private LocalDate endedAt;

    @Column(nullable = false, length = 10)
    @ColumnDefault("0")
    private int subjectCondition;

    @Column(nullable = false, length = 10, name = "del_yn")
    @ColumnDefault("0")
    private int delYn;

}

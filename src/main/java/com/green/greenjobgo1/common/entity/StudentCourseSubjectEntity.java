package com.green.greenjobgo1.common.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.core.annotation.Order;

@Data
@Table(name = "student_course_subject")
@Order(3)
@Entity
@ToString(callSuper = true)
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StudentCourseSubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, length = 20, name = "istd_cos_sub")
    private Long iStdCosSub;

    @ManyToOne
    @JoinColumn(name = "icourseSubject")
    private CourseSubjectEntity courseSubjectEntity;

    @ManyToOne
    @JoinColumn(name = "istudent")
    private StudentEntity studentEntity;

}

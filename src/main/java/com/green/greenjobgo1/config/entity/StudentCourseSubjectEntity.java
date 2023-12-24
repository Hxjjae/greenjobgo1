package com.green.greenjobgo1.config.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.core.annotation.Order;

@Data
@Table(name = "student_course_subject")
@Order(3)
@Entity
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourseSubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, length = 20, name = "istd_cos_sub")
    private Long iStdCosSub;

    @ManyToOne
    @JoinColumn(name = "icourse_subject")
    private CourseSubjectEntity courseSubjectEntity;

    @ManyToOne
    @JoinColumn(name = "istudent")
    private StudentEntity studentEntity;

}

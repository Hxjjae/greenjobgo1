package com.green.greenjobgo1.config.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Table(name = "student_course_subject")
@Entity
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourseSubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, length = 20, name = "istd_cos_sub")
    private Long iStdCosSub;

    @Column
    @ManyToOne
    private CourseSubjectEntity courseSubjectEntity;

    @Column
    @ManyToOne
    private StudentEntity studentEntity;

}

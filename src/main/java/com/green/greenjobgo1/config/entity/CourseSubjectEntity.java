package com.green.greenjobgo1.config.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@Table(name = "course_subject")
@Entity
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CourseSubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, length = 20, name = "icourse_subject")
    private Long icourseSubject;

    @Column(nullable = false, length = 20, name = "subject_name")
    private String subjectName;

    @Column(nullable = false, name = "started_at")
    private LocalDate startedAt;

    @Column(nullable = false, name = "ended_at")
    private LocalDate endedAt;

    @Column(nullable = false, length = 10)
    private int condition;
}

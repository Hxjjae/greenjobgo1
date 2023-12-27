package com.green.greenjobgo1.config.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
@Order(1)
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long istudent;

    @OneToMany(mappedBy = "studentEntity")
    private List<StudentCourseSubjectEntity> scsList = new ArrayList<>();

    @OneToMany(mappedBy = "studentEntity")
    private List<FileEntity> files = new ArrayList<>();

    @Column(nullable = false, length = 20, name = "student_id")
    private String id;

    @Column(nullable = false, length = 100, name = "student_pw")
    private String pw;

    @Column(nullable = false, length = 20, name = "mobile_number")
    private String mobileNumber;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false)
    private int gender;

    @Column(nullable = false, length = 20)
    private String address;

    @Column(nullable = false, length = 20)
    private String education;

    @Column(nullable = false, length = 20)
    private String role;

    @Column(nullable = false, name = "huntjob_yn", length = 10)
    @ColumnDefault("0")
    private int huntJobYn;

    @Column(name = "secret_key")
    private String secretKey;

    @Column(nullable = false, length = 10, name = "storage_yn")
    @ColumnDefault("0")
    private int storageYn;

    @Column(nullable = false, length = 10, name = "editable_yn")
    @ColumnDefault("0")
    private int editableYn;

    @Column(nullable = false, length = 10, name = "del_yn")
    @ColumnDefault("0")
    private int delYn;

    @Column(name = "started_at")
    private int startedAt;

    @Column(name = "ended_at")
    private int endedAt;

    @Column(name = "age")
    private int age;










}

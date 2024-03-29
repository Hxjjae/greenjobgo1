package com.green.greenjobgo1.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
@Order(1)
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long istudent;

    @OneToMany(mappedBy = "studentEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StudentCourseSubjectEntity> scsList = new ArrayList<>();

    @OneToMany(mappedBy = "studentEntity")
    private List<FileEntity> files = new ArrayList<>();

    @OneToMany(mappedBy = "studentEntity", cascade = CascadeType.ALL)
    private List<CertificateEntity> certificates;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "iclassification")
    private CategorySubjectEntity categorySubjectEntity;

    @ManyToOne
    @JoinColumn(name = "employee_profile")
    private EmployeeProfileEntity employeeProfile;

    @Column(nullable = false, length = 100, name = "student_id", unique = true)
    private String id;

    @Column(nullable = false, length = 100, name = "student_pw")
    private String pw;

    @Column(nullable = false, length = 100, name = "mobile_number")
    private String mobileNumber;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(nullable = false, length = 100)
    private String education;

    @Column(nullable = false, length = 20)
    private String role;

    @Column(nullable = false, name = "huntjob_yn", length = 10)
    @ColumnDefault("0")
    private int huntJobYn;

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
    private LocalDate startedAt;

    @Column(name = "ended_at")
    private LocalDate endedAt;

    @Column(name = "age")
    private int age;

    @Column(name = "major_yn")
    private int majorYn;

    @Column(length = 50, name = "introduced_line")
    private String introducedLine;

    @Column(length = 10)
    @ColumnDefault("0")
    private int companyMainYn;
}

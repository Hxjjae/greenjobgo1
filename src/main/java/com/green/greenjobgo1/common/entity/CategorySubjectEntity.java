package com.green.greenjobgo1.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "category_subject")
@Order(1)
@Entity
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class CategorySubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, length = 100)
    private Long iclassification;

    @OneToMany(mappedBy = "categorySubjectEntity", cascade = CascadeType.ALL)
    private List<CourseSubjectEntity> csList = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "categorySubjectEntity")
    private List<StudentEntity> students = new ArrayList<>();

    @Column(nullable = false, length = 100,unique = true)
    private String classification;

    @Column(nullable = false, length = 10, name = "del_yn")
    @ColumnDefault("0")
    private int delYn;


}

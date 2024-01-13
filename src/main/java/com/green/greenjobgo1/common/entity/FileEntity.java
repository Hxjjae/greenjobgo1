package com.green.greenjobgo1.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;

@Data
@Table(name = "file")
@Order(2)
@Entity
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, length = 20)
    private Long ifile;

    @ManyToOne
    @JoinColumn(name = "istudent")
    private StudentEntity studentEntity;

    @ManyToOne
    @JoinColumn(name = "iFileCategory")
    private FileCategoryEntity fileCategoryEntity;

    @Column(nullable = false, length = 100)
    private String file;

    @Column(nullable = false, length = 30)
    private String oneWord;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDate createdAt;

    @UpdateTimestamp
    LocalDate updatedAt;

    @Column
    private Integer mainYn;
}

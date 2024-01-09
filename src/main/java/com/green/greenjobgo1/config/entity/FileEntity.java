package com.green.greenjobgo1.config.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.core.annotation.Order;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Table(name = "file")
@Order(2)
@Entity
@ToString(callSuper = true)
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

    @CreationTimestamp
    @Column(updatable = false)
    LocalDate createdAt;

    @UpdateTimestamp
    LocalDate updatedAt;

    @Column
    private Integer mainYn;
}

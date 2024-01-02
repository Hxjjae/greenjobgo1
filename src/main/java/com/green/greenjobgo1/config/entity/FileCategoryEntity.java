package com.green.greenjobgo1.config.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "file_category")
@Order(1)
@Entity
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class FileCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, length = 20)
    private Long iFileCategory;

    @OneToMany(mappedBy = "fileCategoryEntity")
    private List<FileEntity> files = new ArrayList<>();

    @Column(length = 100)
    private String file;

    @Column(length = 100)
    private String resume;

    @Column(length = 100)
    private String selfIntroduction;

    @Column(length = 100)
    private String portFolio;

    @Column(length = 100)
    private String portfolioLink;


    @Column(length = 100)
    private String thumbnail;
}

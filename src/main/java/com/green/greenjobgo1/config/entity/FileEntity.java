package com.green.greenjobgo1.config.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Table(name = "file")
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
    private StudentEntity studentEntity;

    @ManyToOne
    private FileCategoryEntity fileCategoryEntity;

    @Column(nullable = false, length = 100)
    private String file;



}

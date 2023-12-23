package com.green.greenjobgo1.config.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Table(name = "category_subject")
@Entity
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CategorySubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, length = 20)
    private Long iClassification;

    @Column(nullable = false, length = 10)
    private String classification;

}

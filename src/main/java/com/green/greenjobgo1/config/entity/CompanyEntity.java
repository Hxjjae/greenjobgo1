package com.green.greenjobgo1.config.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;

@Entity
@Data
@Order(1)
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false,nullable = false,columnDefinition = "BIGINT UNSIGNED",length=100)
    private Long icompany;

    @Column(nullable = false, length = 20, name = "id")
    private String id;

    @Column(nullable = false, length = 100, name = "password")
    private String password;

    @Column(nullable = false, length = 20, name = "role")
    private String role;

    @Column(nullable = false, length = 10, name = "started_at")
    private LocalDate startedAt;

    @Column(nullable = false, length = 10, name = "ended_at")
    private LocalDate endedAt;


}

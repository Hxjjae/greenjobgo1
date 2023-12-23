package com.green.greenjobgo1.config.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.core.annotation.Order;

@Entity
@Data
@Order(1)
@Table(name = "Company_list")
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class CompanyListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false,nullable = false,columnDefinition = "BIGINT UNSIGNED",length=100)
    private Long companyCode;

    @Column(nullable = false, length = 20, name = "company_name")
    private String companyName;
}

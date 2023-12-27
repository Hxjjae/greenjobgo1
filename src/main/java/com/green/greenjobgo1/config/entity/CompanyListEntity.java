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

    @Column(length = 50, name = "sector")
    private String sector;

    @Column(length = 20, name = "manager")
    private String manager;

    @Column(length = 20, name = "phone_number")
    private String phoneNumber;

    @Column(length = 30, name = "date_conslusion")
    private String dateConslusion;
}

package com.green.greenjobgo1.common.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;

@Entity
@Data
@Order(1)
@Table(name = "Company_list")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
public class CompanyListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false,nullable = false,columnDefinition = "BIGINT UNSIGNED",length=100)
    private Long companyCode;

    @Column(length = 100, name = "area")
    private String area;

    @Column(nullable = false, length = 100, name = "company_name",unique = true)
    private String companyName;

    @Column(length = 100, name = "leader_name")
    private String leaderName;

    @Column(length = 100, name = "homepage")
    private String homepage;

    @Column(length = 100, name = "manager")
    private String manager;

    @Column(length = 100, name = "phone_number")
    private String phoneNumber;

    @Column(length = 100, name = "date_conslusion")
    private LocalDate dateConslusion;

}

package com.green.greenjobgo1.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.core.annotation.Order;

@Entity
@Data
@SuperBuilder
@Order(1)
@Table(name = "EmployeeProfile")
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class EmployeeProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false,nullable = false,columnDefinition = "BIGINT UNSIGNED",length=100)
    private Long iemply;

    @Column(length = 20, name = "one_word")
    private String oneWord;

    @Column(nullable = false, length = 20, name = "name",unique = true)
    private String name;
    @Column(length = 20, name = "counseling_number")
    private String counselingNumber;

    @Column(nullable = false, length = 20, name = "phone_number")
    private String phoneNumber;

    @Column(length = 20, name = "email")
    private String email;

    @Column(length = 50, name = "profile_pic")
    private String profilePic;

    @Column(nullable = false, length = 10, name = "del_yn")
    @ColumnDefault("0")
    private int delYn;
}

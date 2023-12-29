package com.green.greenjobgo1.config.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
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

    @Column(nullable = false, length = 20, name = "name")
    private String name;
    @Column(length = 20, name = "conuseling_number")
    private String conuselingNumber;

    @Column(nullable = false, length = 20, name = "phone_number")
    private String phoneNumber;

    @Column(length = 20, name = "email")
    private String email;

    @Column(length = 50, name = "profile_pic")
    private String profilePic;

    @Column(length = 20, name = "kakaoid")
    private String kakaoid;




}

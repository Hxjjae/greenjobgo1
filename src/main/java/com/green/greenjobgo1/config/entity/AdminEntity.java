package com.green.greenjobgo1.config.entity;

import jakarta.persistence.*;

@Table(name = "admin")
@Entity
public class AdminEntity{

    /** pk **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long iadmin;

    @Column
    private String id;

    @Column
    private String pw;

    @Column
    private String role;

    @Column
    private int delYn;

    @Column
    private String secretKey;

}

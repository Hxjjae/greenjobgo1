package com.green.greenjobgo1.common.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Data
@Table(name = "admin")
@Entity
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminEntity{

    /** pk **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long iadmin;

    /** admin ID **/
    @Column(nullable = false, length = 20, name = "admin_id")
    private String id;

    /** admin PW **/
    @Column(nullable = false, length = 100, name = "admin_pw")
    private String pw;

    /** admin role **/
    @Column(nullable = false, length = 30)
    private String role;

    /** admin name ex) 취업지원실 **/
    @Column(nullable = false, length = 50)
    private String name; 

    /** **/
    @Column(nullable = false, length = 10, name = "del_yn")
    @ColumnDefault("0")
    private int delYn;

}

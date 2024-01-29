package com.green.greenjobgo1.common.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Table(name = "login_pic")
@Entity
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginPicEntity {
    /** pk **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long ipic;

    @Column(nullable = false, length = 50)
    private String pic;
}

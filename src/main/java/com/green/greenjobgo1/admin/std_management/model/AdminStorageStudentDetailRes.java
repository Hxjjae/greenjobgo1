package com.green.greenjobgo1.admin.std_management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminStorageStudentDetailRes {
    private Long icourseSubject;
    private LocalDate startedAt;
    private LocalDate endedAt;
    private String name;
    private LocalDate birthday;
    private int gender;
    private String address;
    private String education;
    private String mobileNumber;
    private List<AdminStudentFile> file;
}

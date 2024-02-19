package com.green.greenjobgo1.admin.employeeProfile;

import com.green.greenjobgo1.admin.employeeProfile.model.EmployeeProfileDto;
import com.green.greenjobgo1.admin.employeeProfile.model.EmployeeProfileVo;
import com.green.greenjobgo1.common.entity.EmployeeProfileEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/profile")
/** "취업관리실 프로필" **/
public class EmployeeProfileController {
    private final EmployeeProfileService service;

    @GetMapping
    /** summary = "직원 프로필 리스트",description = "사진위치: /img/employee/pk번호/파일명 <br>" +
            "oneWord: 한마디 <br>"+
            "name: 직원 이름 <br>"+
            "counselingNumber : 상담 전화 <br>"+
            "phone : 휴대폰 번호 <br>"+
            "email : 이메일 <br>" **/
    public ResponseEntity<List<EmployeeProfileVo>> getProfile(){
        return ResponseEntity.ok(service.getProfile());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    /** summary = "직원 프로필 추가",description = "oneWord: 한마디 <br>"+
            "name: 직원 이름 <br>"+
            "counselingNumber : 상담 전화 <br>"+
            "phone : 휴대폰 번호 <br>"+
            "email : 이메일 <br>"+
            "<예외처리> <br>"+
            "취업담당실장 이름 중복 예외처리 에러코드 446" **/
    public ResponseEntity<EmployeeProfileEntity> insProfile(EmployeeProfileDto dto,
                                                            @RequestPart MultipartFile pic){
        return ResponseEntity.ok(service.insProfile(dto,pic));
    }
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    /** summary = "직원 프로필 수정", description = "oneWord: 한마디 <br>"+
            "name: 직원 이름 <br>"+
            "counselingNumber : 상담 전화 <br>"+
            "phone : 휴대폰 번호 <br>"+
            "email : 이메일 <br>"+
            "<예외처리> <br>"+
            "취업담당실장 이름 중복 예외처리 에러코드 446" **/
    public ResponseEntity<EmployeeProfileVo> patchProfile(@RequestParam Long iemply,
                                                        @RequestParam(required = false) String oneWord,
                                                        @RequestParam(required = false) String name,
                                                        @RequestParam(required = false) String counselingNumber,
                                                        @RequestParam(required = false) String phone,
                                                        @RequestParam(required = false) String email,
                                                        @RequestPart(required = false) MultipartFile pic){
        return ResponseEntity.ok(service.patchProfile(iemply,name,oneWord,counselingNumber,phone,email,pic));
    }

    @DeleteMapping("/{iemply}")
    /** summary = "프로필삭제",description = "삭제완료시 리턴값 1" **/
    public ResponseEntity<Integer> delprofile(@PathVariable Long iemply){
        return ResponseEntity.ok(service.delprofile(iemply));
    }
}

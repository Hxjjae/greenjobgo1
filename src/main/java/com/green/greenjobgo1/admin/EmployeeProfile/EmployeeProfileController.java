package com.green.greenjobgo1.admin.EmployeeProfile;

import com.green.greenjobgo1.admin.EmployeeProfile.model.EmployeeProfileDto;
import com.green.greenjobgo1.admin.EmployeeProfile.model.EmployeeProfileVo;
import com.green.greenjobgo1.config.entity.EmployeeProfileEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Tag(name = "profile")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class EmployeeProfileController {
    private final EmployeeProfileServicempl service;

    @GetMapping
    @Operation(summary = "직원 프로필 리스트",description = "")
    public ResponseEntity<List<EmployeeProfileVo>> getProfile(){
        return ResponseEntity.ok(service.getProfile());
    }

    @PostMapping
    @Operation(summary = "직원 프로필 입력",description = "")
    public ResponseEntity<EmployeeProfileEntity> insProfile(@RequestBody EmployeeProfileDto dto){
        return ResponseEntity.ok(service.insProfile(dto));
    }

    @PatchMapping(value = "pic",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "사진 수정",description = "")
    public ResponseEntity<EmployeeProfileVo> patchPic(@RequestParam Long number,@RequestPart MultipartFile pic){
        return ResponseEntity.ok(service.patchPic(number,pic));
    }

    @PatchMapping("/name")
    @Operation(summary = "이름 수정",description = "")
    public ResponseEntity<EmployeeProfileVo> patchPic(@RequestParam Long iemply,@RequestParam String name){
        return ResponseEntity.ok(service.patchName(iemply,name));
    }

    @PatchMapping("/phone")
    @Operation(summary = "전화번호 수정",description = "")
    public ResponseEntity<EmployeeProfileVo> patchPhone(@RequestParam Long iemply,@RequestParam String phone){
        return ResponseEntity.ok(service.patchPhone(iemply,phone));
    }
    @PatchMapping("/email")
    @Operation(summary = "이메일 수정",description = "")
    public ResponseEntity<EmployeeProfileVo> patchEmail(@RequestParam Long iemply,@RequestParam String email){
        return ResponseEntity.ok(service.patchemail(iemply,email));
    }

    @PatchMapping("/kakaoid")
    @Operation(summary = "카카오아이디 수정",description = "")
    public ResponseEntity<EmployeeProfileVo> patchKakaoid(@RequestParam Long iemply,@RequestParam String kakaoid){
        return ResponseEntity.ok(service.patchKakaoid(iemply,kakaoid));
    }

    @DeleteMapping("/{iemply}")
    @Operation(summary = "프로필삭제",description = "삭제완료시 리턴값 1")
    public ResponseEntity<Integer> delprofile(@PathVariable Long iemply){
        return ResponseEntity.ok(service.delprofile(iemply));
    }
}

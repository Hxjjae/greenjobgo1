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

    @PostMapping()
    @Operation(summary = "직원 프로필 입력",description = "")
    public ResponseEntity<EmployeeProfileEntity> insProfile(@RequestBody EmployeeProfileDto dto){
        return ResponseEntity.ok(service.insProfile(dto));
    }

    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "사진 수정",description = "")
    public ResponseEntity<Integer> patchPic(@RequestParam Long number,@RequestPart MultipartFile pic){
        return ResponseEntity.ok(service.patchPic(number,pic));
    }
}
